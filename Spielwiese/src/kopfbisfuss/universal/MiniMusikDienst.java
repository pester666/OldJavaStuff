package kopfbisfuss.universal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MiniMusikDienst implements Dienst {

	MeinZeichenPanel meinPanel;

	public JPanel getGuiPanel() {
		JPanel hauptPanel = new JPanel();
		meinPanel = new MeinZeichenPanel();
		JButton spielButton = new JButton("Abspielen");
		spielButton.addActionListener(new SpielListener());
		hauptPanel.add(meinPanel);
		hauptPanel.add(spielButton);
		return hauptPanel;
	}

	public class SpielListener implements ActionListener {

		public void actionPerformed(ActionEvent ev) {

			try {
				Sequencer sequencer = MidiSystem.getSequencer();
				sequencer.open();
				sequencer.addControllerEventListener(meinPanel,
						new int[] { 127 });
				Sequence seq = new Sequence(Sequence.PPQ, 4);
				Track track = seq.createTrack();

				for (int i = 0; i < 60; i += 4) {

					int r = (int) ((Math.random() * 50) + 1);

					if (r < 38) {

						track.add(machEvent(144, 1, r, 100, i));

						track.add(machEvent(176, 1, 127, 0, i));

						track.add(machEvent(128, 1, r, 100, i + 2));
					}
				}

				sequencer.setSequence(seq);
				sequencer.start();
				sequencer.setTempoInBPM(220);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public MidiEvent machEvent(int comd, int chan, int one, int two, int tick) {

		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception ex) {
		}
		return event;
	}

	public class MeinZeichenPanel extends JPanel implements
			ControllerEventListener {

		boolean msg = false;

		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();
		}

		public void paintComponent(Graphics g) {

			if (msg) {
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
