package test;

import client.Spawner;
import junit.framework.TestCase;
import lib.Player;
import lib.actions.Block;
import lib.actions.Dash;
import lib.actions.Slash;
import lib.actions.Uppercut;

public class Actiontest extends TestCase{

    Player p;
    
    public Actiontest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSlash(){
        Slash s = new Slash();
        p = new Player(50, 10, 10, "");
        p.setName("Test");
        Spawner sp = new Spawner();
        try {
            p.setKlasse(sp.selectPlayerClass("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
//            Goblin g = (Goblin)s.action(p, new Goblin(), null, null);
//            if(g.getHp() < 0){
//                break;
//            }
        }
    }
    
    public void testDash(){
        Dash s = new Dash();
        p = new Player(50, 10, 10, "");
        p.setName("Test");
        Spawner sp = new Spawner();
        try {
            p.setKlasse(sp.selectPlayerClass("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
//            Goblin g = (Goblin)s.action(p, new Goblin(), null, null);
//            if(g.getHp() < 0){
//                break;
//            }
        }
    }
    
    public void testBlock(){
        Block s = new Block();
        p = new Player(50, 10, 10, "");
        p.setName("Test");
        Spawner sp = new Spawner();
        try {
            p.setKlasse(sp.selectPlayerClass("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
//            Goblin g = (Goblin)s.action(p, new Goblin(), null, null);
//            if(g.getHp() < 0){
//                break;
//            }
        }
    }
    
    public void testUppercut(){
        Uppercut s = new Uppercut();
        p = new Player(50, 10, 10, "");
        p.setName("Test");
        Spawner sp = new Spawner();
        try {
            p.setKlasse(sp.selectPlayerClass("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
//            Goblin g = (Goblin)s.action(p, new Goblin(), null, null);
//            if(g.getHp() < 0){
//                break;
//            }
        }
    }
    
}
