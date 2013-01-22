package game.gui;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.lib.weapons.Hand;

import game.lib.units.Player;

import game.lib.units.Barbarian;
import game.lib.units.Unit;
import game.lib.weapons.Club;

@SuppressWarnings("serial")
public class DPanel extends JPanel implements Runnable{
    
    public static boolean up = false;
    public static boolean down = false;
    public static boolean left = false;
    public static boolean right = false;
    public static boolean isAttacking = false;
    private boolean fightIsOver = false;
    private static Unit barbar = new Barbarian(200, 200, 1, 2, "Barbar", 100, 1, new Club(), 1);
    private static Unit spieler = new Player(0, 0, 1, 2, "Player", 100, 1, new Hand(), 3);
    
    public DPanel() throws IOException, ClassNotFoundException{
        //Liest die angeklickte Karte von der Festplatte in den Editor ein
//        File ff = new File("maps/test");
//        if(!ff.exists()){
//            JOptionPane.showMessageDialog(this, "The map you want to load does not exist!", "Loading failed", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        FileInputStream fIn = new FileInputStream(ff);
//        BufferedInputStream bufIn = new BufferedInputStream(fIn);
//        ObjectInputStream read = new ObjectInputStream(bufIn);
//
//        //Setzt die internen Daten mit den Daten aus der Datei
//        Tile[][] map = (Tile[][])read.readObject();
//        String mapName = (String)read.readObject();
//        String tilesetName = (String)read.readObject();
    }
    
    @Override
    public void run() {
        while(true){
            if(up){
                spieler.setyCoord(spieler.getyCoord() - 5);
                spieler.setDirection(2);
            }else if(down){
                spieler.setyCoord(spieler.getyCoord() + 5);
                spieler.setDirection(0);
            }
            if(left){
                spieler.setxCoord(spieler.getxCoord() - 5);
                spieler.setDirection(3);
            }else if(right){
                if(spieler.getxCoord() >= 130 && spieler.getxCoord() <= 264 && spieler.getyCoord() >= 130 && spieler.getyCoord() <= 338){

                }else{
                    spieler.setxCoord(spieler.getxCoord() + 5);
                    spieler.setDirection(1);
                }
            }
            if(attackPossible()){
                if(barbar.getEquippedWeapon().getCooldownTimer() > 0){
                    barbar.getEquippedWeapon().setMayAttack(false);
                }else{
                    barbar.getEquippedWeapon().setMayAttack(true);
                    heAttacks();
                    barbar.getEquippedWeapon().setCooldownTimer(barbar.getEquippedWeapon().getCooldown());
                } 
            }
            if(isAttacking){
                if(spieler.getEquippedWeapon().getCooldownTimer() > 0){
                    spieler.getEquippedWeapon().setMayAttack(false);
                }else{
                    if(attackPossible()){
                        iAttack();
                    }
                    spieler.getEquippedWeapon().setMayAttack(true);
                    spieler.getEquippedWeapon().setCooldownTimer(spieler.getEquippedWeapon().getCooldown());
                }
            }
            spieler.getEquippedWeapon().setCooldownTimer(spieler.getEquippedWeapon().getCooldownTimer() - 25);
            barbar.getEquippedWeapon().setCooldownTimer(barbar.getEquippedWeapon().getCooldownTimer() - 25);
            repaint();
            if((spieler.getHp() <= 0 || barbar.getHp() <= 0) && fightIsOver == false){
                System.out.println("The fight is over!");
                fightIsOver = true;
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void paintComponent(Graphics g){
        if(barbar.getEquippedWeapon().mayAttack()){
            g.drawImage(new ImageIcon("icons/ea.PNG").getImage(), barbar.getxCoord(), barbar.getyCoord(), null);
        }else{
            g.drawImage(new ImageIcon("icons/e.PNG").getImage(), barbar.getxCoord(), barbar.getyCoord(), null);  
        }
        if(spieler.getDirection() == 3){
            if(spieler.getEquippedWeapon().mayAttack()){
                g.drawImage(new ImageIcon("icons/aa.PNG").getImage(), spieler.getxCoord(), spieler.getyCoord(), null);
            }else{
                g.drawImage(new ImageIcon("icons/a.PNG").getImage(), spieler.getxCoord(), spieler.getyCoord(), null);
            }
        }else if(spieler.getDirection() == 1){
            if(spieler.getEquippedWeapon().mayAttack()){
                g.drawImage(new ImageIcon("icons/ba.PNG").getImage(), spieler.getxCoord(), spieler.getyCoord(), null);
            }else{
                g.drawImage(new ImageIcon("icons/b.PNG").getImage(), spieler.getxCoord(), spieler.getyCoord(), null);
            }
        }else if(spieler.getDirection() == 2){
            if(spieler.getEquippedWeapon().mayAttack()){
                g.drawImage(new ImageIcon("icons/ba.PNG").getImage(), spieler.getxCoord(), spieler.getyCoord(), null);
            }else{
                g.drawImage(new ImageIcon("icons/b.PNG").getImage(), spieler.getxCoord(), spieler.getyCoord(), null);
            }
        }else if(spieler.getDirection() == 0){
            if(spieler.getEquippedWeapon().mayAttack()){
                g.drawImage(new ImageIcon("icons/ba.PNG").getImage(), spieler.getxCoord(), spieler.getyCoord(), null);
            }else{
                g.drawImage(new ImageIcon("icons/b.PNG").getImage(), spieler.getxCoord(), spieler.getyCoord(), null);
            }
        }
    }
        
    private boolean attackPossible(){
        //TODO weapon range einbauen
        if(spieler.getxCoord()+spieler.getWidth()*64 >= barbar.getxCoord() 
                && spieler.getxCoord()+spieler.getWidth()*64 <= barbar.getxCoord()+barbar.getWidth()*64 
                && spieler.getyCoord()+spieler.getHight()*64 >= barbar.getyCoord()-barbar.getHight()*64
                && spieler.getyCoord()+spieler.getHight()*64 <= barbar.getyCoord()+ barbar.getHight()*64
                && spieler.getHp() > 0 
                && barbar.getHp() > 0){
            return true;
        }else{
            return false;
        }
    }
    
    private void iAttack(){
        Random ran = new Random();
        int dmg = ran.nextInt(spieler.getEquippedWeapon().getDmgMax());
        if(dmg < spieler.getEquippedWeapon().getDmgMin()){
            dmg = spieler.getEquippedWeapon().getDmgMin();
        }
        System.out.println("hit him for " + dmg);
        barbar.setHp(barbar.getHp() - dmg);
        System.out.println("HIS HP: " + barbar.getHp());
    }
    
    private void heAttacks(){
        Random ran = new Random();
        int dmg = ran.nextInt(barbar.getEquippedWeapon().getDmgMax());
        if(dmg < barbar.getEquippedWeapon().getDmgMin()){
            dmg = barbar.getEquippedWeapon().getDmgMin();
        }
        System.out.println(dmg + " dmg on me");
        spieler.setHp(spieler.getHp() - dmg);
        System.out.println("MINE HP: " + spieler.getHp());
    }
}
