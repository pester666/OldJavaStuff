package kopfbisfuss;

public class TestSync {
	public static void main(String[] args) {
		TestSyncTest job = new TestSyncTest();
		Thread a = new Thread(job);
		Thread b = new Thread(job);
		a.start();
		b.start();
	}
}

class TestSyncTest implements Runnable {

	private int kontostand;

	public void run() {
		for (int i = 0; i < 50; i++) {
			inkrementieren();
			System.out.println("Kontostand beträgt " + kontostand);
		}
	}

	public synchronized void inkrementieren() {
		int i = kontostand;
		kontostand = i + 1;
	}
}
