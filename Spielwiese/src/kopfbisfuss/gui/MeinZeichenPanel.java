package kopfbisfuss.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MeinZeichenPanel extends JPanel {

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		int rot = (int) (Math.random() * 255);
		int grün = (int) (Math.random() * 255);
		int blau = (int) (Math.random() * 255);

		Color startColor = new Color(rot, grün, blau);

		rot = (int) (Math.random() * 255);
		grün = (int) (Math.random() * 255);
		blau = (int) (Math.random() * 255);

		Color endColor = new Color(rot, grün, blau);

		GradientPaint gradient = new GradientPaint(70, 70, startColor, 150,
				150, endColor);

		g2d.setPaint(gradient);

		g2d.fillOval(70, 70, 100, 100);
		//		
		// g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//		
		// int rot = (int)(Math.random()*255);
		// int grün = (int)(Math.random()*255);
		// int blau = (int)(Math.random()*255);
		//		
		// Color randomColor = new Color(rot, grün, blau);
		// g.setColor(randomColor);
		// g.fillOval(70, 70, 100, 100);
	}
}
