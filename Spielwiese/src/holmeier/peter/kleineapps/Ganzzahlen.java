package holmeier.peter.kleineapps;

public class Ganzzahlen {

	private static int d;
	private static int o, h;

	public int getD() {
		return d;
	}

	public void setD(int n) {
		d = n;
	}

	public int getO() {
		return o;
	}

	public void setO(int n) {
		o = n;
	}

	public int getH() {
		return h;
	}

	public void setH(int n) {
		h = n;
	}

	public static void main(String[] args) {
		// int d=10;
		// int o=010;
		// int h=0x10;

		Ganzzahlen rechner = new Ganzzahlen();
		rechner.setD(10);
		rechner.setO(0x10);
		rechner.setH(010);

		// geht nicht, da 9 keiene gueltige octal-Ziffer ist: int o1=09;
		// int h2= 0x12FF;
		System.out.println("d: " + d);
		System.out.println("o: " + o);
		System.out.println("h: " + h);
		System.out.println("2 durch 3: " + 2 / 3 + " Rest " + 2 % 3);

		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) {
				System.out.println("i=" + i + " (gerade)");
			} else {
				System.out.println("i=" + i + " (ungerade)");
			}
		}

		// praeincrement und postincrement
		int pre = 0;
		int post = 0;
		System.out.println("Preincrement " + (++pre));
		System.out.println("Postincrement " + (post++));

		// long l=123456789089L;
		// long k=0L;
	}
}
