package test;
import java.awt.Color;
import java.awt.Component;

/**
 *  Class extends the abstract class Level and holds the specific level
 *  definition.
 */
public class LevelOne extends Level
{
	/**
	Legend:

	g: ground element

	*/

	// Strings to represent the level
	public static final String row1  = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row2  = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row3  = "::::g::::::::::::::::::::::::::::::::::::::";
	public static final String row4  = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row5  = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row6  = "::::::::::::g::::::::::::::::::::::::::::::";
	public static final String row7  = ":::::::::::::::::::::gg::::::::::::::::::::";
	public static final String row8  = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row9  = "::::::::::::::::::::::::::::::::gg:::::::::";
	public static final String row10 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row11 = "::::::::::::::::::::::::::::::::::::::::g::";
	public static final String row12 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row13 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row14 = "::::::::::::::::::::::::::::::::::::g::::::";
	public static final String row15 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row16 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row17 = "::::::::::::gggg::::::::::::::::g::::::::::";
	public static final String row18 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row19 = ":::::::::::::::::::::::gggg::::::::::::::::";
	public static final String row20 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row21 = "::::::gggg:::::::::::::::::::::::::::::::::";
	public static final String row22 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row23 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row24 = ":::::::::::::::::::::::::::::::::::::::::::";
	public static final String row25 = "ggggggggggggggggggggggggggggggggggggggggggg";

	// level name
	public static final String level_name = "Level1";

	// color array to hold background colors
	private Color [] colors;

	// constructor
	public LevelOne(Component parent)
	{
		// call super constructor
		super(parent, level_name);

		// generate Array with background colors
		initializeColorArray();

		// set background color array in super class
		super.setColorArray(colors);

		String [] definitions = {row1, row2, row3, row4, row5, row6, row7, row8, row9, row10,
						         row11, row12, row13, row14, row15, row16, row17, row18, row19, row20,
						         row21, row22, row23, row24, row25};

		// initialize the level with the definitions given in this child class
		super.initializeLevel(definitions);
	}

	// method to reset level
	public void resetLevel()
	{
		String [] definitions = {row1, row2, row3, row4, row5, row6, row7, row8, row9, row10,
								 row11, row12, row13, row14, row15, row16, row17, row18, row19, row20,
								 row21, row22, row23, row24, row25};

		super.initializeLevel(definitions);
	}

	// method to initialize Color array
	private void initializeColorArray()
	{
		colors = new Color[255];

		for(int i=0; i<255; i++)
		{
			Color color = new Color(i, i, 255);
			colors[i] = color;
		}
	}

}
