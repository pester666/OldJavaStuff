package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

/**
 * Das Editorpanel wird genutzt, um Karten zu erstellen, zu bearbeiten und den kompletten Kartensatz durchzuschalten
 * @author Pester
 *
 */
public class KartenEditorPanel {

	static private final Logger LOG =Logger.getLogger(KartenSpielPanel.class); 
	
	private static String REGEX = null;
	private static String INPUT_1 = null;
	private static String INPUT_2 = null;

	static final Font BIG_FONT = new Font("sanserif", Font.BOLD, 24);
	static final Font SMALL_FONT = new Font("sanserif", Font.BOLD, 16);
	
	private static final Color BG_COLOR = Color.gray;
	private static final Color FG_COLOR = Color.white;
		
	public static enum Mode {bearbeiten, neu};

	JTextArea taFrage; 
	JTextArea taAntwort;
	JTextArea taSuche;
	JLabel laUeberschrift;
	JPanel plFilter = new JPanel();
	JPanel plAngabenPanel = new JPanel();
	JLabel laKommandoZeile;
	JLabel laKartenAnzahl;
	JLabel laFilterBezeichnung;
	JPanel plSuche = new JPanel();
	JButton btnSpeichern;
	JButton btnAbfrage;
	JButton btnHinzu;
	JButton btnWeiter;
	JButton btnZurück;
	JButton btnSuchen;
	JButton btnAbbrechen;
	JButton btnNeueLektion;
	JButton btnEigeneDruckListe;
	JPanel plKopfZeile = new JPanel();
	JCheckBox cbRegularExpression;
	
	private KarteiKarte aktKarte;
	GuiKartenSpielPanel pFrageAntwort;
	private Mode mode = Mode.bearbeiten;
	private MainframeSteuerung parent;
	private KartenSatzModel kartenSatzModel;
	private boolean kartensatzGeladen = false;
	private boolean gespeichert = false;
			
	private int editorListeIndex = 0;
	private int zwischenSpeicherIndex = 0;
	
	private List<KarteiKarte> editorKartenListe = new ArrayList<KarteiKarte>();
	private List<KarteiKarte> eigeneDruckListe = new ArrayList<KarteiKarte>();

	public int dlgVerwerfenWarnung() {
		if (gespeichert == false) {
			return JOptionPane.showConfirmDialog(KartenEditorPanel.this.laUeberschrift.getRootPane(),
					"Wollen sie die geschrieben Eingaben verwerfen?",
					"Sie haben noch nicht gespeichert.",
					JOptionPane.YES_NO_OPTION);
		} else
			return 0;
	}
	
	public int dlgUeberschreibenWarnung(){
		if(mode == Mode.neu){
		return JOptionPane.showConfirmDialog(KartenEditorPanel.this.laUeberschrift.getRootPane(),
				"Wollen sie die vorhandenen Eingaben überschreiben?",
				"Datei existiert bereits.",
				JOptionPane.YES_NO_OPTION);
		}else
			return 0;
	}
	
	public KartenEditorPanel(boolean bgDurchsichtig, MainframeSteuerung parent,	KartenSatzModel kartenSatzModel) {
		this.parent = parent;
		this.kartenSatzModel = kartenSatzModel;
		taFrage = new JTextArea(5, 10);
		taAntwort = new JTextArea(5, 10);
		taSuche = new JTextArea(1, 1);
		taFrage.setFont(BIG_FONT);
		taAntwort.setFont(BIG_FONT);
		taSuche.setFont(SMALL_FONT);

		taFrage.setLineWrap(true);
		taAntwort.setLineWrap(true);
		taSuche.setLineWrap(true);
		
		laUeberschrift = new JLabel("K A R T E N E D I T O R");
		laUeberschrift.setFont(BIG_FONT);
		laUeberschrift.setForeground(FG_COLOR);
		
		laFilterBezeichnung = new JLabel("Suche:");
		laFilterBezeichnung.setFont(SMALL_FONT);
		laFilterBezeichnung.setForeground(FG_COLOR);
			
		plKopfZeile.setBackground(BG_COLOR);
		plFilter.setBackground(BG_COLOR);
		plSuche.setBackground(BG_COLOR);
		plAngabenPanel.setBackground(BG_COLOR);
		
		laKommandoZeile = new JLabel("Karte wird bearbeitet");
		laKommandoZeile.setFont(SMALL_FONT);
		laKommandoZeile.setForeground(FG_COLOR);
		laKommandoZeile.setPreferredSize(new Dimension(450,25));
		
		laKartenAnzahl = new JLabel("Karte " + editorListeIndex + " von " + editorKartenListe.size());
		laKartenAnzahl.setFont(SMALL_FONT);
		laKartenAnzahl.setForeground(FG_COLOR);

		pFrageAntwort = new GuiKartenSpielPanel(false, true);
		
		cbRegularExpression = new JCheckBox("Regular Expression");
		cbRegularExpression.setBackground(BG_COLOR);
		cbRegularExpression.setForeground(FG_COLOR);

		btnSpeichern = new JButton("Karte Speichern");
		btnSpeichern.addActionListener(new btnSpeichernListener());
		btnSpeichern.setDefaultCapable(true);
		btnSpeichern.setMnemonic(KeyEvent.VK_S);

		btnAbfrage = new JButton("Zurück zur Abfrage");
		btnAbfrage.addActionListener(new btnAbfragenListener());
		btnAbfrage.setEnabled(false);
		
		btnHinzu = new JButton("Karte hinzufügen");
		btnHinzu.addActionListener(new btnHinzuListener());
		btnHinzu.setEnabled(true);
		btnHinzu.setMnemonic(KeyEvent.VK_H);
		
		btnWeiter = new JButton("Nächste Karte (ALT+Rechts)");
		btnWeiter.addActionListener(new btnWeiterListener());
		btnWeiter.setEnabled(false);
		btnWeiter.setMnemonic(KeyEvent.VK_RIGHT);
		
		btnZurück = new JButton("Vorherige Karte (ALT+Links)");
		btnZurück.addActionListener(new btnZurückListener());
		btnZurück.setEnabled(false);
		btnZurück.setMnemonic(KeyEvent.VK_LEFT);
		
		btnSuchen = new JButton("Suche starten");
		btnSuchen.addActionListener(new btnSuchenListener());
		btnSuchen.setEnabled(true);
		btnSuchen.setMnemonic(KeyEvent.VK_U);
		
		btnAbbrechen = new JButton("Hinzufügen abbrechen");
		btnAbbrechen.addActionListener(new btnAbbrechenListener());
		btnAbbrechen.setEnabled(false);
		btnAbbrechen.setMnemonic(KeyEvent.VK_A);
		
		btnNeueLektion = new JButton("Neue Lektion erstellen");
		btnNeueLektion.addActionListener(new btnNeueLektionListener());
		btnNeueLektion.setEnabled(true);
		btnNeueLektion.setMnemonic(KeyEvent.VK_L);
		
		btnEigeneDruckListe = new JButton("Zur Druckliste hinzufügen");
		btnEigeneDruckListe.addActionListener(new btnEigeneDruckListeListener());
		btnEigeneDruckListe.setEnabled(false);
		btnEigeneDruckListe.setMnemonic(KeyEvent.VK_Z);
	}

	public Mode getMode() {
		return mode;
	}
	
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public JComponent guiKartenEditorPanel() {
		JPanel plRoot = new JPanel();
		plRoot.setLayout(new BoxLayout(plRoot, BoxLayout.X_AXIS));
		plRoot.setBackground(BG_COLOR);
		
		plKopfZeile.setLayout(new BoxLayout(plKopfZeile, BoxLayout.X_AXIS));
		plKopfZeile.add(laUeberschrift);
		plKopfZeile.add(Box.createRigidArea(new Dimension(25, 0)));
		plKopfZeile.add(plFilter);
		plKopfZeile.add(Box.createRigidArea(new Dimension(5, 0)));
		plKopfZeile.add(plSuche);
		
		plSuche.setLayout(new BoxLayout(plSuche, BoxLayout.Y_AXIS));
		plSuche.add(cbRegularExpression);
		plSuche.add(btnSuchen);
		
		plAngabenPanel.setLayout(new BoxLayout(plAngabenPanel, BoxLayout.X_AXIS));
		plAngabenPanel.add(laKartenAnzahl);
		plAngabenPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		plAngabenPanel.add(laKommandoZeile);
		plAngabenPanel.add(Box.createHorizontalGlue());
		plAngabenPanel.add(btnEigeneDruckListe);
		plAngabenPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		plAngabenPanel.add(btnNeueLektion);
		plAngabenPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		plAngabenPanel.add(btnAbbrechen);
		
		plFilter.setLayout(new BoxLayout(plFilter, BoxLayout.Y_AXIS));
		plFilter.add(laFilterBezeichnung);
		plFilter.add(taSuche);
		plFilter.add(Box.createRigidArea(new Dimension(0, 25)));
		
		JPanel plButtonBox = new JPanel();
		plButtonBox.setLayout(new BoxLayout(plButtonBox, BoxLayout.X_AXIS));
		plButtonBox.setBackground(BG_COLOR);
		JPanel plTextBox = new JPanel();
		
		plTextBox.setLayout(new BoxLayout(plTextBox, BoxLayout.Y_AXIS));
		plTextBox.add(plKopfZeile);
		plTextBox.add(Box.createRigidArea(new Dimension(0, 5)));
		plTextBox.add(taFrage);
		plTextBox.add(Box.createRigidArea(new Dimension(0, 5)));
		plTextBox.add(taAntwort);
		plTextBox.add(Box.createRigidArea(new Dimension(0, 5)));
		
		plButtonBox.add(btnSpeichern);
		plButtonBox.add(Box.createHorizontalGlue());
		plButtonBox.add(btnZurück);
		plButtonBox.add(Box.createHorizontalGlue());
		plButtonBox.add(btnHinzu);
		plButtonBox.add(Box.createHorizontalGlue());
		plButtonBox.add(btnWeiter);
		plButtonBox.add(Box.createHorizontalGlue());
		plButtonBox.add(btnAbfrage);
		
		plTextBox.add(plButtonBox);
		plTextBox.add(plAngabenPanel);
		plTextBox.add(Box.createRigidArea(new Dimension(0, 5)));
		plTextBox.setOpaque(false);
		
		plRoot.add(Box.createRigidArea(new Dimension(20, 0)));
		plRoot.add(plTextBox);
		plRoot.add(Box.createRigidArea(new Dimension(20, 0)));
		return plRoot;
	}

	public String getFrage() {
		return taFrage.getText().trim();
	}

	public String getAntwort() {
		return taAntwort.getText().trim();
	}

	public void setFrage(String frage) {
		taFrage.setText(frage);
	}

	public void setAntwort(String antwort) {
		taAntwort.setText(antwort);
	}
	
	public boolean getGespeichert() {
		return gespeichert;
	}

	public void setGespeichert(boolean gespeichert) {
		this.gespeichert = gespeichert;
	}

	public boolean getKartensatzGeladen() {
		return kartensatzGeladen;
	}

	public void setKartensatzGeladen(boolean kartensatzGeladen) {
		this.kartensatzGeladen = kartensatzGeladen;
	}
		
	public List<KarteiKarte> getEigeneDruckListe() {
		return eigeneDruckListe;
	}

	public class btnAbfragenListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			String aktuellerKartensatz = parent.currentKartensatzName();
			if(aktuellerKartensatz != null){
			
				if(kartenSatzModel.getKartenListe().get(zwischenSpeicherIndex).getFrage().compareTo(taFrage.getText()) != 0
						|| kartenSatzModel.getKartenListe().get(zwischenSpeicherIndex).getAntwort().compareTo(taAntwort.getText()) != 0){
	
					int chosenOption = dlgVerwerfenWarnung();
					if (chosenOption == JOptionPane.YES_OPTION) {
						parent.activateSpielPanel();
						gespeichert = false;
						laKommandoZeile.setText("Karte wird bearbeitet");
					} else if (chosenOption == JOptionPane.NO_OPTION)
								btnSpeichern.doClick();
								parent.activateSpielPanel();
				}else
					parent.activateSpielPanel();
			}else
				parent.activateSpielPanel();
		}	
	}
	
	public class btnEigeneDruckListeListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			eigeneDruckListe.add(editorKartenListe.get(editorListeIndex));
			laKommandoZeile.setText("Karte hinzugegfügt");
		}
	}

	/**
	 * Betätigt den Karte hinzufügen Button in der GUI
	 */
	public void karteHinzufügen(){
		btnHinzu.doClick();
	}
	
	public class btnHinzuListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			gespeichert = false;
			setMode(Mode.neu);
			setFrage("");
			setAntwort("");
			laKommandoZeile.setText("Karte hinzufügen");
			taFrage.grabFocus();
			btnWeiter.setEnabled(false);
			btnZurück.setEnabled(false);
			btnAbfrage.setEnabled(false);
			btnSuchen.setEnabled(false);
			btnEigeneDruckListe.setEnabled(false);
			btnAbbrechen.setEnabled(true);
			parent.tabbedPaneAktivieren(0, false);
			laKartenAnzahl.setText("Karte " + (editorListeIndex+1) + " von " + editorKartenListe.size());	
			if(parent.currentKartensatzName() == null){
				laKommandoZeile.setText("Lektion hinzufügen");
				laKartenAnzahl.setText("Keine Karten");
			}	
		}
	}
	
	public class btnSpeichernListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			
			KarteikastenDAO dao = new DAOFactory().getKarteikastenDAO();

			boolean neueKarte;
			boolean neueLektion = false;
			String lektionsNameNeu = "";

			if(mode == Mode.bearbeiten){
				neueKarte = false;
			}else
				neueKarte = true;
			String aktuellerKartensatzName = parent.currentKartensatzName();
			
			if(aktuellerKartensatzName == null){
				lektionsNameNeu = (String)JOptionPane.showInputDialog(taFrage, "Benennen Sie ihre Lektion!\n", "Speichern", JOptionPane.PLAIN_MESSAGE, null, null, "");
				if(parent.getListModelElements().contains(lektionsNameNeu) == true){
					JOptionPane.showMessageDialog(taFrage, "Lektion bereits vorhanden! Bitte wählen Sie einen anderen Namen.", "Fehler",1);
					return;
				}else{
					aktuellerKartensatzName = lektionsNameNeu;
					aktKarte = new KarteiKarte(0, getFrage(), getAntwort(), false, new Date());
					neueLektion = true;
				}	
			}else if(neueKarte == false){
				aktKarte.setFrage(getFrage());
				aktKarte.setAntwort(getAntwort());
				aktKarte.setId(editorKartenListe.get(editorListeIndex).getId());
				aktKarte.setAbfrageDatum(editorKartenListe.get(editorListeIndex).getAbfrageDatum());
				aktKarte.setErfassungsDatum(editorKartenListe.get(editorListeIndex).getErfassungsDatum());
				aktKarte.setPhasenNummer(0);
			}else
				aktKarte = new KarteiKarte(0, getFrage(), getAntwort(), false, new Date());				
			try {
				if(aktuellerKartensatzName != null)
				dao.karteSpeichern(editorListeIndex, aktKarte, aktuellerKartensatzName, neueKarte, neueLektion);
			} catch (Exception e) {
				LOG.error("Fehler beim Speichern:", e);
				e.printStackTrace();
			}
			parent.setCurrentKartensatzName(aktuellerKartensatzName);
			laKartenAnzahl.setText("Karte " + (editorListeIndex+1) + " von " + editorKartenListe.size());
			setMode(Mode.bearbeiten);
			btnWeiter.setEnabled(true);
			btnZurück.setEnabled(true);
			btnAbfrage.setEnabled(true);
			btnSuchen.setEnabled(true);
			btnEigeneDruckListe.setEnabled(true);
			btnAbbrechen.setEnabled(false);
			parent.tabbedPaneAktivieren(0, true);
			parent.listeAktualisieren();
			laKommandoZeile.setText("Karte erfolgreich gespeichert");
			gespeichert = true;
			neueKarte = false;
			neueLektion = false;
		}
	}

	/**
	 * Ändert die aktuelle Karte in die eingereichte Karte um.
	 * @param karte : KarteiKarte K
	 */
	public void setAktuelleKarte(KarteiKarte karte) {
		if(kartensatzGeladen == true && karte != null){
			this.aktKarte = karte;
			this.setFrage(karte.getFrage());
			this.setAntwort(karte.getAntwort());
		}	
	}

	public class btnWeiterListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			if ((editorKartenListe.size()-1) > editorListeIndex){
				editorListeIndex++;
				zwischenSpeicherIndex++;
				taFrage.setText(editorKartenListe.get(editorListeIndex).getFrage());
				taAntwort.setText(editorKartenListe.get(editorListeIndex).getAntwort());
				laKartenAnzahl.setText("Karte " + (editorListeIndex+1) + " von " + editorKartenListe.size());
				laKommandoZeile.setText("Karte wird bearbeitet");
			}
		}
	}

	public class btnZurückListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			if((editorKartenListe.size()-1) >= editorListeIndex && editorListeIndex > 0){
				editorListeIndex--;
				zwischenSpeicherIndex--;
				taFrage.setText(editorKartenListe.get(editorListeIndex).getFrage());
				taAntwort.setText(editorKartenListe.get(editorListeIndex).getAntwort());
				laKartenAnzahl.setText("Karte " + (editorListeIndex+1) + " von " + editorKartenListe.size());
				laKommandoZeile.setText("Karte wird bearbeitet");
			}
		}
	}
	
	public class btnAbbrechenListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			if(parent.currentKartensatzName() != null){
				taFrage.setText(editorKartenListe.get(editorListeIndex).getFrage());
				taAntwort.setText(editorKartenListe.get(editorListeIndex).getAntwort());
				laKartenAnzahl.setText("Karte " + (editorListeIndex+1) + " von " + editorKartenListe.size());
				laKommandoZeile.setText("Karte wird bearbeitet");
				
				btnWeiter.setEnabled(true);
				btnZurück.setEnabled(true);
				btnAbfrage.setEnabled(true);
				btnSuchen.setEnabled(true);
				btnEigeneDruckListe.setEnabled(true);
				btnAbbrechen.setEnabled(false);
				parent.tabbedPaneAktivieren(0, true);	
			}else{
				btnWeiter.setEnabled(false);
				btnZurück.setEnabled(false);
				btnAbfrage.setEnabled(false);
				btnSuchen.setEnabled(false);
				btnEigeneDruckListe.setEnabled(false);
				btnAbbrechen.setEnabled(false);
				btnNeueLektion.setEnabled(true);
				parent.tabbedPaneAktivieren(0, true);
			}
		}
	}
	
	public class btnNeueLektionListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			parent.setCurrentKartensatzName(null);
			btnHinzu.doClick();
		}
	}
	
	public class btnSuchenListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
					
			if(taSuche.getText() == ""){
				parent.activateKartenEditorPanel(kartenSatzModel.getKartenListe().get(0));
			}else{	
				editorKartenListe.clear();
				zwischenSpeicherIndex = editorListeIndex;
				editorListeIndex = 0;
				
				if(cbRegularExpression.isSelected() == false){			
						for (int i = 0, length = kartenSatzModel.getKartenListe().size(); i < length; i++) {
							if(kartenSatzModel.getKartenListe().get(i).getAntwort().indexOf(taSuche.getText()) >= 0 || kartenSatzModel.getKartenListe().get(i).getFrage().indexOf(taSuche.getText()) >= 0)
								editorKartenListe.add(kartenSatzModel.getKartenListe().get(i));
						}
						if(editorKartenListe.size() != 0){
							taFrage.setText(editorKartenListe.get(editorListeIndex).getFrage());
							taAntwort.setText(editorKartenListe.get(editorListeIndex).getAntwort());
							laKartenAnzahl.setText("Karte " + (editorListeIndex+1) + " von " + editorKartenListe.size());
							laKommandoZeile.setText("Karte wird bearbeitet");
						}else{
							taFrage.setText("");
							taAntwort.setText("");
							laKartenAnzahl.setText("Keine Übereinstimmung bei dieser Suche");
							laKommandoZeile.setText("");
						}	
				}else{
					REGEX = taSuche.getText();
				
					for (int i = 0, length = kartenSatzModel.getKartenListe().size(); i < length; i++) {
						INPUT_1 = kartenSatzModel.getKartenListe().get(i).getAntwort();
						INPUT_2 = kartenSatzModel.getKartenListe().get(i).getFrage();
										
					    Pattern pattern = Pattern.compile(REGEX);
					    Matcher matcherAntwort = pattern.matcher(INPUT_1);
					    Matcher matcherFrage = pattern.matcher(INPUT_2);
					    
					    if(matcherAntwort.find() == true || matcherFrage.find() == true)
					    	editorKartenListe.add(kartenSatzModel.getKartenListe().get(i));
					}
					if(editorKartenListe.size() != 0){
						taFrage.setText(editorKartenListe.get(editorListeIndex).getFrage());
						taAntwort.setText(editorKartenListe.get(editorListeIndex).getAntwort());
						laKartenAnzahl.setText("Karte " + (editorListeIndex+1) + " von " + editorKartenListe.size());
						laKommandoZeile.setText("Karte wird bearbeitet");
					}else{
						taFrage.setText("");
						taAntwort.setText("");
						laKartenAnzahl.setText("Keine Übereinstimmung bei dieser Suche");
						laKommandoZeile.setText("");
					}	
				}
			}
		}	
	}

	public int getEditorListeIndex() {
		return editorListeIndex;
	}

	public void setEditorListeIndex(int editorListeIndex) {
		this.editorListeIndex = editorListeIndex;
	}

	public List<KarteiKarte> getEditorKartenListe() {
		return editorKartenListe;
	}

	/**
	 * Inizialisiert die EditoKartenListe für das verwenden der Vor- und Zurückbuttons.
	 * @param kartenListe : Reicht die komplette Kartenliste ein um sie Bearbeiten zu können
	 * @param kartenIndex : Gibt die aktuelle Karte an, die im Editorfenster angezeigt wird
	 */
	public void setEditorKartenListe(List<KarteiKarte> kartenListe, int kartenIndex) {
		eigeneDruckListe.clear();
		editorKartenListe.clear();		
		if(cbRegularExpression.getSelectedObjects() == null && kartenListe != null)
			for (int i = 0, length = kartenListe.size(); i < length; i++)
				editorKartenListe.add(kartenListe.get(i));
		btnWeiter.setEnabled(true);
		btnZurück.setEnabled(true);
		editorListeIndex = (kartenIndex-1);
		zwischenSpeicherIndex = editorListeIndex;
		laKartenAnzahl.setText("Karte " + (editorListeIndex+1) + " von " + editorKartenListe.size());
	}
}