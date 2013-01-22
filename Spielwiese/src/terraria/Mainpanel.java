package terraria;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mainpanel extends JPanel implements Runnable{

    private final int speed = 14;
    
    public Player player;
    
    private Level current_level;
    
    private Image walk_left;
    private Image stop_left;
    private Image jump_left;
    private Image walk_right;
    private Image stop_right;
    private Image jump_right;
    
    public Mainpanel(){
        init();
        this.setDoubleBuffered(true);
        Thread t = new Thread(this);
        t.start();
        
    }
    
    public void init() {
        setBackground(Color.blue);

        // initialize first level
        current_level = new LevelOne(this);

        // player
        player = new Player(100, 100, this);

        // fetch images
        getImages();

        // set player images
        player.setImages(stop_right, walk_right, jump_right, stop_left, walk_left, jump_left);

        // level einmal scrollen, um alle nicht sichtbaren Bilder auf nicht in Sicht zu setzen
        current_level.scrollLevel(0);
    }
    
    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        while (true) {
            // test for collisions of the player with level elements
            current_level.testForPlayerCollisions(player);

            // move player
            player.playerMove();

            // test if player leaves game area downside
            if (player.isOutDown()) {
                // reset level to start configuration
                current_level.resetLevel();
                // reset player
                player.resetPlayer(100, 100);
            }

            // scroll level left if necessary
            if (player.getXPosLeft() <= C_Jump.scroll_left) {
                if (current_level.canScrollLeft()) {
                    player.scrollPlayer(2);
                    current_level.scrollLevel(2);
                }
            }
            // scroll level right if necessary
            else if (player.getXPosLeft() >= C_Jump.scroll_right) {
                if (current_level.canScrollRight()) {
                    player.scrollPlayer(-2);
                    current_level.scrollLevel(-2);
                }
            }

            // repaint game
            repaint();

            try {
                // Stoppen des Threads für in Klammern angegebene Millisekunden
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
                // do nothing
            }

            // Zurücksetzen der ThreadPriority auf Maximalwert
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }        
    }
    
    private void getImages() {
        MediaTracker tracker = new MediaTracker(this);

        stop_right = new ImageIcon("icon/standing_right.png").getImage();
        tracker.addImage(stop_right, 1);

        walk_right = new ImageIcon("icon/walking_right.png").getImage();
        tracker.addImage(walk_right, 2);

        jump_right = new ImageIcon("icon/jumping_right.png").getImage();
        tracker.addImage(jump_right, 3);

        stop_left = new ImageIcon("icon/standing_left.png").getImage();
        tracker.addImage(stop_left, 4);

        walk_left = new ImageIcon("icon/walking_left.png").getImage();
        tracker.addImage(walk_left, 5);

        jump_left = new ImageIcon("icon/jumping_left.png").getImage();
        tracker.addImage(jump_left, 6);

        try {
            tracker.waitForAll();
        } catch (Exception exception) {

        }
    }
    
    public void paintComponent(Graphics g){
        current_level.paintLevel(g);
        player.paintPlayer(g);
    }

}
