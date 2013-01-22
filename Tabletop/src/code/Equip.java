package code;

public class Equip {
	
	public static UnitCreator ausruestenAngreifer(UnitCreator angreifer, ArmsCreator waffen, ArmsCreator ruestung){
		
		angreifer.setA(waffen.getMehrA()*angreifer.getA() + angreifer.getA());
		angreifer.setI(angreifer.getI() + waffen.getMehrI());
		angreifer.setS(angreifer.getS() + waffen.getMehrS());
		angreifer.setRw(angreifer.getRw() + ruestung.getMehrRW());
		
		return angreifer;
	}
	
	public static UnitCreator ausruestenVerteidiger(UnitCreator verteidiger, ArmsCreator waffen, ArmsCreator ruestung){
		
		verteidiger.setA(waffen.getMehrA()*verteidiger.getA() + verteidiger.getA());
		verteidiger.setI(verteidiger.getI() + waffen.getMehrI());
		verteidiger.setS(verteidiger.getS() + waffen.getMehrS());
		verteidiger.setRw(verteidiger.getRw() + ruestung.getMehrRW());
		
		return verteidiger;
	}
	
}
