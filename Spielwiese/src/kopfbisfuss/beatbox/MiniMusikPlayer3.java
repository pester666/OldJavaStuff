package kopfbisfuss.beatbox;

import java.awt.Color;
import java.awt.Graphics;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiniMusikPlayer3 {

	static JFrame f = new JFrame("Tanzende Formen");
	static MeinZeichenPanel ml;

	public static void main(String[] args) {

		MiniMusikPlayer3 mini = new MiniMusikPlayer3();
		mini.los();
	}

	public void guiErstellen() {
		ml = new MeinZeichenPanel();
		f.setContentPane(ml);
		f.setBounds(30, 30, 300, 300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void los() {
		guiErstellen();

		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addControllerEventListener(ml, new int[] { 127 });
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			int r = 0;

			for (int i = 0; i < 60; i += 4) {

				r = (int) ((Math.random() * 50) + 1);

				track.add(eventErzeugen(144, 1, r, 100, i));

				track.add(eventErzeugen(176, 1, 127, 0, i));

				track.add(eventErzeugen(128, 1, r, 100, i + 2));
			}

			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(120);
			sequencer.start();
			Thread.sleep(5000);
			sequencer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static MidiEvent eventErzeugen(int comd, int chan, int one, int two,
			int tick) {
		MidiEvent event = null;

		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {
		}
		return event;
	}

	@SuppressWarnings("serial")
	class MeinZeichenPanel extends JPanel implements ControllerEventListener {

		boolean msg = false;

		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();
		}

		public void paintComponent(Graphics g) {

			if (msg) {

				// Graphics2D g2 = (Graphics2D) g;

				int rot = (int) (Math.random() * 255);
				int grün = (int) (Math.random() * 255);
				int blau = (int) (Math.random() * 255);

				g.setColor(new Color(rot, grün, blau));

				int höhe = (int) ((Math.random() * 120) + 10);
				int breite = (int) ((Math.random() * 120) + 10);
				int x = (int) ((Math.random() * 40) + 10);
				int y = (int) ((Math.random() * 40) + 10);

				g.fillRect(x, y, breite, höhe);
				msg = false;
			}
		}
	}
}
