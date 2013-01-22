package gui;

import gui.KartenEditorPanel.Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import pdf.Launcher;
import pdf.PdfKarte;

public class MAINMETHODE implements MainframeSteuerung, KartenSatzModel {

	static private final Logger LOG =Logger.getLogger(MAINMETHODE.class); 
	// gui-Elemente
	private JFrame frame = new JFrame("Karteikasten");
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JSplitPane splitPane;
	private JMenuItem menüPunktBearbeiten;
	private KartenEditorPanel pKartenEditor;
	private KartenSpielPanel pKartenSpielPanel;
	private Statistik pStatistikPanel;
	private PdfKarte pPdfKarte;
	private Launcher pPdfLauncher;
	private KartensatzAuswahl kartensatzAuswahl;
	// model + Steuerung
	private List<KarteiKarte> kartenListe;
	private String currentKartensatzName = null;
	private int phasenNrAusgewählt = -1;
	
	public MAINMETHODE() {
		kartenListe = new ArrayList<KarteiKarte>();
	}

	public static void main(String[] args) {
		MAINMETHODE main = new MAINMETHODE();
		main.initGui();
	}

	public void initGui() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menüLeiste = new JMenuBar();
		JMenu menüDatei = new JMenu("Datei");
		JMenuItem menüPunktBeenden = new JMenuItem("Beenden");
		JMenu subMenüPunktNeue = new JMenu("Erstellen");
		JMenuItem menüPunktNeueKarte = new JMenuItem("Neue Karte");
		JMenuItem menüPunktStatistik = new JMenuItem("Statistik");
		JMenuItem menüPunktDrucken = new JMenuItem("Drucken");
		JMenuItem menüPunktNeueLektion = new JMenuItem("Neue Lektion");
		menüPunktBearbeiten = new JMenuItem("Karte bearbeiten");

		menüPunktBeenden.addActionListener(new MenüBeendenListener());
		menüPunktNeueKarte.addActionListener(new MenüNeueKarteListener());
		menüPunktNeueLektion.addActionListener(new MenüNeueLektionListener());
		menüPunktBearbeiten.addActionListener(new MenüBearbeitenListener());
		menüPunktStatistik.addActionListener(new MenüStatistikListener());
		menüPunktDrucken.addActionListener(new MenüDruckenListener());
		
		subMenüPunktNeue.setMnemonic(KeyEvent.VK_H);
		menüPunktStatistik.setMnemonic(KeyEvent.VK_S);
		menüPunktBeenden.setMnemonic(KeyEvent.VK_B);
		menüPunktDrucken.setMnemonic(KeyEvent.VK_D);
		
		menüPunktBearbeiten.setEnabled(false);
		
		subMenüPunktNeue.add(menüPunktNeueLektion);
		subMenüPunktNeue.add(menüPunktNeueKarte);

		menüDatei.add(subMenüPunktNeue);
		menüDatei.add(menüPunktBearbeiten);
		menüDatei.add(menüPunktDrucken);
		menüDatei.add(menüPunktStatistik);
		menüDatei.add(menüPunktBeenden);
		menüDatei.setMnemonic(KeyEvent.VK_D);
		
		menüLeiste.add(menüDatei);
		frame.setJMenuBar(menüLeiste);

		this.pKartenSpielPanel = new KartenSpielPanel(this, this);
		this.pKartenEditor = new KartenEditorPanel(true, this, this);
		this.pStatistikPanel = new Statistik();
		pPdfLauncher = new Launcher();
		pPdfKarte = new PdfKarte();
		try {
			kartensatzAuswahl = new KartensatzAuswahl(this);
		} catch (Exception e) {
			LOG.error("Beim Laden der Lektionsliste ist ein Fehler aufgetreten: ", e);
			JOptionPane.showMessageDialog(frame, "Beim Laden der Lektionsliste ist ein Fehler aufgetreten!\nAnwendung wird beendet!", "Warnung", 0);
			System.exit(1);
		}
		tabbedPane.addTab("Spielen", pKartenSpielPanel.guiKartenSpielPanel());
		tabbedPane.addTab("Bearbeiten", pKartenEditor.guiKartenEditorPanel());
		tabbedPane.addTab("Statistik", pStatistikPanel.guiStatistik());
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.addChangeListener(new tabUmschaltenListener());

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, kartensatzAuswahl.getSplitPane());
		
        frame.add(splitPane);
        
	    pKartenSpielPanel.clickBtnKartei();
        
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		pStatistikPanel.guiStatistik();
	}

	public class MenüBeendenListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			System.exit(1);
		}
	}
	
	/**
	 * Schaut, ob eine Phase gewählt ist und setzt einen neuen Kartensatz sowie den Name des aktuelle Kastens. Außerdem trifft es alle Vorbereitungen zum Start der Abfrage.
	 */
	public void ladenAusführen(){
		if(phasenNrAusgewählt >= 0 && phasenNrAusgewählt <= 4)
			try {
				MAINMETHODE.this.currentKartensatzName = kartensatzAuswahl.getAktuellerKasten();
				neuerKartensatz(kartenLaden(kartensatzAuswahl.getAktuellerKasten()));	
				tabbedPane.setSelectedIndex(0);
				pKartenEditor.getEditorKartenListe().clear();
				pKartenSpielPanel.setPhasenListe(getKartenListe());
				pKartenSpielPanel.neuesSpiel();
				pKartenEditor.btnAbfrage.setEnabled(true);
				pKartenEditor.btnEigeneDruckListe.setEnabled(true);
				pKartenEditor.setKartensatzGeladen(true);
			} catch (Exception e) {
				LOG.error("Fehler beim parsen: ", e);
				JOptionPane.showMessageDialog(frame, "Ein Fehler ist beim Laden der Kartei aufgetreten!", "Warnung", 0);
			}	
	}

	private List<KarteiKarte> kartenLaden(String kastenname) throws Exception{
			KarteikastenDAO dao=new DAOFactory().getKarteikastenDAO();
			return dao.kartenLaden(kastenname);
	}
	
	/**
	 * Setzt die neue Kartenliste mit der hereingereichten gleich
	 * @param kartensatz : Die Kartensatzliste
	 */
	private void neuerKartensatz(List<KarteiKarte> kartensatz) throws Exception{
		this.kartenListe = kartensatz;
		KarteikastenDAO dao = new DAOFactory().getKarteikastenDAO();
		dao.nextKartenID(kartensatzAuswahl.getAktuellerKasten());
	} 

	/**
	 * Wenn eine Lektion hinzugefügt oder gelöscht wird, wird die Liste aktualisiert
	 */
	public void listeAktualisieren(){
		try {
			kartensatzAuswahl.listeAktualisieren();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Ein Fehler beim aktualisieren der Lektionsliste aufgetreten!", "Warnung", 0);
			LOG.error("Ein Fehler beim aktualisieren der Lektionsliste aufgetreten: ", e);
		}
	}
	
	public class MenüNeueLektionListener implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			tabbedPane.setSelectedIndex(1);
			pKartenEditor.setGespeichert(false);
			pKartenEditor.setMode(Mode.neu);
			pKartenEditor.setFrage("");
			pKartenEditor.setAntwort("");
			pKartenEditor.laKommandoZeile.setText("Lektion hinzufügen");
			pKartenEditor.taFrage.grabFocus();
			pKartenEditor.karteHinzufügen();
			currentKartensatzName = null;
			pKartenEditor.btnNeueLektion.setEnabled(false);
		}
	}
	
	public class MenüNeueKarteListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			tabbedPane.setSelectedIndex(1);
			pKartenEditor.setGespeichert(false);
			pKartenEditor.setMode(Mode.neu);
			pKartenEditor.setFrage("");
			pKartenEditor.setAntwort("");
			pKartenEditor.laKommandoZeile.setText("Karte hinzufügen");
			pKartenEditor.taFrage.grabFocus();
			pKartenEditor.karteHinzufügen();
		}
	}
	
	/**
	 * Aktiviert das Karteneditorpanel und setzt sämtliche Bedingungen
	 */
	@Override
	public void activateKartenEditorPanel(KarteiKarte karte) {
		this.tabbedPane.setSelectedIndex(1);
		pKartenEditor.taFrage.requestFocus();
		if(pKartenSpielPanel.getPhasenListe().size() != 0){
			pKartenEditor.setAktuelleKarte(karte);
			pKartenEditor.setEditorKartenListe(kartenListe, pKartenSpielPanel.getAktuelleKarteIndex());
		}else{
			pKartenEditor.setAktuelleKarte(kartenListe.get(0));
			pKartenEditor.setEditorKartenListe(kartenListe, (pKartenSpielPanel.getAktuelleKarteIndex()+1));
		}
		pKartenEditor.btnSpeichern.setEnabled(true);
		pKartenEditor.setGespeichert(false);
		pKartenEditor.setMode(Mode.bearbeiten);
	}

	public void setMenüPunktBearbeiten(boolean enabled) {
		menüPunktBearbeiten.setEnabled(enabled);
	}

	/**
	 * Das Spielpanel wird aktiviert und die zuvor angezeigte Karte wird wiederhergestellt
	 */
	@Override
	public void activateSpielPanel() {	
		this.tabbedPane.setSelectedIndex(0);
		this.pKartenSpielPanel.reloadKarte();
	}

	@Override
	public List<KarteiKarte> getKartenListe() {
		return this.kartenListe;
	}
	
	public int phasenNummer(int phase){
		return this.phasenNrAusgewählt = phase;
	}

	public class MenüBearbeitenListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			activateKartenEditorPanel(MAINMETHODE.this.pKartenSpielPanel.getAktuelleKarte());
		}
	}

	@Override
	public String currentKartensatzName() {
		return currentKartensatzName;
	}

	@Override
	public void setCurrentKartensatzName(String kastenname) {
		this.currentKartensatzName = kastenname;
	}
	
	public void tabbedPaneAktivieren(int atInedx, boolean isEnabled){
		tabbedPane.setEnabledAt(atInedx, isEnabled);
	}
	
	public List<String> getListModelElements(){
		return kartensatzAuswahl.getListModel();
	}
	
	public class tabUmschaltenListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			if(tabbedPane.getSelectedIndex() == 2 && pKartenSpielPanel.isListeZuende() == true){
				int durchschnitt = 0;
				durchschnitt = 100 * pKartenSpielPanel.getFalscherIndex() / pKartenSpielPanel.getPhasenListe().size();
				pStatistikPanel.setText("Sie haben " + pKartenSpielPanel.getFalscherIndex() + " von " + pKartenSpielPanel.getPhasenListe().size() + " Karten falsch beantwortet. Das entspricht einer Fehlerquote von " + durchschnitt + "%.");
				tabbedPane.setSelectedIndex(2);
			}else
			if(tabbedPane.getSelectedIndex() == 1 && pKartenEditor.getKartensatzGeladen() == true)
				activateKartenEditorPanel(MAINMETHODE.this.pKartenSpielPanel.getAktuelleKarte());
			else if(pKartenEditor.getKartensatzGeladen() == false){
					pKartenEditor.setFrage("");
					pKartenEditor.setAntwort("");
				}else{	
					activateSpielPanel();
					pKartenSpielPanel.resetButtons();
				}
		}
	}
	
	public void statistikAktivieren(){
		tabbedPane.setEnabledAt(2, pKartenSpielPanel.isListeZuende());
	}
	
	public class MenüStatistikListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			if(pKartenSpielPanel.getKartensatzModel().getKartenListe().size() != 0){
				if(pKartenSpielPanel.isListeZuende() == true){
					tabbedPane.setSelectedIndex(2);
				}else
					JOptionPane.showMessageDialog(frame, "Sie haben noch nicht alle Fragen beantwortet!", "Funktion nicht möglich", 2);
			}else
				JOptionPane.showMessageDialog(frame, "Es gibt noch keine Statistik!", "Funktion nicht möglich",1);
		}
	}
	
	public class phasenWahlListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
	        JComboBox cb = (JComboBox)ev.getSource();
	        phasenNrAusgewählt = cb.getSelectedIndex();
		}
	}
	
	/**
	 * Setzt die GUI auf alle Default-Werte zurück und wechselt auf das Spielpanel
	 */
	public void resetKartensatz(){
		pKartenSpielPanel.resetAll();
		tabbedPane.setSelectedIndex(0);
	}
	
	public class MenüDruckenListener implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent ev){
			
			if(pKartenEditor.getKartensatzGeladen() == true){
				
				pPdfKarte.resetTables();
				int option = 0;
				
				Object[] druckOptionen = { "Alle Karten", "Eigene Liste", "Karten in Phase", "Karten der Suche", "Karten nach Datum"};
				String optionLänge = (String)JOptionPane.showInputDialog(frame, "Wählen Sie:\n" + "Optionen", "Drucken in PDF-Format", JOptionPane.PLAIN_MESSAGE, null, druckOptionen, "ham");
				
				if ((optionLänge != null) && (optionLänge.length() > 0)){
				    option = optionLänge.length();
						
					switch (option) {
					case 11: // Alle Karten drucken
						LOG.info("Alle Karten werden gedruckt");
						if(kartenListe.size() > 0){
							for (int i = 0, length = kartenListe.size(); i < length; i++) {
								pPdfKarte.setTableFrage(kartenListe.get(i).getFrage());
							}
							for (int i = 0, length = kartenListe.size(); i < length; i++) {
								pPdfKarte.setListeAntwort(kartenListe.get(i).getAntwort());
							}
							pPdfKarte.dokumentErstellen("pdf/fragen.pdf", true, kartenListe.size());
							pPdfLauncher.openUrl("pdf/fragen.pdf");
						}else{
							JOptionPane.showMessageDialog(frame, "Sie haben keine aktuellen Karten zum Drucken!", "Funktion nicht möglich", 2);
						}
						break;
					case 12:
						LOG.info("Karten der eigenen Liste werden gedruckt");
						if(pKartenEditor.getEigeneDruckListe().size() > 0){
							for (int i = 0, length = pKartenEditor.getEigeneDruckListe().size(); i < length; i++) {
								pPdfKarte.setTableFrage(pKartenEditor.getEigeneDruckListe().get(i).getFrage());
							}
							for (int i = 0, length = pKartenEditor.getEigeneDruckListe().size(); i < length; i++) {
								pPdfKarte.setListeAntwort(pKartenEditor.getEigeneDruckListe().get(i).getAntwort());
							}
							pPdfKarte.dokumentErstellen("pdf/fragen.pdf", true, pKartenEditor.getEigeneDruckListe().size());
							pPdfLauncher.openUrl("pdf/fragen.pdf");
						}else{
							JOptionPane.showMessageDialog(frame, "Sie haben keine aktuellen Karten zum Drucken!", "Funktion nicht möglich", 2);
						}						
						break;
					case 15: // Karten der aktuellen Phase drucken
						LOG.info("Karten der Phase werden gedruckt");
						if(pKartenSpielPanel.getPhasenListe() != null){
							for (int i = 0, length = pKartenSpielPanel.getPhasenListe().size(); i < length; i++) {
								pPdfKarte.setTableFrage(pKartenSpielPanel.getPhasenListe().get(i).getFrage());
							}
							for (int i = 0, length = pKartenSpielPanel.getPhasenListe().size(); i < length; i++) {
								pPdfKarte.setListeAntwort(pKartenSpielPanel.getPhasenListe().get(i).getAntwort());
							}
							pPdfKarte.dokumentErstellen("pdf/fragen.pdf", true, pKartenSpielPanel.getPhasenListe().size());
							pPdfLauncher.openUrl("pdf/fragen.pdf");
						}else{
							JOptionPane.showMessageDialog(frame, "Sie haben keine aktuellen Karten zum Drucken!", "Funktion nicht möglich", 2);
						}	
						break;
					case 16: // Karten der aktuellen Abfrage im Editor
						LOG.info("Karten der Suche werden gedruckt");
						if(pKartenEditor.getEditorKartenListe().size() > 0){
							for (int i = 0, length = pKartenEditor.getEditorKartenListe().size(); i < length; i++) {
								pPdfKarte.setTableFrage(pKartenEditor.getEditorKartenListe().get(i).getFrage());
							}
							for (int i = 0, length = pKartenEditor.getEditorKartenListe().size(); i < length; i++) {
								pPdfKarte.setListeAntwort(pKartenEditor.getEditorKartenListe().get(i).getAntwort());
							}
							pPdfKarte.dokumentErstellen("pdf/fragen.pdf", true, pKartenEditor.getEditorKartenListe().size());
							pPdfLauncher.openUrl("pdf/fragen.pdf");
						}else{
							JOptionPane.showMessageDialog(frame, "Sie haben keine aktuellen Karten zum Drucken!", "Funktion nicht möglich", 2);
						}
						break;
					case 17: // Karten die vor einer gewissen Zeit erstellt wurden werden gedruckt
						LOG.info("Karten nach Datum werden gedruckt");
						if(kartenListe.size() > 0){
							for (int i = 0, length = kartenListe.size(); i < length; i++) {
								if(kartenListe.get(i).getErfassungsDatum() != null)
									if(((new Date().getTime())-(kartenListe.get(i).getErfassungsDatum().getTime())) > 300000)
										pPdfKarte.setTableFrage(kartenListe.get(i).getFrage());
							}
							for (int i = 0, length = kartenListe.size(); i < length; i++) {
								if(kartenListe.get(i).getErfassungsDatum() != null)
									if(((new Date().getTime())-(kartenListe.get(i).getErfassungsDatum().getTime())) > 300000)
										pPdfKarte.setListeAntwort(kartenListe.get(i).getAntwort());
							}if(pPdfKarte.getZellenInhalt().size() > 0){
								pPdfKarte.dokumentErstellen("pdf/fragen.pdf", true, pPdfKarte.getZellenInhalt().size());
								pPdfLauncher.openUrl("pdf/fragen.pdf");
							}else{
								JOptionPane.showMessageDialog(frame, "Keine Karte mit Erstellungsdatum vorhanden!", "Funktion nicht möglich", 2);
								LOG.info("Keine Antwortdatei erstellt ---> Keine Karten");
							}	
						}else{
							JOptionPane.showMessageDialog(frame, "Sie haben keine aktuellen Karten zum Drucken!", "Funktion nicht möglich", 2);
						}
						break;
					default:
						break;
					}
				}
			}else
				JOptionPane.showMessageDialog(frame, "Sie haben keine aktuellen Karten zum Drucken!", "Funktion nicht möglich", 2);				
		}	
	}
}