package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Stellt die Oberfläche des Spielpanels und die Textfelder dar
 * 
 * @author Pester
 * 
 */
public class GuiKartenSpielPanel {

	static final Font BIG_FONT = new Font("sanserif", Font.BOLD, 24);
	JTextArea taFrage;
	JTextArea taAntwort;
	boolean durchsichtig;

	/**
	 * Stellt die Oberfläche des Spielpanels dar
	 * 
	 * @param editable
	 *            : zeigt an, ob die textareas editierbar sein sollen
	 * @param bgDurchsichtig
	 *            : zeigt an, ob der Hintergrund durchsichtig sein soll.
	 */
	public GuiKartenSpielPanel(boolean editable, boolean bgDurchsichtig) {
		this.durchsichtig = bgDurchsichtig;
		taFrage = new JTextArea(5, 10);
		taAntwort = new JTextArea(5, 10);
		taFrage.setFont(BIG_FONT);
		taAntwort.setFont(BIG_FONT);

		taFrage.setLineWrap(true);
		taAntwort.setLineWrap(true);

		taFrage.setEditable(editable);
		taAntwort.setEditable(editable);
	}

	public JComponent guiSpielPanel() {
		JPanel plRoot = new JPanel();
		plRoot.setLayout(new BoxLayout(plRoot, BoxLayout.Y_AXIS));
		plRoot.add(taFrage);
		plRoot.add(Box.createRigidArea(new Dimension(0, 5)));
		plRoot.add(taAntwort);
		plRoot.setOpaque(!durchsichtig);
		return plRoot;
	}

	public void setEditable(boolean editable) {
		taFrage.setEditable(editable);
		taAntwort.setEditable(editable);
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
}
