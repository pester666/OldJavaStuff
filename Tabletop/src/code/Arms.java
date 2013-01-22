package code;

public class Arms {

	ArmsCreator wZusaetzlich = new ArmsCreator			(1, 0, 0, 0);
	ArmsCreator wZweihand = new ArmsCreator				(0, 0, 2, -10);
	ArmsCreator wHelleb = new ArmsCreator				(0, 0, 1, 0);
	ArmsCreator wFlegel = new ArmsCreator				(0, 0, 1, 0);
	ArmsCreator wSpeer = new ArmsCreator				(0, 0, 0, 0);
	
	ArmsCreator rSchild = new ArmsCreator				(0, -1, 0, 0);
	ArmsCreator rLeicht = new ArmsCreator				(0, -1, 0, 0);
	ArmsCreator rSchwer = new ArmsCreator				(0, -2, 0, 0);
	ArmsCreator rPlatte = new ArmsCreator				(0, -3, 0, 0);
	ArmsCreator rBeritten = new ArmsCreator				(0, -1, 0, 0);
	ArmsCreator rBerittenS = new ArmsCreator			(0, -2, 0, 0);
	
	ArmsCreator standard = new ArmsCreator				(0, -3, 0, 0);
	ArmsCreator nix = new ArmsCreator					(0, 0, 0, 0);
	
}
