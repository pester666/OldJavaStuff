package terraria;

/**
 *  Class that hold some constants for the game to make
 *  the game as variable as possible according to the size
 *  of the level elements, player images...
 */

public class C_Jump
{
	// Applet size
	public static final int applet_height = 480;
	public static final int applet_width = 480;

	// If the player position is greater or smaller than
	// those values scroll the game to the left or to the right
	public static final int scroll_right =170;
	public static final int scroll_left = 70;

	// When to set level elements active
	public static final int inSight_border_left = -32;
	public static final int inSight_border_right = 512;

	// Size of the player
	public static final int player_image_height = 64;
	public static final int player_image_width = 32;

	// Size of level elements
	public static final int level_element_height = 16;
	public static final int level_element_width = 16;

	// Level size
	public static final int number_of_level_lines = 30;

	// Element id's
	public static final int no_element_id = -1;
	public static final int grass_id = 1;
	public static final int ground_id = 2;

}