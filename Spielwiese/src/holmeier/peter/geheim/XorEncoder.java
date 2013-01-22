package holmeier.peter.geheim;

public class XorEncoder extends Encoder {

	private int key;

	public XorEncoder(int key) {
		this.key = key;
	}

	public String encode(String s) {
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++)
			c[i] = (char) (c[i] ^ key);
		return new String(c);
	}

	public String decode(String s) {
		return encode(s);
	}

}
