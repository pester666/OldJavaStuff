package kopfbisfuss.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation {

	int x = 70;
	int y = 70;

	public static void main(String[] args) {
		Animation gui = new Animation();
		gui.los();
	}

	public void los() {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MeinZeichenPanel zeichenPanel = new MeinZeichenPanel();

		frame.add(zeichenPanel);
		frame.setSize(300, 300);
		frame.setVisible(true);

		for (int i = 0; i < 130; i++) {

			x++;
			y++;

			zeichenPanel.repaint();

			try {
				Thread.sleep(25);
			} catch (Exception e) {
			}
		}
	}

    @SuppressWarnings("serial")
	class MeinZeichenPanel extends JPanel {

		public void paintComponent(Graphics g) {

			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.setColor(Color.green);
			g.fillOval(x, y, 40, 40);
		}
	}

}
