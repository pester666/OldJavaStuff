package territory;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Dunedain extends JPanel{

    private boolean isVisible = false;
    
    public Dunedain(){
        this.setName("Dunedain");
        this.setDoubleBuffered(true);
        this.setOpaque(false);
        this.setVisible(false);
        this.setLayout(new BorderLayout());
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionListener);
    }

    public void setActiveMode(boolean isActive) {
        this.isVisible = isActive;
        this.setOpaque(isActive);
        this.setVisible(isActive);
    }
    
    public void paintComponent(Graphics g){
        if(isVisible){
            
        }
    }
    
    private MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isVisible) {
                
            }
        }
    };

    private MouseMotionListener motionListener = new MouseMotionListener() {

        @Override
        public void mouseMoved(MouseEvent e) {
            if (isVisible) {
                repaint();
            }

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }
    };
    
    private Image getImage(String path) {
        URL url = null;
        url = this.getClass().getClassLoader().getResource(path);
        if (url != null) {
            return new ImageIcon(url).getImage();
        } else {
            return new ImageIcon(path).getImage();
        }
    }
}
