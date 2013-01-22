package kopfbisfuss.quiz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizKartenAutor {

	private JTextArea taFrage;
	private JTextArea taAntwort;
	private JButton btnNaechsteKarteButton;
	private ArrayList<QuizKarte> kartenListe;
	private JFrame frame;
	private int kartenCount = 0;
	private boolean gespeichert = false;

	// final JOptionPane optionPane = new JOptionPane(
	// "Sie haben noch nicht gespeichert.\n"
	// + "Wollen sie die geschrieben Eingaben verwerfen?",
	// JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);

	// int value = ((Integer)optionPane.getValue()).intValue();

	public int showSpeicherWarnungDlg() {
		if (kartenCount != 0 & gespeichert == false) {
			return JOptionPane.showConfirmDialog(frame,
					"Wollen sie die geschrieben Eingaben verwerfen?",
					"Sie haben noch nicht gespeichert.",
					JOptionPane.YES_NO_OPTION);
		} else
			return 0;

	}

	public static void main(String[] args) {
		QuizKartenAutor editor = new QuizKartenAutor();
		editor.los();
	}

	public void los() {

		frame = new JFrame("Quizkartenautor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		taFrage = new JTextArea(6, 20);
		taFrage.setLineWrap(true);
		taFrage.setWrapStyleWord(true);
		taFrage.setFont(bigFont);
		FrageAntwortKeyListener l = new FrageAntwortKeyListener();
		taFrage.addKeyListener(l);

		JScrollPane fScroller = new JScrollPane(taFrage);
		fScroller
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fScroller
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		taAntwort = new JTextArea(6, 20);
		taAntwort.setLineWrap(true);
		taAntwort.setWrapStyleWord(true);
		taAntwort.setFont(bigFont);
		taAntwort.addKeyListener(l);

		JScrollPane aScroller = new JScrollPane(taAntwort);
		aScroller
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		btnNaechsteKarteButton = new JButton("Nächste Karte");
		btnNaechsteKarteButton.addActionListener(new NächsteKarteListener());
		btnNaechsteKarteButton.setEnabled(false);
		kartenListe = new ArrayList<QuizKarte>();

		JLabel fLabel = new JLabel("Die Frage lautet: ");
		JLabel aLabel = new JLabel("Die Antwort lautet: ");

		mainPanel.add(fLabel);
		mainPanel.add(fScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(btnNaechsteKarteButton);

		JMenuBar menüLeiste = new JMenuBar();
		JMenu menüDatei = new JMenu("Datei");
		JMenuItem menüPunktNeu = new JMenuItem("Neu");
		JMenuItem menüPunktSpeichern = new JMenuItem("Speichern");
		JMenuItem menüPunktBeenden = new JMenuItem("Beenden");

		menüPunktNeu.addActionListener(new MenüNeuListener());

		// menüPunktSpeichern.addActionListener(new ActionListener(){
		// public void actionPerformed(ActionEvent ev) {
		// System.out.println("taFrage.getText():"
		// + (frage.getText() == null ? "null" : frage.getText()));
		// System.out.println("taAntwort.getText():"
		// + (antwort.getText() == null ? "null" : antwort.getText()));
		// QuizKarte karte = new QuizKarte(frage.getText(), antwort.getText());
		// System.out.println(karte);
		// kartenListe.add(karte);
		// karteAbräumen();
		// }
		// });

		menüPunktSpeichern.addActionListener(new MenüSpeichernListener());

		menüPunktBeenden.addActionListener(new MenüBeendenListener());

		menüDatei.add(menüPunktNeu);
		menüDatei.add(menüPunktSpeichern);
		menüDatei.add(menüPunktBeenden);
		menüLeiste.add(menüDatei);
		frame.setJMenuBar(menüLeiste);
		frame.add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}

	public class NächsteKarteListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			System.out.println("taFrage.getText():"
					+ (taFrage.getText() == null ? "null" : taFrage.getText()));
			System.out.println("taAntwort.getText():"
					+ (taAntwort.getText() == null ? "null" : taAntwort
							.getText()));
			if (checkValidFrageAntwort()) {
				QuizKarte karte = new QuizKarte(taFrage.getText(), taAntwort
						.getText());
				System.out.println(karte);
				kartenListe.add(karte);
				kartenCount += 1;
			}
			karteAbräumen();
			btnNaechsteKarteButton.setEnabled(checkValidFrageAntwort());
		}
	}

	public class MenüSpeichernListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			if (checkValidFrageAntwort()) {
				QuizKarte karte = new QuizKarte(taFrage.getText(), taAntwort
						.getText());
				kartenListe.add(karte);
			}
			JFileChooser dateiWahl = new JFileChooser();
			dateiWahl.showSaveDialog(frame);
			dateiSpeichern(dateiWahl.getSelectedFile());
		}

	}

	private boolean checkValidFrageAntwort() {
		if (!checkValidString(taFrage.getText())
				|| !checkValidString(taAntwort.getText())) {
			return false;
		}
		return true;
	}

	private boolean checkValidString(String toCheck) {
		if (toCheck == null)
			return false;
		if (toCheck.trim().length() == 0)
			return false;
		return true;

	}

	public class MenüNeuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			int chosenOption = showSpeicherWarnungDlg();
			if (chosenOption == JOptionPane.YES_OPTION) {
				kartenListe.clear();
				karteAbräumen();
				kartenCount = 0;
				gespeichert = false;
			} else if (chosenOption == JOptionPane.NO_OPTION) {
				JFileChooser dateiWahl = new JFileChooser();
				dateiWahl.showSaveDialog(frame);
				if (dateiWahl.getSelectedFile() != null) {
					dateiSpeichern(dateiWahl.getSelectedFile());
				}
			}
		}
	}

	class FrageAntwortKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			btnNaechsteKarteButton.setEnabled(checkValidFrageAntwort());
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	}

	private void karteAbräumen() {
		taFrage.setText("");
		taAntwort.setText("");
		taFrage.requestFocus();
	}

	private void dateiSpeichern(File datei) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(datei));

			for (QuizKarte karte : kartenListe) {
				writer.write(karte.getFrage() + "/");
				writer.write(karte.getAntwort() + "\n");
			}
			writer.close();
			gespeichert = true;
		} catch (IOException ex) {
			System.out.println("konnte die Kartenliste nicht schreiben");
			ex.printStackTrace();
		}
	}

	public class MenüBeendenListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			int chosenOption = showSpeicherWarnungDlg();
			if (chosenOption == JOptionPane.YES_OPTION) {
				frame.setVisible(false);
				System.exit(1);
			} else if (chosenOption == JOptionPane.NO_OPTION) {
				JFileChooser dateiWahl = new JFileChooser();
				dateiWahl.showSaveDialog(frame);
				if (dateiWahl.getSelectedFile() != null) {
					dateiSpeichern(dateiWahl.getSelectedFile());
				}
			}
		}
	}
}
