package holmeier.peter.exceptions;

import Prog1Tools.IOTools;

public class PingPong {

	public static void Ping() throws PingException {
		System.out.println("Ping aufgerufen!");
		throw new PingException();
	}

	public static void Pong() throws PongException {
		System.out.println("Pong aufgerufen!");
		throw new PongException();
	}

	public PingPong() throws PingPongException {
		System.out.println("PingPong aufgerufen");
		throw new PingPongException();
	}

	public static void Hauptprogramm() throws PingException, PongException,
			PingPongException {
		System.out.println("1 = Ping");
		System.out.println("2 = Pong");
		System.out.println("3 = PingPong");
		System.out.println("");
		int choice = IOTools.readInteger("Ihre Wahl:");
		switch (choice) {
		case 1:
			Ping();
			break;
		case 2:
			Pong();
			break;
		case 3:
			Ping();
			break;
		default:
			System.out.println("Eingabefehler!");
		}
	}

	public static void main(String[] args) throws PingPongException {
		System.out.println("Betrete kritischen Bereich!");
		try {
			Hauptprogramm();
		}
		// catch(PingException ex){
		// System.out.println("PingException aufgetreten");
		// }
		catch (PongException e) {
			System.out.println("PONG");
		}
		// catch(PingPongException ex){
		// System.out.println("PingPongException aufgetreten");
		// }
		finally {
			System.out.println("Verlasse kritischen Bereich!");
		}
	}

}
