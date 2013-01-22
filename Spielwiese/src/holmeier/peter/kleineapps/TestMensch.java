package holmeier.peter.kleineapps;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class TestMensch {

	public static void main(String[] args) throws ParseException {

		String vorname, nachname;
		int alter;
		boolean geschlecht;
		Mensch person;

		System.out.println("Informationen für Person eingeben:");
		vorname = IOTools.readLine("Vorname eingeben:");
		nachname = IOTools.readLine("Nachname eingeben:");
		alter = IOTools.readInteger("Alter eingeben:");
		geschlecht = IOTools.readBoolean("Maennlich (true/false)");
		person = new Mensch(alter, geschlecht, vorname, nachname);

		System.out.println(person);

	}
}
