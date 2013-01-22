package kopfbisfuss;

class BankKonto {

	private int kontostand = 100;

	public int getKontostand() {
		return kontostand;
	}

	public void abbuchen(int betrag) {
		kontostand = kontostand - betrag;
	}
}

public class RainerUndMonikaJob implements Runnable {

	private BankKonto konto = new BankKonto();

	public static void main(String[] args) {

		RainerUndMonikaJob derJob = new RainerUndMonikaJob();
		Thread eins = new Thread(derJob);
		Thread zwei = new Thread(derJob);
		eins.setName("Rainer");
		zwei.setName("Monika");
		eins.start();
		zwei.start();
	}

	public void run() {
		for (int x = 0; x < 10; x++) {
			abhebungMachen(10);
			if (konto.getKontostand() < 0)
				System.out.println("Überzogen!");
		}
	}

	private synchronized void abhebungMachen(int betrag) {
		if (konto.getKontostand() >= betrag) {
			System.out.println(Thread.currentThread().getName()
					+ " ist im Begriff abzuheben.");
			try {
				System.out.println(Thread.currentThread().getName()
						+ " schläft ein.");
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()
					+ " ist aufgewacht.");
			konto.abbuchen(betrag);
			System.out.println(Thread.currentThread().getName()
					+ " führt die Abhebung zu Ende.");
		} else {
			System.out.println("Leider nicht genug Geld für "
					+ Thread.currentThread().getName());
		}
	}

}
