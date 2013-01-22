package code;

import java.util.ArrayList;
import java.util.List;

public class Unit {
	//														Name, 			KG, S, W, LP, I, A, MW, RW, ANGST
	List<UnitCreator> allUnits = new ArrayList<UnitCreator>(); 
	
	UnitCreator orkKrieger = new UnitCreator		("Ork-Krieger", 		3, 3, 4, 1, 2, 1, 7, 6, false, false);
	UnitCreator orkTroll = new UnitCreator			("Troll", 				3, 5, 4, 3, 1, 3, 4, 7, true, false);
	UnitCreator orkGoblin = new UnitCreator			("Goblin", 				3, 3, 3, 1, 2, 1, 6, 5, false, false);
	
	UnitCreator imperiumSpeerkämpfer = new UnitCreator("Speerkämpfer", 		3, 3, 3, 1, 2, 1, 7, 5, false, false);
	UnitCreator imperiumSchwertkämpfer = new UnitCreator("Schwertkämpfer", 	4, 3, 3, 1, 2, 1, 7, 4, false, false);
	
	UnitCreator chaosKrieger = new UnitCreator		("Chaoskrieger", 		5, 4, 4, 1, 5, 1, 8, 3, false, false);
	UnitCreator chaosBarbar = new UnitCreator		("Chaosbarbar", 		4, 3, 3, 1, 4, 1, 7, 5, false, false);
	UnitCreator chaosZerfleischer = new UnitCreator	("Zerfleischer", 		4, 5, 3, 1, 5, 2, 8, 6, true, false);
	
	UnitCreator vampireZombie = new UnitCreator		("Zombie", 				1, 2, 2, 1, 1, 1, 4, 7, true, false);
	UnitCreator vampireSkelett = new UnitCreator	("Skelett", 			2, 3, 3, 1, 2, 1, 4, 4, true, false);
	UnitCreator vampireGhul = new UnitCreator		("Gruftghul", 			3, 3, 4, 1, 2, 2, 4, 7, true, false);
	
	UnitCreator khemriSkelettkrieger = new UnitCreator("Skelettkrieger", 	2, 3, 3, 1, 2, 1, 4, 4, true, false);
	UnitCreator khemriGrabwächter = new UnitCreator	("Grabwächter", 		4, 4, 4, 1, 2, 1, 8, 3, true, false);
	
	UnitCreator echsenSaurus = new UnitCreator		("Sauruskrieger", 		3, 4, 4, 1, 1, 2, 10, 4, false, false);
	
	UnitCreator skavenKlanratte = new UnitCreator	("Klanratte", 			3, 3, 3, 1, 4, 1, 5, 4, false, false);
	
	UnitCreator waldelfenDryade = new UnitCreator	("Dryade", 				4, 4, 4, 1, 4, 2, 8, 6, true, false);
	
	public void setAllUnits(){
		allUnits.add(orkKrieger);
		allUnits.add(orkTroll);
		allUnits.add(orkGoblin);
		allUnits.add(imperiumSpeerkämpfer);
		allUnits.add(imperiumSchwertkämpfer);
		allUnits.add(chaosKrieger);
		allUnits.add(chaosBarbar);
		allUnits.add(chaosZerfleischer);
		allUnits.add(vampireSkelett);
		allUnits.add(vampireZombie);
		allUnits.add(vampireGhul);
		allUnits.add(echsenSaurus);	
		allUnits.add(khemriSkelettkrieger);
		allUnits.add(khemriGrabwächter);
		allUnits.add(skavenKlanratte);
		allUnits.add(waldelfenDryade);
		
	}
	
	public List<UnitCreator> getAllUnits(){
		return allUnits;
	}
}
