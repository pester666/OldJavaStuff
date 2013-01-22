package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lib.Monster;
import lib.Player;
import lib.base.Klasse;
import lib.units.Bogenschütze;
import lib.units.Goblin;
import lib.units.Krieger;
import lib.units.Magier;

public class Spawner {

    private List<Monster> monster = new ArrayList<Monster>();
    
    public Spawner(){
        
    }
    
    public void fillMonsterList(){
        this.monster.add(new Goblin());
    }
    
    
    public Klasse selectPlayerClass(String classId) throws Exception{
        int i = Integer.parseInt(classId);
        switch (i) {
            case 1:
                return new Krieger();
            case 2:
                return new Bogenschütze();
            case 3:
                return new Magier();
            default:
                throw new Exception();
        }
    }

    public Monster generateMonster(Player p) {
        Monster m = null;
        boolean monsterOutOfRange = false;
        Random ran = new Random();
        m = this.monster.get(ran.nextInt(monster.size()));
        //Default-Schwierigkeitsgrad ... alle Monster sind gleich der Heldenstufe oder +1/-1
        do{
            if(m.getLvl() == p.getLvl() || m.getLvl()-1 == p.getLvl() || m.getLvl()+1 == p.getLvl()){
                monsterOutOfRange = true;
                return new Monster(m.getHp(), m.getStr(), m.getDex(), m.getName(), m.getLvl(), m.getActions());
            }
        }while(monsterOutOfRange);
        return new Monster(m.getHp(), m.getStr(), m.getDex(), m.getName(), m.getLvl(), m.getActions());
    }
}
