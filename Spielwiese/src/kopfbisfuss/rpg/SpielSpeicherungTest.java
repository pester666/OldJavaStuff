package kopfbisfuss.rpg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SpielSpeicherungTest {

	public static void main(String[] args) throws ClassNotFoundException {

		Spielfigur eins = new Spielfigur(50, "Elf", new String[] { "Bogen",
				"Speer", "Gleve" });
		Spielfigur zwei = new Spielfigur(200, "Troll", new String[] {
				"Steinkeule", "Kotze", "Klauen und Zähne" });
		Spielfigur drei = new Spielfigur(90, "Ork", new String[] { "Spalta",
				"Nochn Spalta" });
		Spielfigur vier = new Spielfigur(40, "Skelettkrieger",
				new String[] { "Schwert" });
		Spielfigur fünf = new Spielfigur(130, "Chaoskrieger", new String[] {
				"Axt", "Schwert", "Kriegshammer" });

		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream("Spiel.ser"));
			os.writeObject(eins);
			os.writeObject(zwei);
			os.writeObject(drei);
			os.writeObject(vier);
			os.writeObject(fünf);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		eins = null;
		zwei = null;
		drei = null;
		vier = null;
		fünf = null;

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"Spiel.ser"));
			Spielfigur einsWiederhergestellt = (Spielfigur) is.readObject();
			Spielfigur zweiWiederhergestellt = (Spielfigur) is.readObject();
			Spielfigur dreiWiederhergestellt = (Spielfigur) is.readObject();
			Spielfigur vierWiederhergestellt = (Spielfigur) is.readObject();
			Spielfigur fünfWiederhergestellt = (Spielfigur) is.readObject();
			is.close();

			System.out.println("Typ 1. Figur: "
					+ einsWiederhergestellt.getTyp());
			System.out.println("Typ 2. Figur: "
					+ zweiWiederhergestellt.getTyp());
			System.out.println("Typ 3. Figur: "
					+ dreiWiederhergestellt.getTyp());
			System.out.println("Typ 4. Figur: "
					+ vierWiederhergestellt.getTyp());
			System.out.println("Typ 5. Figur: "
					+ fünfWiederhergestellt.getTyp());

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
