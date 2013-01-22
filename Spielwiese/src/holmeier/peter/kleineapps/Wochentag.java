package holmeier.peter.kleineapps;

public class Wochentag {
	public static void main(String[] args) {

		int t = 22;
		int m = 6;
		int j = 1959;
		int h = 0;

		int monat = m;
		int jahr = j;
		int tage = t;

		if (m <= 2) {
			m += 10;
			j -= 1;

		} else {
			m -= 2;
		}

		int c = j / 100;
		int y = j % 100;

		h = (((26 * m - 2) / 10) + t + y + y / 4 + c / 4 - 2 * c) % 7;

		String tag = "bla";

		if (h < 0) {
			h += 7;

		}
		if (h == 1) {
			tag = "Montag";
		}

		if (h == 2) {
			tag = "Dienstag";
		}
		if (h == 3) {
			tag = "Mittwoch";
		}
		if (h == 4) {
			tag = "Donnerstag";
		}
		if (h == 5) {
			tag = "Freitag";
		}
		if (h == 6) {
			tag = "Samstag";
		}
		if (h == 0) {
			tag = "Sonntag";
		}
		System.out.println("Der " + tage + "." + monat + "." + jahr
				+ " ist ein " + tag + ".");

	}
}
