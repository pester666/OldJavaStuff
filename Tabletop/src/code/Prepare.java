package code;

public class Prepare {

	Battle b = new Battle();
	UnitCreator angreifer;
	UnitCreator verteidiger;
	
	public void charge(){
		
		b.angriff(angreifer.getA(), angreifer, verteidiger, true);
		if(b.isEnde() == false)
		b.angriff(verteidiger.getA(), verteidiger, angreifer, false);
		b.kampfErgebnis(angreifer, verteidiger);
		
		while(!b.isEnde()) {
			angreifer.setFlieht(false);
			verteidiger.setFlieht(false);
			b.angriffReihenfolge(angreifer, verteidiger);
		};
		b.setEnde(false);
	}
}
