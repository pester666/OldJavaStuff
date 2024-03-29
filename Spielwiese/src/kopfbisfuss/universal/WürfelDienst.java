package kopfbisfuss.universal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WürfelDienst implements Dienst {

	JLabel label;
	JComboBox anzahlWürfel;

	public JPanel getGuiPanel() {
		JPanel panel = new JPanel();
		JButton button = new JButton("Würfeln");
		String[] wahl = { "1", "2", "3", "4", "5" };
		anzahlWürfel = new JComboBox(wahl);
		label = new JLabel("hier erscheinen gleich die Augenzahlen");
		button.addActionListener(new WurfListener());
		panel.add(anzahlWürfel);
		panel.add(button);
		panel.add(label);
		return panel;
	}

	public class WurfListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			String augenZahl = "";
			String auswahl = (String) anzahlWürfel.getSelectedItem();
			int anzahlZuWerfende = Integer.parseInt(auswahl);
			for (int i = 0; i < anzahlZuWerfende; i++) {
				int z = (int) ((Math.random() * 6) + 1);
				augenZahl += (" " + z);
			}
			label.setText(augenZahl);
		}
	}
}
