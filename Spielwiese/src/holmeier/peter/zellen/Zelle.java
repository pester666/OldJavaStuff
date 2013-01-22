package holmeier.peter.zellen;

public class Zelle {

	private boolean lebendig;

	public boolean istLebendig() {
		return lebendig;
	}

	public Zelle(boolean istLebendig) {
		lebendig = istLebendig;
	}

	public Zelle(Zelle alt, int zahlDerLebendenNachbarn) {
		switch (zahlDerLebendenNachbarn) {
		case 2:
			lebendig = alt.lebendig;
			break;
		case 3:
			lebendig = true;
			break;
		default:
			lebendig = false;
			break;
		}
	}

}
