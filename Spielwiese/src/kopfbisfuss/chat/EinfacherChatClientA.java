package kopfbisfuss.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EinfacherChatClientA {

	JTextField ausgehend;
	PrintWriter writer;
	Socket sock;

	public void los() {

		JFrame frame = new JFrame("Lächerlich einfacher Chat-Client");
		JPanel hauptPanel = new JPanel();
		ausgehend = new JTextField(20);
		JButton sendenButton = new JButton("Senden");
		sendenButton.addActionListener(new SendenButtonListener());
		hauptPanel.add(ausgehend);
		hauptPanel.add(sendenButton);
		netzwerkEinrichten();
		frame.add(BorderLayout.CENTER, hauptPanel);
		frame.setSize(400, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void netzwerkEinrichten() {

		try {
			sock = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Netzwerkverbindung steht");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private class SendenButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				writer.println(ausgehend.getText());
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			ausgehend.setText("");
			ausgehend.requestFocus();
		}
	}
}
