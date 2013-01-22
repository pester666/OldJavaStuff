package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation {

    int x = 70;
    int y = 70;
    
    int w = 120;
    int z = 120;
    boolean bewegen = false;
    MeinZeichenPanel zeichenPanel;

    public static void main(String[] args) {
        Animation gui = new Animation();
        gui.los();
    }

    public void los() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        zeichenPanel = new MeinZeichenPanel();

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                bewegen = true;
                int event = e.getKeyCode();
                switch (event) {
                    case KeyEvent.VK_RIGHT:
                        rechts();
                        break;
                    case KeyEvent.VK_LEFT:
                        links();
                        break;
                    case KeyEvent.VK_UP:
                        oben();
                        break;    
                    case KeyEvent.VK_DOWN:
                        unten();
                        break;
                    case KeyEvent.VK_A:
                        attack();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                stop();
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

        });
        frame.add(zeichenPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @SuppressWarnings("serial")
    class MeinZeichenPanel extends JPanel {

        public void paintComponent(Graphics g) {

            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.green);
            g.fillOval(x, y, 40, 40);
            
            g.setColor(Color.red);
            g.fillOval(w, z, 40, 40);
        }
    }

    public void rechts() {
        x = x + 5;
        zeichenPanel.repaint();
    }

    public void links() {
        x = x - 5;
        zeichenPanel.repaint();

    }

    public void oben() {
        y = y - 5;
        zeichenPanel.repaint();
    }

    public void unten() {
        y = y + 5;
        zeichenPanel.repaint();
    }

    public void stop() {
        bewegen = false;
    }
    
    private void attack() {
        int range = 20;
        int range2 = -20;
        
        if((x-w < range && y-z < range && x-w > 0 && y-z > 0) ||
                (x-w > range2 && x-w > range2 && x-w < 0 && y-z < 0)){
            System.out.println("BÄÄM");
        }
    }
}
