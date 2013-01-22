package client;

import java.util.Random;

import lib.Battleturn;

import lib.Monster;
import lib.Player;
import lib.base.Constants;
import lib.base.Unit;

public class Frame {

    private Player pDummy;
    private Monster mDummy;
    private Monster m;
    private Player p;
    private Spawner s;
    
    public Frame(){
//        int count = 0;
        p = new Player(50, 10, 10, "");
        s = new Spawner();
        s.fillMonsterList();
        System.out.println("Whats your name?");
        p.setName(System.console().readLine());
      //fo debuggin
//        p.setName("Pester");
        System.out.println("\n");
        System.out.println("You can select one of the following classes:");
        System.out.println(Constants.warrior.getId() + ". " + Constants.warrior.getName());
        System.out.println(Constants.archer.getId() + ". " + Constants.archer.getName());
        System.out.println(Constants.mage.getId() + ". " + Constants.mage.getName());
        System.out.print("Choose wisley: ");
        
        while(p.getKlasse() == null){
            try {
                p.setKlasse(s.selectPlayerClass(System.console().readLine()));    
            } catch (Exception e) {
                System.out.println("This isnt classy!");
            }
        }
        
      //fo debuggin
//        try {
//            p.setKlasse(s.selectPlayerClass("1"));
//        } catch (Exception e) {

//        }
        
        System.out.println("\n");
        System.out.println(p.getName() + ", your stats look like this:");
        System.out.println("Your are an " + p.getKlasse().getName() + " of lvl " + p.getLvl());
        System.out.println("HP: " + p.getHp() + " Strength: " + p.getStr() + " Dexterity: " + p.getDex());
        System.out.println("\n");
        System.out.println("Your journey may begin!");
        System.out.println("\n");
        
        while(true){
            if(m == null || m.getHp() <= 0){
                m = null;
                m = s.generateMonster(p);
                System.out.println("You encounter a level " + m.getLvl() + " " + m.getName());
//                if(pDummy != null){
//                    p.setDex(pDummy.getDex());
//                    p.setStr(pDummy.getStr());
//                }
            }
            pDummy = new Player(p.getHp(), p.getStr(), p.getDex(), p.getName());
            pDummy.setKlasse(p.getKlasse());
            pDummy.setLvl(p.getLvl());
            pDummy.setXp(p.getXp());
            mDummy = new Monster(m.getHp(), m.getStr(), m.getDex(), m.getName(), m.getLvl(), m.getActions());
            System.out.println("\n");
            System.out.println("Attack with the following:");
            for (int i = 0; i < p.getActions().length; i++) {
                System.out.println((i+1) + ". " + p.getAction(i).getName() + ": " + p.getAction(i).getDescription());
            }
            Integer actionP;
            Integer actionM = monsterAction();
            System.out.println("\n");
            System.out.print("What whilst thou do?: ");
            
            do{
                actionP = playerAction(System.console().readLine());
            }while(actionP == null);
            
            //fo debuggin
            //actionP = playerAction("1");
            
            System.out.println("\n");
            
            Battleturn bt = p.getAction(actionP.intValue()).action(p, m, pDummy, mDummy);
            p = bt.getP();
            pDummy = bt.getpDummy();
            m = bt.getM();
            mDummy = bt.getmDummy();
            bt = m.getAction(actionM.intValue()).action(m, p, mDummy, pDummy);
            p = bt.getP();
            pDummy = bt.getpDummy();
            m = bt.getM();
            mDummy = bt.getmDummy();
            //Block und Concentrate funtzn noch net gscheid
            m.setStr(mDummy.getStr());
            p.setStr(pDummy.getStr());
            m.setDex(mDummy.getDex());
            p.setDex(pDummy.getDex());
            System.out.println(p.getName() + " has " + p.getHp() + " HP left.");
            if(p.getHp() <= 0){
                System.out.println(p.getName() + " has died.");  
                System.out.println("You have lost!");
                break;
            }
            System.out.println(m.getName() + " has " + m.getHp() + " HP left.");
            if(m.getHp() <= 0){
                System.out.println(m.getName() + " has died.");
                System.out.println("You got " + calcExp(p, m) + " experience!");
                System.out.println("\n");
            }
//            count++;
        }
        System.out.println("G A M E   O V E R");
    }
    
    private Integer playerAction(String action){
        try {
            return Integer.parseInt(action)-1;
        } catch (Exception e) {
            System.out.println("That is not a action!");
        }
        return null;
    }
    
    private Integer monsterAction(){
        Random ran = new Random();
        return ran.nextInt(4);        
    }
    
    private int calcExp(Unit a, Unit b){
        if(a.getLvl() > b.getLvl()){
            return b.getLvl()/2+1;
        }else if(a.getLvl() < b.getLvl()){
            return (int)(b.getLvl()*1.5);
        }else{
            return b.getLvl();
        }
    }
}
