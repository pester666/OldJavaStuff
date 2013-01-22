package jrio;

/**
 *  Class that hold some constants for the game to make
 *  the game as variable as possible according to the size
 *  of the level elements, player images...
 */

public class C_Jump
{
	// Applet size
	public static final int applet_height = 300;
	public static final int applet_width = 300;

	// If the player position is greater or smaller than
	// those values scroll the game to the left or to the right
	public static final int scroll_right =170;
	public static final int scroll_left = 70;

	// When to set level elements active
	public static final int inSight_border_left = -15;
	public static final int inSight_border_right = 315;

	// Size of the player
	public static final int player_image_height = 27;
	public static final int player_image_width = 16;

	// Size of level elements
	public static final int level_element_height = 12;
	public static final int level_element_width = 15;

	// Level size
	public static final int number_of_level_lines = 25;

	// Element id's
	public static final int no_element_id = -1;
	public static final int ground_id = 1;

}