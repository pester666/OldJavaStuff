package kopfbisfuss.universal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class W�rfelDienst implements Dienst {

	JLabel label;
	JComboBox anzahlW�rfel;

	public JPanel getGuiPanel() {
		JPanel panel = new JPanel();
		JButton button = new JButton("W�rfeln");
		String[] wahl = { "1", "2", "3", "4", "5" };
		anzahlW�rfel = new JComboBox(wahl);
		label = new JLabel("hier erscheinen gleich die Augenzahlen");
		button.addActionListener(new WurfListener());
		panel.add(anzahlW�rfel);
		panel.add(button);
		panel.add(label);
		return panel;
	}

	public class WurfListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			String augenZahl = "";
			String auswahl = (String) anzahlW�rfel.getSelectedItem();
			int anzahlZuWerfende = Integer.parseInt(auswahl);
			for (int i = 0; i < anzahlZuWerfende; i++) {
				int z = (int) ((Math.random() * 6) + 1);
				augenZahl += (" " + z);
			}
			label.setText(augenZahl);
		}
	}
}
