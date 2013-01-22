package test;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Main class, manages the complete game
 */
public class Main extends JFrame implements Runnable {
    // Constants
    private final int speed = 14;

    // Variables for key events
    private boolean key_left;
    private boolean key_right;
    private boolean key_space;

    // Variables for player images
    private Image mario_walk_left;
    private Image mario_stop_left;
    private Image mario_jump_left;
    private Image mario_walk_right;
    private Image mario_stop_right;
    private Image mario_jump_right;

    // Thread
    private Thread th;

    // current level
    private Level current_level;

    // Player
    private Player player;

    // Variables used for double buffering
    private Image dbImage;
    private Graphics dbg;

    // initialize game
    public static void main(String[] args) {

        Main m = new Main();
        m.init();        
    }
    
    public void init(){
        final JFrame frame = new JFrame();
        frame.setBackground(Color.blue);
        current_level = new LevelOne(this);

        // player
        player = new Player(100, 200, this);

        // no key pressed
        key_left = false;
        key_right = false;
        key_space = false;

        // fetch images
        getImages();

        // set player images
        player.setImages(mario_stop_right, mario_walk_right, mario_jump_right, mario_stop_left, mario_walk_left, mario_jump_left);

        // level einmal scrollen, um alle nicht sichtbaren Bilder auf nicht in Sicht zu setzen
        current_level.scrollLevel(0);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
    

    /**
     * Method fetches player images and uses media tracker to do so
     */
    private void getImages() {
        MediaTracker tracker = new MediaTracker(this);

        mario_stop_right = new ImageIcon("resources/s1.PNG").getImage();
        tracker.addImage(mario_stop_right, 1);

        mario_walk_right = new ImageIcon("resources/s1.PNG").getImage();
        tracker.addImage(mario_walk_right, 2);

        mario_jump_right = new ImageIcon("resources/s1.PNG").getImage();
        tracker.addImage(mario_jump_right, 3);

        mario_stop_left = new ImageIcon("resources/s1.PNG").getImage();
        tracker.addImage(mario_stop_left, 4);

        mario_walk_left = new ImageIcon("resources/s1.PNG").getImage();
        tracker.addImage(mario_walk_left, 5);

        mario_jump_left = new ImageIcon("resources/s1.PNG").getImage();
        tracker.addImage(mario_jump_left, 6);

        try {
            tracker.waitForAll();
        } catch (Exception exception) {

        }
    }

    /** Methode starts Thread */
    public void start() {
        // create new thread
        th = new Thread(this);
        // start thread
        th.start();
    }

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
                player.resetPlayer(100, 200);
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

    // event handling for keys down
    public boolean keyDown(Event e, int key) {
        if (key == Event.LEFT) {
            key_left = true;

            player.playerWalkLeft(true);
        } else if (key == Event.RIGHT) {
            key_right = true;

            player.playerWalkRight(true);
        } else if (key == 97) {
            key_space = true;

            player.playerJump(true);
        }

        return true;
    }

    // event handling for keys up
    public boolean keyUp(Event e, int key) {
        if (key == Event.LEFT) {
            key_left = false;

            player.playerWalkLeft(false);
        } else if (key == Event.RIGHT) {
            key_right = false;

            player.playerWalkRight(false);
        } else if (key == 97) {
            key_space = false;

            player.playerJump(false);
        }

        return true;
    }

    // Stop Thread
    public void stop() {
        th.stop();
    }

    // Stop Thread
    public void destroy() {
        th.stop();
    }

    public void update(Graphics g) {
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        dbg.setColor(getForeground());

        paint(dbg);

        g.drawImage(dbImage, 0, 0, this);
    }

    /** draw game */
    public void paint(Graphics g) {
        current_level.paintLevel(g);
        player.paintPlayer(g);
    }
}
