package holmeier.peter.spiele;

import holmeier.peter.IOTools;

public class Ratespiel {
	public static void main(String[] args) {

		int geheimZahl = (int) (50 * Math.random() + 1);
		// System.out.println(geheimZahl);

		System.out.println("Willkommen beim Zahlenraten.");
		System.out.println(" ");
		System.out
				.println("Ich denke mir eine Zahl zwischen 1 und 50. Rate diese Zahl!");
		System.out.println(" ");

		for (int versuch = 1; versuch <= 10; versuch++) {
			int eingabe;
			eingabe = IOTools.getIntChecked("Zahl eingeben:");
			{
				System.out.println(versuch + ". Versuch: " + eingabe);

				if (eingabe == geheimZahl) {
					System.out.println("Du hast meine Zahl beim " + versuch
							+ ". Versuch erraten!");
					System.out.println(" ");
					break;
				}
				if (eingabe > geheimZahl) {
					System.out.println("Meine Zahl ist kleiner!");
					System.out.println(" ");
				}
				if (eingabe < geheimZahl) {
					System.out.println("Meine Zahl ist groesser!");
					System.out.println(" ");
				}
			}

		}
	}
}
