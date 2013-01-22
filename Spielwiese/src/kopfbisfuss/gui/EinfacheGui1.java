package kopfbisfuss.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EinfacheGui1 {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		JButton launch = new JButton("LAUNCH");
		JButton north = new JButton("Zustand stabil");
		JButton south = new JButton("Bereit zum Start");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("Nuclear Launch System");

		frame.add(BorderLayout.CENTER, launch);
		frame.add(BorderLayout.NORTH, north);
		frame.add(BorderLayout.SOUTH, south);

		frame.setSize(200, 200);

		frame.setVisible(true);
	}
}
