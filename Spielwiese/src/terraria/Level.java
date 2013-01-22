package terraria;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Abstract class that implements the most important methods of a level. It
 * holds an array of level elements and handels the collision control... . If
 * one uses enemies... this class can take control over those elements, too.
 */
public abstract class Level {
    // Variables

    // Array of LevelElements used during scrolling process
    private LevelElement[] elements;

    // Array of LevelElements used for collision control
    private LevelElement[][] collision_array;

    // Component reference
    private Component parent;

    // Size variables
    private int left_level_border;
    private int right_level_border;
    private int level_length;

    // Images
    private Image ground;
    private Image background;
    private Image grass;

    // Constructor, only sets some values
    public Level(Component parent, String level_name) {
        this.parent = parent;

        // fetch images
        ground = new ImageIcon("icon/ground.png").getImage();
        background = new ImageIcon("icon/background.png").getImage();
        grass = new ImageIcon("icon/grass.png").getImage();
    }

    // parse the definition lines and generate LevelElement arrays (one and two dimensional)
    public void initializeLevel(String[] definitions) {
        // initialize collision control array
        collision_array = new LevelElement[C_Jump.number_of_level_lines][definitions[0].length()];

        // initialize level data
        level_length = definitions[0].length() * C_Jump.level_element_width;
        left_level_border = 0;
        right_level_border = C_Jump.applet_width;

        // counter for the number of level_elements
        int elements_counter = 0;

        // iterate over string definitions array
        for (int i = 0; i < definitions.length; i++) {
            char[] definition_line = definitions[i].toCharArray();

            for (int j = 0; j < definition_line.length; j++) {
                // translate chars to concrete LevelElements
                if (definition_line[j] == ':') {
                    collision_array[i][j] = null;
                }
                // ground element
                else if (definition_line[j] == 'g') {
                    Ground element = new Ground(j * C_Jump.level_element_width, i * C_Jump.level_element_height, grass, parent, C_Jump.grass_id);

                    collision_array[i][j] = element;

                    elements_counter++;
                } else if (definition_line[j] == 'b') {
                    Ground element = new Ground(j * C_Jump.level_element_width, i * C_Jump.level_element_height, ground, parent, C_Jump.ground_id);

                    collision_array[i][j] = element;

                    elements_counter++;
                }
            }
        }

        // initialize array with level elements only (one dimensional)
        elements = new LevelElement[elements_counter];

        int counter = 0;

        // copy references of level_elements in collision_array to elements array
        for (int i = 0; i < collision_array.length; i++) {
            for (int j = 0; j < collision_array[i].length; j++) {
                if (collision_array[i][j] != null) {
                    elements[counter] = collision_array[i][j];
                    counter++;
                }
            }
        }
    }

    // method tests for collisions of the player with other level elements
    public void testForPlayerCollisions(Player player) {
        // get some player specific values
        int player_game_pos = player.getGameXPosition();
        int player_down_pos = player.getYPosDown();
        int player_up_pos = player.getYPosUp();

        int player_left = player_game_pos - (C_Jump.player_image_width / 2);
        int player_right = player_game_pos + (C_Jump.player_image_width / 2);

        // Collisions downside
        LevelElement down_element = testCollisionDown(player_game_pos, player_down_pos);

        if (down_element != null) {
            player.playerFall(false);
        } else {
            player.playerFall(true);
        }

        // Collision upside (important for jumping)
        LevelElement upper_element = testCollisionUp(player_game_pos, player_up_pos);

        if (upper_element != null) {
            player.playerJump(false);
        }

        // Collision left
        LevelElement left_element = testCollisionLeft(player_left, player_down_pos);

        if (left_element != null) {
            player.playerWalkLeft(false);
        }

        // Collision right
        LevelElement right_element = testCollisionRight(player_right, player_down_pos);

        if (right_element != null) {
            player.playerWalkRight(false);
        }
    }

    // Method to test for collisions down
    public LevelElement testCollisionDown(int game_pos, int player_y_down) {
        // translate player game positions to collision array positions
        int col1 = game_pos / C_Jump.level_element_width;
        int col2 = col1 - 1;
        int row = player_y_down / C_Jump.level_element_height;

        try {
            // return level element at this position
            if (collision_array[row][col1] != null) {
                return collision_array[row][col1];
            } else if (collision_array[row][col2] != null) {
                return collision_array[row][col2];
            } else {
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    // Method to test for collisions up
    public LevelElement testCollisionUp(int game_pos, int player_y_up) {
        // calculate array positions
        int col = game_pos / C_Jump.level_element_width;
        int row = player_y_up / C_Jump.level_element_height;

        try {
            if (collision_array[row][col] != null) {
                return collision_array[row][col];
            } else {
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    // Method to test for collisions left
    public LevelElement testCollisionLeft(int game_pos, int player_y_down) {
        // calculate array positions
        int col = (game_pos / C_Jump.level_element_width);
        int row1 = (player_y_down / C_Jump.level_element_height) - 1;
        int row2 = row1 - 1;
        int row3 = row2 - 1;
        int row4 = row3 - 1;

        try {
            if (collision_array[row1][col] != null) {
                return collision_array[row1][col];
            } else if (collision_array[row2][col] != null) {
                return collision_array[row2][col];
            } else if (collision_array[row3][col] != null) {
                return collision_array[row3][col];
            } else if (collision_array[row4][col] != null) {
                return collision_array[row4][col];
            } else {
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    // Method to test for collisions right
    public LevelElement testCollisionRight(int game_pos, int player_y_down) {
        // calculate array positions
        int col = (game_pos / C_Jump.level_element_width);
        int row1 = (player_y_down / C_Jump.level_element_height) - 1;
        int row2 = row1 - 1;
        int row3 = row2 - 1;
        int row4 = row3 - 1;

        try {
            if (collision_array[row1][col] != null) {
                return collision_array[row1][col];
            } else if (collision_array[row2][col] != null) {
                return collision_array[row2][col];
            } else if (collision_array[row3][col] != null) {
                return collision_array[row3][col];
            } else if (collision_array[row4][col] != null) {
                return collision_array[row4][col];
            } else {
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    // method to scroll level, scrolls all elements in elements array
    public void scrollLevel(int speed) {
        left_level_border -= speed;
        right_level_border -= speed;

        for (int i = 0; i < elements.length; i++) {
            elements[i].scrollElement(speed);
        }
    }

    // method tests if game can scroll left
    public boolean canScrollLeft() {
        if (left_level_border < 0) {
            return false;
        } else {
            return true;
        }
    }

    // method tests if game can scroll right
    public boolean canScrollRight() {
        if (right_level_border > level_length) {
            return false;
        } else {
            return true;
        }
    }

    // method to paint level
    public void paintLevel(Graphics g) {
        try {
            g.drawImage(background, 0, 0, null);

            // draw all elements in elements array
            for (int i = 0; i < elements.length; i++) {
                elements[i].paintElement(g);
            }
        } catch (Exception ex) {
            // do nothing
        }
    }

    // abstract method to reset level to start values, has to be implemented
    // by child classes of this class
    public abstract void resetLevel();
}