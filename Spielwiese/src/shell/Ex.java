package shell;

import java.io.IOException;

public class Ex {

	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();
		try {
			@SuppressWarnings("unused")
			Process pr = rt.exec("shutdown -i");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
