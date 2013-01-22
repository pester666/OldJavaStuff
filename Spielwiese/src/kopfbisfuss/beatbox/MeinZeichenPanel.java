package kopfbisfuss.beatbox;

import java.awt.Color;
import java.awt.Graphics;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MeinZeichenPanel extends JPanel implements ControllerEventListener {

	boolean msg = false;

	public void controlChange(ShortMessage event) {
		msg = true;
		repaint();
	}

	public void paintComponent(Graphics g) {

		if (msg) {

			// Graphics2D g2d = (Graphics2D) g;
			//
			// g.fillRect(0, 0, this.getWidth(), this.getHeight());
			//		
			// int rot = (int)(Math.random()*255);
			// int grün = (int)(Math.random()*255);
			// int blau = (int)(Math.random()*255);
			//		
			// Color startColor = new Color(rot, grün, blau);
			//		
			// rot = (int)(Math.random()*255);
			// grün = (int)(Math.random()*255);
			// blau = (int)(Math.random()*255);
			//		
			// Color endColor = new Color(rot, grün, blau);
			//		
			//		
			// GradientPaint gradient = new
			// GradientPaint(70,70,startColor,150,150,endColor);
			//		
			// g2d.setPaint(gradient);
			//		
			// g2d.fillOval(70, 70, 100, 100);
			//		

			int rot = (int) (Math.random() * 255);
			int grün = (int) (Math.random() * 255);
			int blau = (int) (Math.random() * 255);

			g.setColor(new Color(rot, grün, blau));

			int höhe = (int) ((Math.random() * 120) + 10);
			int breite = (int) ((Math.random() * 120) + 10);
			int x = (int) ((Math.random() * 40) + 10);
			int y = (int) ((Math.random() * 40) + 10);

			g.fillRect(x, y, breite, höhe);
			msg = false;

		}
	}
}
