package kopfbisfuss.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EinfacheGui1B implements ActionListener {

	JButton button;

	public static void main(String[] args) {
		EinfacheGui1B gui = new EinfacheGui1B();
		gui.los();
	}

	public void los() {
		JFrame frame = new JFrame();

		button = new JButton("Klick Mich");

		button.addActionListener(this);

		frame.setTitle("Fenster");
		frame.add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		button.setText("Ich wurde geklickt!");
	}

}
