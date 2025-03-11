package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sensor = new TemperatureSensor();

		// TODO - start
		Client sensorClient = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);

		// create a client object and use it to
		// - connect to the broker - user "sensor" as the user name
		sensorClient.connect();

		// - publish the temperature(s)

		for (int i = 0; i < COUNT; i++) {
			int temperature = sensor.read();
			sensorClient.publish(Common.TEMPTOPIC, "Temperature: " + temperature + " Celcius");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// - disconnect from the broker
		sensorClient.disconnect();

		// TODO - end

		System.out.println("Temperature device stopping ... ");

		throw new UnsupportedOperationException(TODO.method());

	}
}
