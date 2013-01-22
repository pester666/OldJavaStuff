package holmeier.peter.kleineapps;

public class Kommandozeilentest {

	public static void main(String[] args) {

		for (int i = 0; i == args.length;) {
			System.out
					.println("Der Aufruf erfolte ohne Kommandozeilenargumente");
			break;
		}

		for (int i = 0; i < args.length; i++) {
			System.out.println("Das " + (i + 1) + "."
					+ " Kommandozeilenargument lautet: " + args[i]);

		}

	}
}
