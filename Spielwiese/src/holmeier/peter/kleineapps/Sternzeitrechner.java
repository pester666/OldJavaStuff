package holmeier.peter.kleineapps;

public class Sternzeitrechner {
	public static void main(String args[]) {

		double jahrakt = 2009.0;
		double monat = 10.0;
		double tagakt = 7.0;
		double tagemonat = 0;
		double uhr = 08.88;

		if (monat == 1.0)
			tagemonat = 31;
		if (monat == 2.0)
			tagemonat = 59;
		if (monat == 3.0)
			tagemonat = 90;
		if (monat == 4.0)
			tagemonat = 120;
		if (monat == 5.0)
			tagemonat = 151;
		if (monat == 6.0)
			tagemonat = 181;
		if (monat == 7.0)
			tagemonat = 212;
		if (monat == 8.0)
			tagemonat = 242;
		if (monat == 9.0)
			tagemonat = 273;
		if (monat == 10.0)
			tagemonat = 303;
		if (monat == 11.0)
			tagemonat = 334;
		if (monat == 12.0)
			tagemonat = 365;

		double tagen = tagakt + tagemonat;

		double jahre = jahrakt - 1111.0, tag = tagen;

		double tage = jahre * 365.0 + tag;
		// System.out.println(tage + " Tage bisher");

		double uhrzeit = 100.0 * uhr / 24.0;
		// System.out.println(uhrzeit + " Prozent des Tages, Nachkommastellen");

		double sternzeit = (float) uhrzeit / 100 + (float) tage;
		System.out.println("Galaktische Sternenzeit " + sternzeit);
	}
}
