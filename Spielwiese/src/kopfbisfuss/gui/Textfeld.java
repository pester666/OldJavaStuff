package kopfbisfuss.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Textfeld implements ActionListener {

	JTextArea text;

	public static void main(String[] args) {
		Textfeld gui = new Textfeld();
		gui.los();
	}

	public void los() {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JButton button = new JButton("einfach anklicken");
		button.addActionListener(this);
		text = new JTextArea(10, 20);
		text.setLineWrap(true);

		JScrollPane scroller = new JScrollPane(text);
		scroller
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panel.add(scroller);

		frame.add(BorderLayout.CENTER, panel);
		frame.add(BorderLayout.SOUTH, button);

		frame.setSize(350, 350);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent ev) {
		text.append("Button geklickt \n");
	}

}
