package holmeier.peter.kleineapps;

public class GrussWort {

	static String vorname;
	static String nachname;

	public static void main(String[] args) {

		for (int i = 0; i == args.length;) { // 1 Parameter
			System.out.println("Bist du stumm?");
			break;
		}

		for (int i = 1; i == args.length;) {
			System.out.println("Hallo, " + args[0] + "!");
			System.out.println("Wie ist dein Nachname?");
			break;
		}

		for (int i = 2; i == args.length;) { // 2 Paramter
			System.out.println("Hallo, " + args[0] + "!");
			System.out.println(args[1] + " ist aber ein schoener Nachname!");
			break;
		}

		for (int j = 3; j <= args.length;) {

			for (int i = args.length; i <= args.length + 1; i++) { // Mehr als 2
																	// Parameter
				System.out.println("Hallo, " + args[i - i] + " "
						+ args[i - i + 1] + "!");
				System.out.println(args[i - 1]
						+ " ist aber ein schoener Nachname!");
				break;
			}
			break;
		}
	}

}
