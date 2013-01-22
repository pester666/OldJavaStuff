package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Das Spielpanel wird genutzt, um Abfragen durchzugehen, deren Phasen festzulegen sowie den Zeistempel zu vergeben
 * @author Pester
 *
 */
public class KartenSpielPanel{

	private static final Color BG_COLOR = Color.gray;
	private static final Color BTN_COLOR = Color.yellow;
	private static final Color BTN_PHASE_COLOR = Color.cyan;

	static private final Logger LOG =Logger.getLogger(KartenSpielPanel.class); 
	
	private MainframeSteuerung parent;
	private KartenSatzModel kartenSatzModel;

	// Model und Steuerung
	private boolean istAntwortAngezeigt = false;
	/** in dieser Liste stehen die falsch beantworteten Karten */
	private List<KarteiKarte> falscheListe;
	/** in dieser Liste stehen die in der Phase enthaltenen Karten */
	private List<KarteiKarte> phasenListe; 

	private int aktuelleKarteIndex = 0;
	private int falscherIndex = 0;
	private int randomIndex = 0;
	private int phasenListeIndex = 0;
	private boolean listeZuende = false;
	private boolean darfAbgefragtWerden = false;
		
	private int festgelegtePhase = 0;

	// gui
	private JPanel plCentral = new JPanel();
	private JButton btnRichtig = new JButton("Richtig");
	private JButton btnFalsch = new JButton("Falsch");
	private JButton btnKartei1 = new JButton("Phase 1");
	private JButton btnKartei2 = new JButton("Phase 2");
	private JButton btnKartei3 = new JButton("Phase 3");
	private JButton btnKartei4 = new JButton("Phase 4");
	private JButton btnKartei5 = new JButton("Phase 5");

	private JButton btnAufdecken = new JButton("Karte umdrehen");
	private JButton btnBearbeiten = new JButton("Karte Bearbeiten");

	private JPanel plButtonKasten;
	private JPanel plHeader;
	private JPanel plPhasen = new JPanel();
	private JLabel laKartei;
	private JLabel laKartenzahl;
	private JLabel laKartenzahlFalsch;
	private JLabel laKommandoZeile;
	private JLabel laAbfrageDatum;
	private JLabel laKartenZahlGesamt;
	private JPanel plKonsole = new JPanel();
	private JPanel plKartenzahlen = new JPanel();
	private GuiKartenSpielPanel pFrageAntwort;

	public KartenSpielPanel(MainframeSteuerung parent, KartenSatzModel model) {
		super();
		this.parent = parent;
		this.kartenSatzModel = model;
		falscheListe = new ArrayList<KarteiKarte>();
	}
	
	public JComponent guiKartenSpielPanel() {
		initGui();
		JPanel plRoot = new JPanel();

		plRoot.setBackground(BG_COLOR);
		plRoot.setLayout(new BoxLayout(plRoot, BoxLayout.X_AXIS));

		JPanel plContent = new JPanel();
		plContent.setBackground(BG_COLOR);
		plContent.setLayout(new BoxLayout(plContent, BoxLayout.Y_AXIS));
		plContent.add(plHeader);
		plContent.add(Box.createRigidArea(new Dimension(0, 5)));
		plContent.add(plCentral);
		plContent.add(Box.createRigidArea(new Dimension(0, 5)));
		plContent.add(plPhasen);
		plRoot.add(Box.createRigidArea(new Dimension(20, 0)));
		plRoot.add(plContent);
		plRoot.add(Box.createRigidArea(new Dimension(20, 0)));
		return plRoot;
	}

	public KartenSatzModel getKartensatzModel() {
		return kartenSatzModel;
	}

	public void setKartensatzModel(KartenSatzModel kartensatzModel) {
		this.kartenSatzModel = kartensatzModel;
	}

	private void initGui() {

		plButtonKasten = new JPanel(); // Der Kasten mit den vier Buttons in der
		// Mitte der GUI
		plButtonKasten.setLayout(new BoxLayout(plButtonKasten, BoxLayout.X_AXIS));
		btnRichtig.setEnabled(false);
		btnFalsch.setEnabled(false);
		btnBearbeiten.setEnabled(false);
		btnAufdecken.setEnabled(false);
		plButtonKasten.add(btnAufdecken);
		plButtonKasten.add(Box.createHorizontalGlue());
		plButtonKasten.add(btnRichtig);
		plButtonKasten.add(Box.createHorizontalGlue());
		plButtonKasten.add(btnFalsch);
		plButtonKasten.add(Box.createHorizontalGlue());
		plButtonKasten.add(btnBearbeiten);

		Font smallFont = new Font("sanserif", Font.BOLD, 16);

		plHeader = new JPanel();
		laKartei = new JLabel("K A R T E N A B F R A G E            ");

		laKartenzahl = new JLabel("Noch keine Kartei geladen");
		laKartenZahlGesamt = new JLabel(" ");
		laKartenzahlFalsch = new JLabel(" ");
		laKommandoZeile = new JLabel(" ");
		laAbfrageDatum = new JLabel (" ");
		plKonsole.setLayout(new BoxLayout(plKonsole, BoxLayout.X_AXIS));
		plKonsole.add(laAbfrageDatum);
		plKonsole.add(Box.createHorizontalGlue());
		plKonsole.add(laKommandoZeile);
		plKonsole.setBackground(BG_COLOR);
		plKartenzahlen.setLayout(new BoxLayout(plKartenzahlen, BoxLayout.Y_AXIS));
		plKartenzahlen.add(laKartenzahl);
		plKartenzahlen.add(laKartenzahlFalsch);
		plKartenzahlen.add(laKartenZahlGesamt);
		plHeader.setLayout(new BoxLayout(plHeader, BoxLayout.X_AXIS));
		plHeader.add(laKartei);
		plHeader.add(plKartenzahlen);
		
		btnAufdecken.setMnemonic(KeyEvent.VK_K);
		btnFalsch.setMnemonic(KeyEvent.VK_F);
		btnRichtig.setMnemonic(KeyEvent.VK_R);
		btnBearbeiten.setMnemonic(KeyEvent.VK_B);

		plPhasen.add(BorderLayout.CENTER, btnKartei1);
		plPhasen.add(BorderLayout.CENTER, btnKartei2);
		plPhasen.add(BorderLayout.CENTER, btnKartei3);
		plPhasen.add(BorderLayout.CENTER, btnKartei4);
		plPhasen.add(BorderLayout.CENTER, btnKartei5);

		laKartenzahl.setFont(smallFont);
		laKartenzahl.setForeground(Color.white);
		laKartenzahlFalsch.setFont(smallFont);
		laKartenzahlFalsch.setForeground(Color.white);
		laKartenZahlGesamt.setFont(smallFont);
		laKartenZahlGesamt.setForeground(Color.white);
		laKommandoZeile.setForeground(Color.white);
		laKommandoZeile.setFont(smallFont);
		laAbfrageDatum.setForeground(Color.white);
		laAbfrageDatum.setFont(smallFont);
		laKartei.setFont(smallFont);
		laKartei.setForeground(Color.white);
		laKartei.setBackground(BG_COLOR);
		plKartenzahlen.setBackground(BG_COLOR);

		plCentral.setBackground(BG_COLOR);
		plHeader.setBackground(BG_COLOR);
		laKartenzahl.setBackground(BG_COLOR);
		laKartenZahlGesamt.setBackground(BG_COLOR);
		plButtonKasten.setBackground(BG_COLOR);
		plPhasen.setBackground(BG_COLOR);
		btnRichtig.setBackground(Color.green);
		btnFalsch.setBackground(Color.red);

		pFrageAntwort = new GuiKartenSpielPanel(false, true);

		btnAufdecken.addActionListener(new NächsteKarteListener());
		btnFalsch.addActionListener(new NächsteKarteListenerFalsch());
		btnRichtig.addActionListener(new NächsteKarteListenerRichtig());
		btnBearbeiten.addActionListener(new BearbeitenListener());
		
		btnKartei1.addActionListener(new btnPhaseEinsListener());
		btnKartei2.addActionListener(new btnPhaseZweiListener());
		btnKartei3.addActionListener(new btnPhaseDreiListener());
		btnKartei4.addActionListener(new btnPhaseVierListener());
		btnKartei5.addActionListener(new btnPhaseFünfListener());
		
		btnKartei1.setBackground(BTN_COLOR);
		btnKartei2.setBackground(BTN_COLOR);
		btnKartei3.setBackground(BTN_COLOR);
		btnKartei4.setBackground(BTN_COLOR);
		btnKartei5.setBackground(BTN_COLOR);
		
		plCentral.setLayout(new BoxLayout(plCentral, BoxLayout.Y_AXIS));
		plCentral.add(pFrageAntwort.guiSpielPanel());
		plCentral.add(Box.createRigidArea(new Dimension(0, 5)));
		plCentral.add(plKonsole);
		plCentral.add(Box.createRigidArea(new Dimension(0, 5)));
		plCentral.add(plButtonKasten);
	}

	/**
	 * Inizialisiert die benötigen Einstellungen um mit den Abfragen beginnen zu können
	 */
	public void neuesSpiel() {
		if(kartenSatzModel.getKartenListe().size() != 0){
			parent.tabbedPaneAktivieren(2, false);
			laKartenZahlGesamt.setText("Karten in Lektion : " + kartenSatzModel.getKartenListe().size());
			btnAufdecken.setEnabled(false);
			phasenListe = new ArrayList<KarteiKarte>();
			phasenListe.clear();
			falscheListe.clear();
			phasenListeIndex = 0;
			falscherIndex = 0;
			aktuelleKarteIndex = 0;
			for (int i = 0, length = kartenSatzModel.getKartenListe().size(); i < length; i++){
				abfragenAbstand(festgelegtePhase, i);
				if((kartenSatzModel.getKartenListe().get(i).getPhasenNummer() == festgelegtePhase) && darfAbgefragtWerden == true){
					phasenListe.add(kartenSatzModel.getKartenListe().get(i));
				}
			}
			if(phasenListe.size() > 0){
				btnAufdecken.setEnabled(true);
				parent.tabbedPaneAktivieren(1, true);
				parent.setMenüPunktBearbeiten(true);
				LOG.info("Neue Abfrage gestartet");
				laKartenzahlFalsch.setText("Bisher 0 falsch beantwortet");
				laKartenzahl.setText("aktuelle Karte: " + 1 + " von "	+ phasenListe.size());
				nächsteKarteZeigen();
				pFrageAntwort.setAntwort("") ;
				btnBearbeiten.setEnabled(true);
				if(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getErfassungsDatum() != null)
					laKommandoZeile.setText("Erstelldatum: " + kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getErfassungsDatumString());
				else
					laKommandoZeile.setText("Karte hat kein Erstelldatum");
				listeZuende = false;
			}else{
				laKommandoZeile.setText("Keine Karte in dieser Phase");
				pFrageAntwort.setFrage("");
				pFrageAntwort.setAntwort("");
				laKartenzahlFalsch.setText(" ");
				laKartenzahl.setText("Keine Karte in dieser Phase");
				laAbfrageDatum.setText("Möglicherweise kein geeignetes Abfragedatum");
				darfAbgefragtWerden = false;
				btnBearbeiten.setEnabled(false);
				parent.tabbedPaneAktivieren(1, (true));
				parent.setMenüPunktBearbeiten(false);
			}	
		}else
			JOptionPane.showMessageDialog(plCentral, "Enthält keine verwendbaren Daten!", "Kartendatei ungültig", 0);
	}

	/**
	 * Wird von den drei Spielknöpfen verwendet. Zeigt entweder die Antwort oder die nächste Frage an. Falls keine Karte mehr vorhanden ist, wird auf die LetzteKarte-Methode zugegriffen
	 */
	private void nächsteKarteZeigen() { // wird von allen 3 Knöpfen verwendet ... zeigt sowohl Antwort als auch Frage an
	
		randomIndex = (int)(Math.random() * falscheListe.size());
		btnRichtig.setEnabled(false);
		btnFalsch.setEnabled(false);
		darfAbgefragtWerden = false;
				
		if (kartenSatzModel.getKartenListe().size() > aktuelleKarteIndex) {
			if(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex).getPhasenNummer() == festgelegtePhase){
				abfragenAbstand(festgelegtePhase, aktuelleKarteIndex);
				if(darfAbgefragtWerden == true){
					if (kartenSatzModel.getKartenListe().get(aktuelleKarteIndex).getBeantwortet() == false) {
						phasenListeIndex++;
						aktuelleKarteIndex++;
						pFrageAntwort.setFrage(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getFrage());
						btnAufdecken.setEnabled(true);
						istAntwortAngezeigt = true;
						if(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getErfassungsDatum() != null)
							laKommandoZeile.setText("Erstelldatum: " + kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getErfassungsDatumString());
						else
							laKommandoZeile.setText("Karte hat kein Erstelldatum");
						if(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getAbfrageDatum() != null)
							laAbfrageDatum.setText("Zuletzt Abgefragt: " + kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getAbfrageDatumString());
						else
							laAbfrageDatum.setText("Karte wurde nicht abgefragt");
					}	
				}else{
					aktuelleKarteIndex++;
					nächsteKarteZeigen();
				}						
			}else{
				aktuelleKarteIndex++;
				nächsteKarteZeigen();
			}
		} else if (falscheListe.size() <= falscherIndex && falscheListe.size() != 0) {
					pFrageAntwort.setFrage(falscheListe.get(randomIndex).getFrage());
					btnAufdecken.setEnabled(true);
					istAntwortAngezeigt = true;
					laKommandoZeile.setText("Noch " + falscheListe.size() + " falsche Karte(n)");
					listeZuende = true;
				}else { // keine naechste Karte da
					LOG.info("Letzte Karte erreicht");
					letzteKarte();
				}			
		darfAbgefragtWerden = false;
	}

	public KarteiKarte getAktuelleKarte() {
		if(phasenListe.size() != 0)
			return kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1);
		return null;
	}

	public class NächsteKarteListener implements ActionListener { // steht für den Aufdecken Knopf
		public void actionPerformed(ActionEvent ev) {
			if (istAntwortAngezeigt == true) {
				if (kartenSatzModel.getKartenListe().size() >= aktuelleKarteIndex && listeZuende == false) {
					pFrageAntwort.setAntwort(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getAntwort());
				}else if(falscheListe.size() <= falscherIndex && falscheListe.size() != 0)
					pFrageAntwort.setAntwort(falscheListe.get(randomIndex).getAntwort());
			istAntwortAngezeigt = false;
			btnAufdecken.setEnabled(false);
			btnRichtig.setEnabled(true);
			btnFalsch.setEnabled(true);
			}
		}
	}

	public class NächsteKarteListenerRichtig implements ActionListener { // der Richtig Knopf
		public void actionPerformed(ActionEvent ev) {

			String aktuellerKastenName = parent.currentKartensatzName();
			kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).setBeantwortet(true);
			
			if(phasenListe.size() >= 1)
				phasenListe.get(phasenListeIndex-1).setAbfrageDatum(new Date());
			if(listeZuende == false){
				kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).setPhasenNummer(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getPhasenNummer()+1);
				if (kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getPhasenNummer() >= 5)
					kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).setPhasenNummer(4);
			}
			if (aktuelleKarteIndex < phasenListe.size() || falscherIndex >= falscheListe.size()) {
				kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).setBeantwortet(true);
				updatePhaseUndZeitstempel(aktuellerKastenName);
				if(listeZuende == true)
					falscheListe.remove(falscheListe.get((randomIndex)));
				nächsteKarteZeigen();
				laKartenzahl.setText("aktuelle Karte: " + (phasenListe.indexOf(getAktuelleKarte())+1) + " von " + phasenListe.size());
				if(listeZuende == true)
					laKartenzahl.setText("aktuelle Karte: " + phasenListe.size() + " von " + phasenListe.size());
				if(istAntwortAngezeigt == true)
					pFrageAntwort.setAntwort("");
			}
		}
	}

	/**
	 * Setzt bei der Karte das Abfragedatum und aktualisert die Phasennummer
	 * @param kastenname : Der Kastenname
	 */
	private void updatePhaseUndZeitstempel(String kastenname) { // braucht man für den Abfragen Zeitstempel	
		try {
			KarteikastenDAO dao=new DAOFactory().getKarteikastenDAO();
			dao.karteSpeichern(aktuelleKarteIndex-1, getAktuelleKarte(), kastenname, false, false);
		} catch (Exception ex) {
			LOG.error("Konnte die Kartenliste nicht schreiben");
			ex.printStackTrace();
		}
	}
	
	/**
	 * Falls die beantwortete Karte die letzte war, werden diverse Einstellungen gemacht um fortfahren zu können.
	 */
	private void letzteKarte() {
		laKommandoZeile.setText("Das war die letzte Karte");
		btnAufdecken.setEnabled(false);
		btnRichtig.setEnabled(false);
		btnFalsch.setEnabled(false);
		btnBearbeiten.setEnabled(false);
		listeZuende = true;
		parent.tabbedPaneAktivieren(2, true);
	}

	public class NächsteKarteListenerFalsch implements ActionListener { // der Falsch Knopf
		public void actionPerformed(ActionEvent ev) {

			String aktuellerKastenname = parent.currentKartensatzName();
			kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).setBeantwortet(false);

			if(phasenListe.size() >= 1)
				phasenListe.get(phasenListeIndex-1).setAbfrageDatum(new Date());
			else
				phasenListe.get(phasenListeIndex).setAbfrageDatum(new Date());
			
			if (aktuelleKarteIndex < phasenListe.size() || falscherIndex >= falscheListe.size()) {
				if(listeZuende == false){
					falscheListe.add(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1));
					kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).setPhasenNummer(0);
				}
				if(listeZuende == true){
					falscheListe.add(falscheListe.get(randomIndex));
					falscheListe.remove(falscheListe.get((randomIndex)));
				}
				updatePhaseUndZeitstempel(aktuellerKastenname);
				falscherIndex += 1;
				nächsteKarteZeigen();
				laKartenzahl.setText("aktuelle Karte: " + (phasenListe.indexOf(getAktuelleKarte())+1) + " von " + phasenListe.size());
				if(listeZuende == true)
					laKartenzahl.setText("aktuelle Karte: " + phasenListe.size() + " von " + phasenListe.size());
				pFrageAntwort.setAntwort("");
				laKartenzahlFalsch.setText("Bisher " + falscherIndex + " falsch beantwortet");	
			}
		}
	}

	public class BearbeitenListener implements ActionListener { // der Bearbeiten Knopf
		public void actionPerformed(ActionEvent ev) {
			if(kartenSatzModel.getKartenListe().size() >= aktuelleKarteIndex && listeZuende == false)
				parent.activateKartenEditorPanel(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1));
			else
				laKommandoZeile.setText("Karte nicht original!");
		}
	}

	/**
	 * Die zuvor angezeigte Karte wird wiederhergestellt.
	 */
	public void reloadKarte() { // wenn man die Karte wiederherstellen will
		if(parent.currentKartensatzName() != null){
//			if(phasenListe.size() > 0)
//				abfragenAbstand(festgelegtePhase, aktuelleKarteIndex-1);
//			else
//				btnAufdecken.setEnabled(false);
//			if(darfAbgefragtWerden == true){
				this.pFrageAntwort.setFrage(kartenSatzModel.getKartenListe().get(aktuelleKarteIndex-1).getFrage());
				laKartenzahl.setText("aktuelle Karte: " + (phasenListe.indexOf(getAktuelleKarte())+1) + " von " + phasenListe.size());
//			}	
			this.pFrageAntwort.setAntwort("");
			istAntwortAngezeigt = true;
			listeZuende = false;
		}
	}
	
	/**
	 * Setzt den Aufdeckenbutton auf Aktiviert und den Richtig- sowie den Falschbutton auf deaktiviert
	 */
	public void resetButtons(){
		btnAufdecken.setEnabled(true);
		btnRichtig.setEnabled(false);
		btnFalsch.setEnabled(false);
	}
	
	/**
	 * Setzt die Hintergrundfarbe der Phasenknöpfe auf den Default-Wert zurück
	 */
	public void btnFarbeWiederherstellen(){
		btnKartei1.setBackground(BTN_COLOR);
		btnKartei2.setBackground(BTN_COLOR);
		btnKartei3.setBackground(BTN_COLOR);
		btnKartei4.setBackground(BTN_COLOR);
		btnKartei5.setBackground(BTN_COLOR);
	}
	
	public class btnPhaseEinsListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			festgelegtePhase = kartenSatzModel.phasenNummer(0);
			btnFarbeWiederherstellen();
			btnKartei1.setBackground(BTN_PHASE_COLOR);
			if(kartenSatzModel.getKartenListe().size() != 0)
			neuesSpiel();
		}
	}
	
	public class btnPhaseZweiListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			festgelegtePhase = kartenSatzModel.phasenNummer(1);
			btnFarbeWiederherstellen();
			btnKartei2.setBackground(BTN_PHASE_COLOR);
			if(kartenSatzModel.getKartenListe().size() != 0)
			neuesSpiel();
		}
	}
	
	public class btnPhaseDreiListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			festgelegtePhase = kartenSatzModel.phasenNummer(2);
			btnFarbeWiederherstellen();
			btnKartei3.setBackground(BTN_PHASE_COLOR);
			if(kartenSatzModel.getKartenListe().size() != 0)
			neuesSpiel();
		}
	}
	
	public class btnPhaseVierListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			festgelegtePhase = kartenSatzModel.phasenNummer(3);
			btnFarbeWiederherstellen();
			btnKartei4.setBackground(BTN_PHASE_COLOR);
			if(kartenSatzModel.getKartenListe().size() != 0)
			neuesSpiel();
		}
	}
	
	public class btnPhaseFünfListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			festgelegtePhase = kartenSatzModel.phasenNummer(4);
			btnFarbeWiederherstellen();
			btnKartei5.setBackground(BTN_PHASE_COLOR);
			if(kartenSatzModel.getKartenListe().size() != 0)
			neuesSpiel();
		}
	}

	/**
	 * Setzt das Frage und Antwort Textfenster leer, löscht die Kartenliste, deaktiviert die Abfrageknöpfe und setzt sämtliche Labels auf den Defaultwert zurück.
	 */
	public void resetAll(){
		pFrageAntwort.setFrage(" ");
		pFrageAntwort.setAntwort(" ");
		kartenSatzModel.getKartenListe().clear();
		btnAufdecken.setEnabled(false);
		btnRichtig.setEnabled(false);
		btnFalsch.setEnabled(false);
		laKommandoZeile.setText(" ");
		laAbfrageDatum.setText(" ");
		laKartenzahl.setText("Noch keine Kartei geladen");
		laKartenzahlFalsch.setText(" ");
		laKartenZahlGesamt.setText(" ");
	}
	
	public int getAktuelleKarteIndex() {
		return aktuelleKarteIndex;
	}

	public int getFalscherIndex() {
		return falscherIndex;
	}

	public boolean isListeZuende() {
		return listeZuende;
	}

	public List<KarteiKarte> getPhasenListe() {
		return phasenListe;
	}

	public void setPhasenListe(List<KarteiKarte> phasenListe) {
		this.phasenListe = phasenListe;
	}
	
	/**
	 * Legt fest, wie groß der zeitliche Abstand der Karten sein muss, um erneut, Phasenspezifisch, Abgefragt zu werden.
	 * @param phase : Legt die Ausgewählte Phase fest
	 * @param aktuelleKarte : Der Index der aktuellen Karte in der Liste
	 */
	public void abfragenAbstand(int phase, int aktuelleKarte){
		if(kartenSatzModel.getKartenListe().get(aktuelleKarte).getAbfrageDatum() != null){
			long currentTime = new Date().getTime();
			long cardTime = kartenSatzModel.getKartenListe().get(aktuelleKarte).getAbfrageDatum().getTime();
			darfAbgefragtWerden = false;
			
			switch (phase) {
				case 0:
						if((currentTime-cardTime) >= 3500){
							darfAbgefragtWerden = true;
							listeZuende = false;
						}	
						break;
				case 1:
						if((currentTime-cardTime) >= 7500){
							darfAbgefragtWerden = true;
							listeZuende = false;
						}	
						break;
				case 2:
						if((currentTime-cardTime) >= 15000){
							darfAbgefragtWerden = true;
							listeZuende = false;
						}	
						break;
				case 3:
						if((currentTime-cardTime) >= 30000){
							darfAbgefragtWerden = true;
							listeZuende = false;
						}	
						break;
				case 4:
						if((currentTime-cardTime) >= 60000){
							darfAbgefragtWerden = true;
							listeZuende = false;
						}	
						break;
				default:
						break;
			}
		}else{
			darfAbgefragtWerden = true;
			listeZuende = false;
		}	
	}
	
	/**
	 * Betätigt den Button der Phase 1
	 */
	public void clickBtnKartei(){
		btnKartei1.doClick();
	}
}