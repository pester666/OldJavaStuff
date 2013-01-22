package code;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Battle {
	
	Wuerfeln w = new Wuerfeln();
	private int wuerfel = 0;
	private int killCountAngreifer = 0;
	private int killCountVerteidiger = 0;
	private int kampfergebnis = 0;
	private boolean ende = false;
	JDialog dlg;
	JTextArea txt = new JTextArea();
	JScrollPane scrPane = new JScrollPane(txt);
	
	public void init(){
		dlg = new JDialog();
		dlg.setTitle("Kampfgeschehen");
		dlg.setSize(400, 700);
		txt.append("Der Kampf beginnt\n");
		dlg.setAlwaysOnTop(true);
		dlg.add(scrPane);
		dlg.setLocation(800, 0);
		dlg.setVisible(true);
	}
	
	public void angriffReihenfolge(UnitCreator angreifer, UnitCreator verteidiger){

		if(angreifer.getI() == verteidiger.getI()){
			setWuerfel((int) (Math.random()*6));
			if(getWuerfel() > 2){
				angriff(angreifer.getA(), angreifer, verteidiger, true);
				angriff(verteidiger.getA(), verteidiger, angreifer, false);
			}else{
				angriff(verteidiger.getA(), verteidiger, angreifer, false);
				angriff(angreifer.getA(), angreifer, verteidiger, true);
			}
		}else if(angreifer.getI() > verteidiger.getI()){
			angriff(angreifer.getA(), angreifer, verteidiger, true);
			angriff(verteidiger.getA(), verteidiger, angreifer, false);
			}else if(angreifer.getI() < verteidiger.getI()){
				angriff(verteidiger.getA(), verteidiger, angreifer, false);
				angriff(angreifer.getA(), angreifer, verteidiger, true);
			}
		kampfErgebnis(angreifer, verteidiger);
	}
	
	public void angriff(int attackenAnzahl, UnitCreator angreifer, UnitCreator verteidiger, boolean killCA){
		if(!ende){		
			if(verteidiger.isAngst() == true && angreifer.isAngst() == false){
				wuerfel = ((int) (Math.random()*7)) + ((int) (Math.random()*7));
				if(wuerfel > angreifer.getMw()){
					txt.append(angreifer.getName() + " hat Angst und greift nicht an! (" + wuerfel + ")\n");
//					System.out.println(angreifer.getName() + " hat Angst und greift nicht an! (" + wuerfel + ")");
				}else
					for(int i = 0; i < attackenAnzahl; i++)
						attacke(angreifer, verteidiger, killCA);
			}else
				for(int i = 0; i < attackenAnzahl; i++)
					attacke(angreifer, verteidiger, killCA);
		}
	}	
	
	public void attacke(UnitCreator angreifer, UnitCreator verteidiger, boolean killCA){
		if(!ende){
			wuerfel = (int) (Math.random()*7);
			if(wuerfel == 0){
				wuerfel = 1;
			}
			
//			System.out.println(wuerfel);
			if(wuerfel >= w.ersterWurf(angreifer.getKg(),verteidiger.getKg())){
				txt.append(angreifer.getName() + " trifft! (" + wuerfel + ")\n");
//				System.out.println(angreifer.getName() + " trifft!");
				wuerfel = (int) (Math.random()*7);
				if(wuerfel == 0){
					wuerfel = 1;
				}
//				System.out.println(wuerfel);
				if(wuerfel >= w.zweiterWurf(angreifer.getS(),verteidiger.getW())){
					txt.append(angreifer.getName() + " verwundet! (" + wuerfel + ")\n");
//					System.out.println(angreifer.getName() + " verwundet!");
					wuerfel = (int) (Math.random()*7);
					if(wuerfel == 0){
						wuerfel = 1;
					}
//					System.out.println(wuerfel);
					if(wuerfel >= w.dritterWurf(angreifer.getS(), verteidiger.getRw())){
						txt.append(verteidiger.getName() + " hat die Verwundung verteidigt! (" + wuerfel + ")\n");
//						System.out.println(verteidiger.getName() + " hat die Verwundung verteidigt!");
					}else{
						txt.append(verteidiger.getName() + " verliert einen Lebenspunkt! (" + wuerfel + ")\n");
//						System.out.println(verteidiger.getName() + " verliert einen Lebenspunkt!");
						verteidiger.setLp(verteidiger.getLp()-1);
//						verteidiger.setA(verteidiger.getA()-1);
						if(killCA)
							killCountAngreifer +=1;
						else
							killCountVerteidiger +=1;
						if(verteidiger.getLp() <= 0){
							ende = true;
							verteidiger.setFlieht(true);
							txt.append(verteidiger.getName() + " s t i r b t! (" + wuerfel + ")\n");
//							System.out.println(verteidiger.getName() + " stirbt!");
						}else{
							txt.append(verteidiger.getName() + " hat noch " + verteidiger.getLp() + " Lebenspunkte! (" + wuerfel + ")\n");
//							System.out.println(verteidiger.getName() + " hat noch " + verteidiger.getLp() + " Lebenspunkte!");
						}
					}
				}else{
					txt.append(angreifer.getName() + " verwundet nicht! (" + wuerfel + ")\n");
//					System.out.println(angreifer.getName() + " verwundet nicht!");
				}
			}else{
				txt.append(angreifer.getName() + " schlägt daneben! (" + wuerfel + ")\n");
//				System.out.println(angreifer.getName() + " schlägt daneben!");
			}
		}	
	}
	
	public void kampfErgebnis(UnitCreator angreifer, UnitCreator verteidiger){
		
		kampfergebnis = killCountAngreifer-killCountVerteidiger;
		if(kampfergebnis == 0){
			if(!ende)
				txt.append("Die Kampfrunde wurde zu einem unentschieden\n");
//				System.out.println("Die Kampfrunde wurde zu einem unentschieden");
		}else if(kampfergebnis > 0){
				txt.append(angreifer.getName() + " gewinnt den Kampf mit einem Ergebnis von " + kampfergebnis + "\n");
//				System.out.println(angreifer.getName() + " gewinnt den Kampf mit einem Ergebnis von " + kampfergebnis);
				if(!ende){
					if(angreifer.isAngst() && angreifer.getLp() == verteidiger.getLp()*2){
						ende = true;
						txt.append(verteidiger.getName() + " ist geflohen! Der Kampf ist vorbei.\n");
//						System.out.println(verteidiger.getName() + " ist geflohen! Der Kampf ist vorbei.");
//						verteidiger.setFlieht(true);
					}else{
						if(!verteidiger.isAngst()){
							wuerfel = ((int) (Math.random()*7)) + ((int) (Math.random()*7));
							if(wuerfel >= verteidiger.getMw()-kampfergebnis){
								txt.append(verteidiger.getName() + " ist geflohen! Der Kampf ist vorbei.\n");
//								System.out.println(verteidiger.getName() + " ist geflohen! Der Kampf ist vorbei.");
								return;
							}	
						}	
					}
				}
			}else if(kampfergebnis < 0){
				txt.append(verteidiger.getName() + " gewinnt den Kampf mit einem Ergebnis von " + kampfergebnis*-1 + "\n");
//				System.out.println(verteidiger.getName() + " gewinnt den Kampf mit einem Ergebnis von " + kampfergebnis*-1);
				if(!ende){
					if(verteidiger.isAngst() && verteidiger.getLp() == angreifer.getLp()*2){
						ende = true;
//						angreifer.setFlieht(true);
						txt.append(angreifer.getName() + " ist geflohen! Der Kampf ist vorbei.\n");
//						System.out.println(angreifer.getName() + " ist geflohen! Der Kampf ist vorbei.");
					}else{
						if(!angreifer.isAngst()){
							wuerfel = ((int) (Math.random()*7)) + ((int) (Math.random()*7));
							if(wuerfel >= angreifer.getMw()-(kampfergebnis*-1)){
								txt.append(angreifer.getName() + " ist geflohen! Der Kampf ist vorbei.\n");
//								System.out.println(angreifer.getName() + " ist geflohen! Der Kampf ist vorbei.");
								return;
							}	
						}
					}
				}
			}
		killCountAngreifer = 0;
		killCountVerteidiger = 0;		
	}
	
	public int getWuerfel() {
		return wuerfel;
	}

	public void setWuerfel(int wuerfel) {
		this.wuerfel = wuerfel;
	}

	public boolean isEnde() {
		return ende;
	}

	public void setEnde(boolean ende) {
		this.ende = ende;
	}

	public int getKillCountAngreifer() {
		return killCountAngreifer;
	}

	public void setKillCountAngreifer(int killCountAngreifer) {
		this.killCountAngreifer = killCountAngreifer;
	}

	public int getKillCountVerteidiger() {
		return killCountVerteidiger;
	}

	public void setKillCountVerteidiger(int killCountVerteidiger) {
		this.killCountVerteidiger = killCountVerteidiger;
	}
}
