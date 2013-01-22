package jrio;

import java.awt.Image;
import java.awt.Component;

/**
 *  This class extends the class Levelelement and implements the
 *  specific behaviour of a ground element in the level which is
 *  to do nothing ;-)
 */
public class Ground extends LevelElement
{
	// Constructor, calls super constructor
	public Ground(int x, int y, Image image, Component parent, int element_id)
	{
		super(x, y, image, parent, element_id);
	}
}