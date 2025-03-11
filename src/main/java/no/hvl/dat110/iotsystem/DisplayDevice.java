package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		
		// TODO - START

		//Oppretter en klient for å koble ti brokeren
		Client displayClient = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		
		//Koble til brukeren
		displayClient.connect();

		// - create the temperature topic on the broker
		displayClient.createTopic(Common.TEMPTOPIC);

		// - subscribe to the topic
		displayClient.subscribe(Common.TEMPTOPIC);
		// - receive messages on the topic
		for (int i = 0; i < COUNT; i++) {
			Message msg = displayClient.receive();
			if (msg != null) {
				System.out.println("[Display] Received: " + msg.toString());
			}
			try {
				Thread.sleep(2000); //Simulerer mottak av data hvert 2. sekund
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// - unsubscribe from the topic and disconnect from the broker
		displayClient.unsubscribe(Common.TEMPTOPIC);
		displayClient.disconnect();
		
		// TODO - END
		
		System.out.println("Display stopping ... ");
		
		throw new UnsupportedOperationException(TODO.method());
		
	}
}
