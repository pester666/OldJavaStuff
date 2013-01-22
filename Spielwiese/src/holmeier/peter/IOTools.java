package holmeier.peter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Formatter;

public class IOTools {

	static public String readLine(String anzeigetext) {
		System.out.println(anzeigetext);
		String ret = null;
		try {
			// System.out.println(ret.length());
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			ret = br.readLine();

		} catch (IOException e) {
			// ist hier mal egal
			e.printStackTrace();
		}
		return ret;
	}

	static public char readChar(String anzeigetext) {
		System.out.println(anzeigetext);
		char ret = ' ';
		try {
			// System.out.println(ret.length());
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			ret = (char) br.read();

		} catch (IOException e) {
			// ist hier mal egal
			e.printStackTrace();
		}
		return ret;
	}

	static public int readInteger(String bla) throws ParseException {
		System.out.println(bla);
		int numberFromConsole;
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine();
			DecimalFormat df = new DecimalFormat();
			Number n = df.parse(s);
			numberFromConsole = n.intValue();
		} catch (IOException e) {
			numberFromConsole = 0;
		}
		return numberFromConsole;
	}

	static public boolean readBoolean(String bla) {
		System.out.println(bla);
		boolean numberFromConsole = false;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			String s = br.readLine();
			int position = s.indexOf("1");
			if (position >= 0) {
				System.out.println("1 gefunden an Position: " + position);
				numberFromConsole = true;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}

		return numberFromConsole;
	}

	static public double readDouble(String bla) throws ParseException {
		System.out.println(bla);
		double numberFromConsole;
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine();
			DecimalFormat df = new DecimalFormat();
			Number n = df.parse(s);
			numberFromConsole = n.doubleValue();
		} catch (IOException e) {
			numberFromConsole = 0;
		}
		return numberFromConsole;
	}

	public static void main(String[] args) throws IOException, ParseException {

		int u = 5;

		StringBuilder sb = new StringBuilder("A");
		sb.append(5);

		Formatter formatter = new Formatter(sb);
		formatter.format("%1$25s: %2$06d", "Total Requests", u);
		sb.append("\r\n");
		System.out.println(sb);

	}

	public static int getIntChecked(String prompt) {
		boolean inputWrong = false;
		int ret = 0;
		do {
			try {
				ret = readInteger(prompt);
				inputWrong = false;
			} catch (ParseException e) {
				inputWrong = true;
			}
		} while (inputWrong);
		return ret;

	}

}
