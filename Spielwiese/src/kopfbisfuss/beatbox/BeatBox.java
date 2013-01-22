package kopfbisfuss.beatbox;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("serial")
public class BeatBox implements Serializable {

	JPanel hauptPanel;
	List<JCheckBox> checkboxListe;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame derFrame;

	String[] instrumentNamen = { "Bassdrum", "Hi-Hat, geschlossen",
			"Hi-Hat, offen", "Snaredrum", "Crashbecken", "Händeklatschen",
			"Hohes Tom-Tom", "Hohes Bongo", "Maracas", "Trillerpfeife",
			"Tiefe Conga", "Kuhglocke", "Vibraslap", "Tiefes Tom-Tom",
			"Hohes Agogo", "Hohe Conga, offen" };

	int[] instrumente = { 35, 42, 46, 38, 49, 39, 0, 60, 70, 72, 64, 56, 58,
			47, 67, 63 };

	public static void main(String[] args) {
		new BeatBox().guiErstellen();
	}

	public void guiErstellen() {

		derFrame = new JFrame("Cyber-BeatBox");
		derFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel hintergrund = new JPanel(layout);
		hintergrund.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		checkboxListe = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);

		JButton start = new JButton("Starten");
		start.addActionListener(new MeinStartListener());
		buttonBox.add(start);

		JButton stopp = new JButton("Stopp");
		stopp.addActionListener(new MeinStoppListener());
		buttonBox.add(stopp);

		JButton schneller = new JButton("Schneller");
		schneller.addActionListener(new MeinSchnellerListener());
		buttonBox.add(schneller);

		JButton langsamer = new JButton("Langsamer");
		langsamer.addActionListener(new MeinLangsamerListener());
		buttonBox.add(langsamer);

		JButton speichern = new JButton("Serialisieren");
		speichern.addActionListener(new MeinSendenListener());
		buttonBox.add(speichern);

		JButton laden = new JButton("Wiederherstellen");
		laden.addActionListener(new MeinEinlesenListener());
		buttonBox.add(laden);

		Box namensBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 16; i++)
			namensBox.add(new Label(instrumentNamen[i]));

		hintergrund.add(BorderLayout.EAST, buttonBox);
		hintergrund.add(BorderLayout.WEST, namensBox);

		derFrame.add(hintergrund);

		GridLayout raster = new GridLayout(16, 16);
		raster.setVgap(1);
		raster.setHgap(2);
		hauptPanel = new JPanel(raster);
		hintergrund.add(BorderLayout.CENTER, hauptPanel);

		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxListe.add(c);
			hauptPanel.add(c);
		}

		midiEinrichten();

		derFrame.setBounds(50, 50, 300, 300);
		derFrame.pack();
		derFrame.setVisible(true);
	}

	public void midiEinrichten() {

		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void trackErstellenUndStarten() {

		int[] trackListe = null;

		sequence.deleteTrack(track);
		track = sequence.createTrack();

		for (int i = 0; i < 16; i++) {

			trackListe = new int[16];

			int taste = instrumente[i];

			for (int j = 0; j < 16; j++) {

				JCheckBox jc = checkboxListe.get(j + (16 * i));
				if (jc.isSelected())
					trackListe[j] = taste;
				else
					trackListe[j] = 0;
			}

			tracksErzeugen(trackListe);
		}

		track.add(eventErzeugen(192, 9, 1, 0, 16));

		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class MeinStartListener implements ActionListener {

		public void actionPerformed(ActionEvent a) {
			trackErstellenUndStarten();
		}
	}

	public class MeinStoppListener implements ActionListener {

		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
		}
	}

	public class MeinSchnellerListener implements ActionListener {

		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor * 1.03));
		}
	}

	public class MeinLangsamerListener implements ActionListener {

		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor * .97));
		}
	}

	public void tracksErzeugen(int[] liste) {

		for (int i = 0; i < 16; i++) {
			int taste = liste[i];

			if (taste != 0) {
				track.add(eventErzeugen(144, 9, taste, 100, i));
				track.add(eventErzeugen(128, 9, taste, 100, i + 1));
			}
		}
	}

	public MidiEvent eventErzeugen(int comd, int chan, int one, int two,
			int tick) {
		MidiEvent event = null;

		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return event;
	}

	public class MeinSendenListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {

			// boolean[] checkboxZustand = new boolean[256];
			// for (int i = 0; i < 256; i++) {
			//				
			// JCheckBox check = (JCheckBox) checkboxListe.get(i);
			// if (check.isSelected()){
			// checkboxZustand[i] = true;
			// }
			// }
			try {
				JFileChooser fc = new JFileChooser();
				fc.showSaveDialog(derFrame);
				dateiSpeichern(fc.getSelectedFile());
				System.out.println("Schema erfolreich serialisiert!");
				// FileOutputStream fileStream = new FileOutputStream(new
				// File());
				// ObjectOutputStream os = new ObjectOutputStream(fileStream);
				// os.writeObject(checkboxZustand);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	public class MeinEinlesenListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {

			// boolean[] checkboxZustand = null;
			//						
			try {
				JFileChooser dateiÖffnen = new JFileChooser();
				dateiÖffnen.showOpenDialog(derFrame);
				kartenAusDateiLadenXml(dateiÖffnen.getSelectedFile());
				// FileInputStream fileIn = new FileInputStream(new File
				// ("Checkbox.ser"));
				// ObjectInputStream is = new ObjectInputStream(fileIn);
				// checkboxZustand = (boolean[]) is.readObject();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// for (int i = 0; i < 256; i++) {
			// JCheckBox check = (JCheckBox) checkboxListe.get(i);
			// if(checkboxZustand[i])
			// check.setSelected(true);
			// else
			// check.setSelected(false);
			// }

			sequencer.stop();
			trackErstellenUndStarten();
		}
	}

	private void dateiSpeichern(File datei) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(datei));
			// FileOutputStream fileStream = new FileOutputStream(datei);
			// ObjectOutputStream os = new ObjectOutputStream(fileStream);
			// os.writeObject(this.readZustandFromGui());
			String xml = toXML();
			writer.append(xml);
			writer.flush();

		} catch (IOException ex) {
			System.out.println("konnte die Kartenliste nicht schreiben");
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updateGui(List<Boolean> zustaende) {
		// this.checkboxListe=null;
		for (int i = 0; i < this.checkboxListe.size(); i++) {
			JCheckBox cb = this.checkboxListe.get(i);
			boolean cbZustand = zustaende.get(i);
			cb.setSelected(cbZustand);
		}
	}

	public List<Boolean> readZustandFromGui() {
		List<Boolean> retList = new ArrayList<Boolean>(checkboxListe.size());
		for (int i = 0; i < this.checkboxListe.size(); i++) {
			JCheckBox cb = this.checkboxListe.get(i);
			retList.add(cb.isSelected());
		}
		return retList;
	}

	private String toXML() {
		XStream xstream = new XStream(new DomDriver());
		List<Boolean> l = readZustandFromGui();
		return xstream.toXML(l);
		// System.out.println(xml);
	}

//	private void kartenAusDateiLaden(File datei) {
//
//		// BufferedReader reader=null;
//		try {
//			FileInputStream fileIn = new FileInputStream(datei);
//			ObjectInputStream is = new ObjectInputStream(fileIn);
//			try {
//				Object o = is.readObject();
//				if (o instanceof List) {
//					List<Boolean> cbZustaende = (List<Boolean>) o;
//					updateGui(cbZustaende);
//					System.out.println("geladenes Objekt war List<Boolean>");
//				}
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		} catch (IOException ex) {
//			System.out.println("konnte Beatboxdatei nicht lesen");
//			ex.printStackTrace();
//		}
//	}

	@SuppressWarnings("unchecked")
	private void kartenAusDateiLadenXml(File datei) {

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(datei));
			StringBuffer sb = new StringBuffer();
			while (true) {
				String s = br.readLine();
				if (s == null)
					break;
				sb.append(s);
			}
			XStream xstream = new XStream(new DomDriver());
			List<Boolean> cbZustaende = (List<Boolean>)xstream.fromXML(sb.toString());
			updateGui(cbZustaende);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
