package gui;

import java.util.Date;
@SuppressWarnings("unused")
public class KarteiFuerTest {

	
	private int id;
	private int phasenNummer = 0;
	private String frage;
	private String antwort;
	private Date erfassungsDatum;
	private Date abfrageDatum;
	private boolean beantwortet = false;
	
	public KarteiFuerTest(int id, String frage, String antwort,
			boolean beantwortet, Date erfassungsDatum) {
		super();
		this.id = id;
		this.frage = frage;
		this.antwort = antwort;
		this.setBeantwortet(beantwortet);
		this.erfassungsDatum = erfassungsDatum;
		abfrageDatum = getAbfrageDatum();
		phasenNummer = getPhasenNummer();
	}

	private int getPhasenNummer() {
		return 0;
	}

	private Date getAbfrageDatum() {
		return null;
	}

	private void setBeantwortet(boolean beantwortet) {
		this.beantwortet = beantwortet;
	}
	
}
