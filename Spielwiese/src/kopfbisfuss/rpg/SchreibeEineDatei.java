package kopfbisfuss.rpg;

import java.io.FileWriter;
import java.io.IOException;

public class SchreibeEineDatei {

	public static void main(String[] args) {

		try {
			FileWriter writer = new FileWriter("duda.txt");

			writer.write("Hallo du da!");

			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
