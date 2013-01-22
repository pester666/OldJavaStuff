package terraria;

import java.awt.*;

/**
 * Basis class for all level elements, implements common behaviour of all level
 * elements, which is scrolling, drawing... . Specific bahaviour has to be
 * implemented by the child classes.
 */
public class LevelElement {
    // Variables
    private int x_pos_left;
    private int y_pos_up;
    private int x_pos_right;
    private int y_pos_down;

    // specific id of the level element
    private int element_id;

    // boolean value, tells if level element is active
    private boolean inSight;

    // Image
    private Image element_image;

    private Component parent;

    // constructor
    public LevelElement(int x, int y, Image image, Component parent, int element_id) {
        // initialize position
        x_pos_left = x;
        y_pos_up = y;
        x_pos_right = x + C_Jump.level_element_width;
        y_pos_down = y + C_Jump.level_element_height;

        // initialize image
        element_image = image;

        // store element id
        this.element_id = element_id;

        inSight = true;

        this.parent = parent;
    }

    // scrolls level element
    public void scrollElement(int speed) {
        x_pos_left += speed;
        x_pos_right += speed;

        if (x_pos_left < C_Jump.inSight_border_left || x_pos_right > C_Jump.inSight_border_right) {
            inSight = false;
        } else {
            inSight = true;
        }
    }

    // returns level element ID
    public int getElementID() {
        return element_id;
    }

    // returns if element is active
    public boolean getInSight() {
        return inSight;
    }

    // returns x position of level element
    public int getXPos() {
        return x_pos_left;
    }

    // returns y position of level element
    public int getYPos() {
        return y_pos_up;
    }

    // draw level element if element is in sight
    public void paintElement(Graphics g) {
        if (inSight) {
            g.drawImage(element_image, x_pos_left, y_pos_up, 16, 16, parent);
        }
    }
}