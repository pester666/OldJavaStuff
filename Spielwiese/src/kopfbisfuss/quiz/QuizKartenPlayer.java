package kopfbisfuss.quiz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizKartenPlayer {

	private JTextArea anzeige;
	private List<QuizKarte> kartenListe;
	private QuizKarte aktuelleKarte;
	private int aktuelleKarteIndex;
	private JFrame frame;
	private JButton nächsteKarteButton;
//	private JButton antwortZeigenKarteButton;
	private boolean istAntwortAngezeigt;

	public static void main(String[] args) {
		QuizKartenPlayer reader = new QuizKartenPlayer();
		reader.los();
	}

	public void los() {

		frame = new JFrame("Quizkartenplayer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel hauptPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);

		anzeige = new JTextArea(10, 20);
		anzeige.setFont(bigFont);

		anzeige.setLineWrap(true);
		anzeige.setEditable(false);

		JScrollPane fScroller = new JScrollPane(anzeige);
		fScroller
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fScroller
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		nächsteKarteButton = new JButton("Frage zeigen");
		nächsteKarteButton.setEnabled(false);
		nächsteKarteButton.addActionListener(new NächsteKarteListener());
//		antwortZeigenKarteButton = new JButton("Antwort zeigen");
//		antwortZeigenKarteButton.setEnabled(false);
//		antwortZeigenKarteButton.addActionListener(new NächsteKarteListener());
		hauptPanel.add(fScroller);
		hauptPanel.add(nächsteKarteButton);

		JMenuBar menüleiste = new JMenuBar();
		JMenu menüDatei = new JMenu("Datei");
		JMenuItem menüPunktLaden = new JMenuItem("Kartensatz laden");
		JMenuItem menüPunktBeenden = new JMenuItem("Beenden");

		menüPunktLaden.addActionListener(new MenüÖffnenListener());

		menüPunktBeenden.addActionListener(new MenüBeendeListener());

		menüDatei.add(menüPunktLaden);
		menüDatei.add(menüPunktBeenden);
		menüleiste.add(menüDatei);
		frame.setJMenuBar(menüleiste);
		frame.add(BorderLayout.CENTER, hauptPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}

	public class NächsteKarteListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (istAntwortAngezeigt) {
				anzeige.setText(aktuelleKarte.getAntwort());
				nächsteKarteButton.setText("Nächste Karte");
				istAntwortAngezeigt = false;
			} else {
				if (aktuelleKarteIndex < kartenListe.size())
					nächsteKarteZeigen();
				else {
					anzeige.setText("Das war die letzte Karte.");
					nächsteKarteButton.setEnabled(false);
				}
			}
		}
	}

	public class MenüÖffnenListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser dateiÖffnen = new JFileChooser();
			dateiÖffnen.showOpenDialog(frame);
			if (dateiÖffnen.getSelectedFile() != null)
				neuerKartensatz(kartenAusDateiLaden(dateiÖffnen.getSelectedFile()));
		}
	}

	
	private List<QuizKarte> kartenAusDateiLaden(File datei) {
	
		List<QuizKarte> kartenListe = new ArrayList<QuizKarte>();
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(datei));
			String zeile = null;
			int liCount = 0;
			while ((zeile = reader.readLine()) != null) {
				System.out.println("reading line" + liCount++);
				QuizKarte card=karteParsen(zeile);
				if(card!=null){
					kartenListe.add(card);
				}
			}
		} catch (IOException ex) {
			System.out.println("konnte Kartendatei nicht lesen");
			ex.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return kartenListe;
	}
	

	/**
	 * Parsen eines Kartenobjektes aus einer Zeile
	 * @param zuParsendeZeile String mit zu parsender Zeile, Frage und antwort sollen mit / getrennt sein
	 * @return <code>QuizKarte</code>-Objekt, wenn Frage und antwort geparrst werden konnten, sonst<code>null</code> 
	 */
	private QuizKarte karteParsen(String zuParsendeZeile) {
		String[] ergebnis = zuParsendeZeile.split("/");
		System.out.println("ArraySize von ergebnis: " + ergebnis.length);
		if(ergebnis.length>=2){
			QuizKarte karte = new QuizKarte(ergebnis[0], ergebnis[1]);
			if ((ergebnis[0] != null) && (ergebnis[1] != null)) {
				System.out.println("Karte geparst");
				return karte;
			}
		}
		return null;
	}

	private void nächsteKarteZeigen() {
		if(kartenListe.size()>aktuelleKarteIndex){
			aktuelleKarte = kartenListe.get(aktuelleKarteIndex);
			aktuelleKarteIndex++;
			anzeige.setText(aktuelleKarte.getFrage());
			nächsteKarteButton.setText("Antwort zeigen");
			istAntwortAngezeigt = true;
			nächsteKarteButton.setEnabled(true);
		}else // keine naechste Karte da
		{
			anzeige.setText("");
			nächsteKarteButton.setText("Frage zeigen");
			nächsteKarteButton.setEnabled(false);
		}
	}

	private void neuerKartensatz(List<QuizKarte> kartensatz){
		this.kartenListe=kartensatz;
		this.aktuelleKarteIndex=0;
		nächsteKarteZeigen();
	}
	
	public class MenüBeendeListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			frame.setVisible(false);
			System.exit(1);
		}
	}

}
