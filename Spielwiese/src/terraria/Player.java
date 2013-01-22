package terraria;

import java.awt.*;

/**
 * Class implements the player behaviour including movement, scrolling ... other
 * abilities to be added
 */
public class Player {
    // Constants
    private final int walk_x_speed = 2;
    private final int fall_y_speed = 2;
    private final int jump_y_speed = 2;
    private final int jump_y_speed2 = 1;

    // Variables
    private int x_pos_left;
    private int x_pos_right;
    private int y_pos_up;
    private int y_pos_down;

    // Counter for jumping and animation
    private int jump_counter;
    private int picture_counter;
    private int step_counter;

    // game position of the player
    private int game_x_position;

    // boolean values, is player jumping, falling or walking
    private boolean jumping;
    private boolean falling;
    private boolean walking_left;
    private boolean walking_right;

    // value tells if player looks left (look_left = true) or right (look_left = false)
    private boolean look_left;

    // cant jump one more time if player is already jumping => we need a jump lock
    private boolean jump_lock;

    // Images
    private Image stop_left;
    private Image walk_left;
    private Image jump_left;
    private Image stop_right;
    private Image walk_right;
    private Image jump_right;

    private Component parent;

    // Constructor
    public Player(int x, int y, Component parent) {
        //initialize player positions
        x_pos_left = x;
        x_pos_right = x + C_Jump.player_image_width;
        y_pos_up = y;
        y_pos_down = y + C_Jump.player_image_height;

        // game position of the player
        game_x_position = x + (C_Jump.player_image_width / 2);

        // initialize counter
        jump_counter = 0;
        picture_counter = 0;
        step_counter = 0;

        // initialize boolean values
        jump_lock = false;
        look_left = false;

        this.parent = parent;
    }

    // method sets images
    public void setImages(Image stop_right, Image walk_right, Image jump_right, Image stop_left, Image walk_left, Image jump_left) {
        this.stop_right = stop_right;
        this.walk_right = walk_right;
        this.jump_right = jump_right;
        this.stop_left = stop_left;
        this.walk_left = walk_left;
        this.jump_left = jump_left;
    }

    // returns x position left
    public int getXPosLeft() {
        return x_pos_left;
    }

    // returns x position right
    public int getXPosRight() {
        return x_pos_right;
    }

    // returns y position up
    public int getYPosUp() {
        return y_pos_up;
    }

    // returns y position down
    public int getYPosDown() {
        return y_pos_down;
    }

    // returns game position of the player
    public int getGameXPosition() {
        return game_x_position;
    }

    // returns if player is falling
    public boolean getIsFalling() {
        return falling;
    }

    // returns if player is jumping
    public boolean getIsJumping() {
        return jumping;
    }

    // returns if player looks left
    public boolean getLooksLeft() {
        return look_left;
    }

    // resets player to start values
    public void resetPlayer(int x, int y) {
        x_pos_left = x;
        x_pos_right = x + C_Jump.player_image_width;
        y_pos_up = y;
        y_pos_down = y + C_Jump.player_image_height;

        game_x_position = x + (C_Jump.player_image_width / 2);

        jump_counter = 0;
        jump_lock = false;
        look_left = false;

        picture_counter = 0;
        step_counter = 0;
    }

    // tests if player is out
    public boolean isOutDown() {
        if (y_pos_up > C_Jump.applet_height) {
            return true;
        } else {
            return false;
        }
    }

    // sets boolean value walking_left
    public void playerWalkLeft(boolean value) {
        walking_left = value;
    }

    // sets boolean value walking_right
    public void playerWalkRight(boolean value) {
        walking_right = value;
    }

    // sets boolean value jumping
    public void playerJump(boolean value) {
        // reset counter
        if (!jumping && !jump_lock && value) {
            jump_counter = 0;
        }

        // set value of jumping (cant jump if player is falling)
        if (falling) {
            jumping = false;
        } else {
            jumping = value;
        }
    }

    // set value of player falling
    public void playerFall(boolean value) {
        if (!value) {
            if (jump_lock) {
                jump_lock = false;
                jumping = false;
            }

            // correct player position if player stops falling in a level element
            // move player to level element surface
            while (y_pos_down % C_Jump.level_element_height != 0) {
                y_pos_down--;
                y_pos_up--;
            }
        }

        falling = value;
    }

    // method moves player according to the given boolean values (walking_left...)
    public void playerMove() {
        // player walks left
        if (walking_left) {
            x_pos_left -= walk_x_speed;
            x_pos_right -= walk_x_speed;
            game_x_position -= walk_x_speed;

            if (step_counter % 15 == 0) {
                picture_counter++;

                if (picture_counter == 2) {
                    picture_counter = 0;
                }

                step_counter = 1;
            } else {
                step_counter++;
            }

            look_left = true;
        }
        // player walks right
        else if (walking_right) {
            x_pos_left += walk_x_speed;
            x_pos_right += walk_x_speed;
            game_x_position += walk_x_speed;

            if (step_counter % 15 == 0) {
                picture_counter++;

                if (picture_counter == 2) {
                    picture_counter = 0;
                }

                step_counter = 1;
            } else {
                step_counter++;
            }

            look_left = false;
        }

        // player jumps
        if (jumping) {
            jump_lock = true;

            // falling is false if player is jumping
            falling = false;

            if (jump_counter < 50) {
                y_pos_up -= jump_y_speed;
                y_pos_down -= jump_y_speed;
                jump_counter++;
            } else if (jump_counter < 60) {
                y_pos_up -= jump_y_speed2;
                y_pos_down -= jump_y_speed2;
                jump_counter++;
            } else {
                jumping = false;
            }
        }

        // player falls
        if (falling) {
            y_pos_up += fall_y_speed;
            y_pos_down += fall_y_speed;
        }
    }

    // scroll player
    public void scrollPlayer(int speed) {
        x_pos_left += speed;
        x_pos_right += speed;
    }

    // draw player to the screen
    public void paintPlayer(Graphics g) {
        if (!jumping && !falling) {
            if (walking_right) {
                if (picture_counter == 0) {
                    g.drawImage(stop_right, x_pos_left, y_pos_up, 32, 64, parent);
                } else {
                    g.drawImage(walk_right, x_pos_left, y_pos_up, 32, 64, parent);
                }
            } else if (walking_left) {
                if (picture_counter == 0) {
                    g.drawImage(stop_left, x_pos_left, y_pos_up, 32, 64, parent);
                } else {
                    g.drawImage(walk_left, x_pos_left, y_pos_up, 32, 64, parent);
                }
            } else {
                if (look_left) {
                    g.drawImage(stop_left, x_pos_left, y_pos_up, 32, 64, parent);
                } else {
                    g.drawImage(stop_right, x_pos_left, y_pos_up, 32, 64, parent);
                }
            }
        } else {
            if (look_left) {
                g.drawImage(jump_left, x_pos_left, y_pos_up, 32, 64, parent);
            } else {
                g.drawImage(jump_right, x_pos_left, y_pos_up, 32, 64, parent);
            }
        }
    }
}