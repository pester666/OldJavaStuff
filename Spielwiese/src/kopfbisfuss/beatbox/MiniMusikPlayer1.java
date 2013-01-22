package kopfbisfuss.beatbox;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMusikPlayer1 {

	public static void main(String[] args) {

		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			for (int i = 5; i < 61; i += 4) {

				track.add(eventErzeugen(144, 8, i, 100, i));
				track.add(eventErzeugen(128, 8, i, 100, i + 2));
			}

			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
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
}
