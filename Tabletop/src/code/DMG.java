package code;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DMG {
	
	static Unit unit = new Unit();
	static List<UnitCreator> units = new ArrayList<UnitCreator>(); 
	static Frame f = new Frame();
	static Prepare pr = new Prepare();
	static Arms arms = new Arms();
	
	public static void main(String[] args) {
		
		for(int i = 0; i < unit.getAllUnits().size(); i++) {
			units.add(unit.getAllUnits().get(i));
		}
		
		int option = 0;

		Object[] configOptionen = { "Wiese", "Wueste", "Eis", "Fels" };
		String s = (String) JOptionPane.showInputDialog(null,
				"Wählen Sie:\n" + "Geländeoptionen",
				"Schauplatz des Kampfes", JOptionPane.PLAIN_MESSAGE,
				null, configOptionen, "ham");

		if ((s != null) && (s.length() > 0)) {
			option = s.length();

			switch (option) {
			case 3:
				f.changeColor("weiss");
				f.farbenListe.setSelectedIndex(2);
				break;
			case 4:
				f.changeColor("grau");
				f.farbenListe.setSelectedIndex(3);
				break;
			case 5:
				f.changeColor("gruen");
				f.farbenListe.setSelectedIndex(0);
				break;
			case 6:
				f.changeColor("gelb");
				f.farbenListe.setSelectedIndex(1);
				break;
			default:
				break;
			}
		} else
			System.exit(1);
	}
	
	public static void prepare(){

		units.get(0).setAll(Equip.ausruestenAngreifer(units.get(0), arms.nix, arms.standard));
		units.get(1).setAll(Equip.ausruestenAngreifer(units.get(1), arms.nix, arms.standard));
		units.get(2).setAll(Equip.ausruestenAngreifer(units.get(2), arms.nix, arms.nix));
		units.get(3).setAll(Equip.ausruestenAngreifer(units.get(3), arms.nix, arms.nix));
		units.get(4).setAll(Equip.ausruestenAngreifer(units.get(4), arms.nix, arms.nix));
		units.get(5).setAll(Equip.ausruestenAngreifer(units.get(5), arms.nix, arms.standard));
	}
	
//	public static void regimentsGroesse(int groesseAngreifer, int groesseVerteidiger, boolean verluste){
//		angreifer.setA(angreifer.getA()*groesseAngreifer);
//		if(!verluste)
//		angreifer.setLp(angreifer.getLp()*groesseAngreifer);
//		
//		verteidiger.setA(verteidiger.getA()*groesseVerteidiger);
//		if(!verluste)
//		verteidiger.setLp(verteidiger.getLp()*groesseVerteidiger);
//	}
}
