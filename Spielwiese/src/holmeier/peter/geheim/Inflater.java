package holmeier.peter.geheim;

public class Inflater extends Encoder {

	public String encode(String s) {
		char[] c = s.toCharArray();
		String res = "";
		for (int i = 0; i < c.length - 1; i = i + 2) {
			char c1 = c[i];
			char c2 = c[i + 1];
			char c3 = (char) ('a' + 26 * Math.random());
			res = res + c2 + c3 + c1;
		}

		if (c.length % 2 != 0)
			res = res + c[c.length - 1];
		return res;
	}

	public String decode(String s) {
		char[] c = s.toCharArray();
		String res = "";
		for (int i = 0; i < c.length - 2; i = i + 3) {
			res = res + c[i + 2];
			res = res + c[i];
		}

		if (c.length % 3 != 0)
			res = res + c[c.length - 1];
		return res;
	}

}
