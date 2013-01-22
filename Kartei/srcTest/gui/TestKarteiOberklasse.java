package gui;


import java.util.Date;

public interface TestKarteiOberklasse {
	
	static final KarteiKarte KARTE_1 = new KarteiKarte(1, "erste", "Karte", false, new Date());
	static final KarteiKarte KARTE_2 = new KarteiKarte(2, "Zweite", "Karte", false, new Date());
	KarteikastenDAOXStreamImpl karte = new KarteikastenDAOXStreamImpl();
}
