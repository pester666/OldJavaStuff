package kopfbisfuss.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel1 {

	public static void main(String[] args) {
		Panel1 gui = new Panel1();
		gui.los();
	}

	public void los() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JButton button = new JButton("Hau drauf");
		JButton button2 = new JButton("Lukas");

		panel.add(button);
		panel.add(button2);

		frame.add(BorderLayout.EAST, panel);
		frame.setSize(250, 200);
		frame.setVisible(true);
	}

}
