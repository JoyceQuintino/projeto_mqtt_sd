package javamqtt;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
	
    private final static int minTemp = 0;
    private final static int maxTemp = 2000;

    public static void main(String[] args) throws InterruptedException {
        TemperatureSensor temperatureSensor = new TemperatureSensor("tcp://localhost:1883", null, null);
        temperatureSensor.start();


        new Listener(temperatureSensor, "sensor/temperature/#", 0);

        while (true) {
        	Thread.sleep(1000);
            String mensagem = Integer.toString(ThreadLocalRandom.current().nextInt(minTemp, maxTemp + 1));
            System.out.println("Current Temperature: "+ ThreadLocalRandom.current().nextInt(minTemp, maxTemp + 1));
            temperatureSensor.publish("sensor/temperature/caldeira", mensagem.getBytes(), 0);
        }

    }

}
