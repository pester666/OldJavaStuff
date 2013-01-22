package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Gibt die GUI der Statistik zurück
 * 
 * @author Pester
 * 
 */
public class Statistik {

	private final Font smallFont = new Font("sanserif", Font.BOLD, 17);

	private JLabel laRundenAnzeige = new JLabel("Aktuelle Runde:");
	private JLabel laAuflistungErgebnis = new JLabel(
			"Hier steht dann das Ergebnis dass einfach noch viel unglaublich länger ist und viel platz braucht");
	private JPanel plRoot = new JPanel();

	public Statistik() {
		super();
	}

	public JComponent guiStatistik() {
		plRoot.setLayout(new BoxLayout(plRoot, BoxLayout.Y_AXIS));
		laRundenAnzeige.setFont(smallFont);
		laAuflistungErgebnis.setFont(smallFont);
		plRoot.add(Box.createRigidArea(new Dimension(0, 15)));
		plRoot.add(laRundenAnzeige);
		plRoot.add(Box.createRigidArea(new Dimension(0, 5)));
		plRoot.add(laAuflistungErgebnis);
		plRoot.add(Box.createRigidArea(new Dimension(0, 15)));
		return plRoot;
	}

	public void setText(String text) {
		this.laAuflistungErgebnis.setText(text);
	}
}