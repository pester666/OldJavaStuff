package gui;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KarteiKarte {

	private static final SimpleDateFormat DF_TT_MM_JJJJ_TIME = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm");

	/** Eindeutige ID der Karte im Kasten **/
	private int id;
	private int phasenNummer;
	private String frage;
	private String antwort;
	private Date erfassungsDatum;
	private Date abfrageDatum;

	private transient boolean beantwortet = false;

	public String getFrage() {
		return this.frage;
	}

	/**
	 * Die KarteiKarte stellt das Objekt dar, welches alle benötigten
	 * Informationen zum Abfragen enthält
	 * 
	 * @param id
	 *            : Die ID der Karte. Sie ist einmalig.
	 * @param frage
	 *            : Der String der die Frage darstellt.
	 * @param antwort
	 *            : Der String der die Antwort darstellt.
	 * @param beantwortet
	 *            : Gibt an, ob die Karte bereits Beantwortet wurde oder nicht.
	 * @param erfassungsDatum
	 *            Zeigt das Datum der Erstellung der Karte an.
	 */
	public KarteiKarte(int id, String frage, String antwort,
			boolean beantwortet, Date erfassungsDatum) {
		super();
		this.id = id;
		this.frage = frage;
		this.antwort = antwort;
		this.setBeantwortet(beantwortet);
		this.erfassungsDatum = erfassungsDatum;
		this.phasenNummer = 0;
	}

	public String getAntwort() {
		return this.antwort;
	}

	public boolean getBeantwortet() {
		return false;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Frage: ");
		sb.append(frage == null ? "[null]" : frage);
		sb.append(", Antwort: ");
		sb.append(antwort == null ? "[null]" : antwort);
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!o.getClass().equals(getClass()))
			return false;
		KarteiKarte that = (KarteiKarte) o;
		if (id == that.id && phasenNummer == that.phasenNummer) {
			if (objectEqual(frage, that.frage)
					&& objectEqual(antwort, that.antwort)
					&& objectEqual(erfassungsDatum, that.erfassungsDatum)
					&& objectEqual(abfrageDatum, that.abfrageDatum)) {
				return true;
			}
		}
		return false;
	}

	private boolean objectEqual(Object s0, Object s1) {
		if (s0 == null) {
			return s1 == null;
		} else {
			if (s1 == null)
				return false;
			return s0.equals(s1);
		}
	}

	@Override
	public int hashCode() {
		// zur berechnung des Hashcodes alle Felder berücksichtigen,
		// die auch für equals verwendet werden
		return id + phasenNummer + hashCodeForObject(frage)
				+ hashCodeForObject(antwort) + hashCodeForObject(abfrageDatum)
				+ hashCodeForObject(erfassungsDatum);
	}

	private int hashCodeForObject(Object o) {
		if (o == null)
			return 0;
		return o.hashCode();
	}

	public void setBeantwortet(boolean beantwortet) {
		this.beantwortet = beantwortet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFrage(String frage) {
		this.frage = frage;
	}

	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}

	public Date getErfassungsDatum() {
		return erfassungsDatum;
	}

	public String getErfassungsDatumString() {
		return DF_TT_MM_JJJJ_TIME.format(erfassungsDatum);
	}

	public void setErfassungsDatum(Date erfassungsDatum) {
		this.erfassungsDatum = erfassungsDatum;
	}

	public Date getAbfrageDatum() {
		return abfrageDatum;
	}

	public String getAbfrageDatumString() {
		return DF_TT_MM_JJJJ_TIME.format(abfrageDatum);
	}

	public void setAbfrageDatum(Date abfrageDatum) {
		this.abfrageDatum = abfrageDatum;
	}

	public int getPhasenNummer() {
		return phasenNummer;
	}

	public void setPhasenNummer(int phasenNummer) {
		this.phasenNummer = phasenNummer;
	}

	/**
	 * Alle Felder ausser der ID aus der hereingereichten Karte uebernehmen.
	 * 
	 * @param k
	 *            : Updatet die Karte in der Lektion mit der eingereichten Karte.
	 */
	public void update(KarteiKarte k) {
		this.abfrageDatum = k.abfrageDatum;
		this.antwort = k.antwort;
		this.beantwortet = k.beantwortet;
		this.erfassungsDatum = k.erfassungsDatum;
		this.frage = k.frage;
		this.phasenNummer = k.phasenNummer;
	}
}
