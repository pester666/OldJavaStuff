package kopfbisfuss.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EinfacheGui1C {

	JFrame frame;
	JLabel label;

	public static void main(String[] args) {
		EinfacheGui1C gui = new EinfacheGui1C();
		gui.los();
	}

	public void los() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton colorButton = new JButton("Farbe wechseln");
		colorButton.addActionListener(new ColorListener());

		JButton labelButton = new JButton("Label wechseln");
		labelButton.addActionListener(new LabelListener());

		label = new JLabel("Ein Kreis in der Mitte");

		MeinZeichenPanel zeichenPanel = new MeinZeichenPanel();

		frame.setTitle("Schönes schwarzes Fenster mit buntem Kreis");
		frame.add(BorderLayout.SOUTH, colorButton);
		frame.add(BorderLayout.CENTER, zeichenPanel);
		frame.add(BorderLayout.EAST, labelButton);
		frame.add(BorderLayout.WEST, label);
		frame.setSize(420, 300);
		frame.setVisible(true);
	}

	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			label.setText("AUTSCH!");
		}
	}

	class ColorListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			frame.repaint();
		}
	}
}
