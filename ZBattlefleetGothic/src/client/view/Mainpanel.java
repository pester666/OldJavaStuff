package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.lib.Button;
import client.lib.Constants;
import client.lib.basics.Particle;
import client.lib.basics.Ship;
import client.lib.basics.ShipWeapon;
import client.lib.components.ShipBridge;
import client.lib.components.ShipGenerator;
import client.lib.components.ShipLarzarett;
import client.lib.components.ShipOxygen;
import client.lib.components.ShipShield;
import client.lib.components.ShipTurbines;
import client.lib.components.ShipWeapons;
import client.lib.particles.Shot;
import client.lib.ships.Cruiser;

public class Mainpanel extends JPanel implements Runnable{
 
    private int density = 300;
    private Star[] stars;
    private Random ran;
    private Ship ship;
    private List<Button> buttons;
    private Button interior;
    private Button pause;
    private Polygon subsystems;
    private Polygon weaponButtons;
    private Polygon enemyShip;
    private ShipWeapon selectedWeapon;
    private List<Particle> particles;
    
    public Mainpanel(){
        this.setSize(Constants.WINDOWWIDTH, Constants.WINDOWHEIGTH);
        this.setPreferredSize(new Dimension(Constants.WINDOWWIDTH, Constants.WINDOWHEIGTH));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        ran = new Random();
        stars = new Star[density];
        ship = new Cruiser();
        buttons = new ArrayList<Button>();
        interior = new Button(Constants.WINDOWWIDTH-200, Constants.WINDOWHEIGTH-100, "Interior");
        pause = new Button(Constants.WINDOWWIDTH-400, Constants.WINDOWHEIGTH-100, "Pause");
        buttons.add(interior);
        buttons.add(pause);
        this.addMouseListener(new clickiBunit());
        subsystems = new Polygon(new int[]{80, 285, 285, 75}, new int[]{730, 730, 755, 755}, 4);
        weaponButtons = new Polygon(new int[]{300, 410, 410, 300}, new int[]{670, 670, 745, 745}, 4);
        enemyShip = new Polygon(new int[]{710, 995, 995, 710}, new int[]{15, 15, 290, 290}, 4);
        particles = new ArrayList<Particle>();
        for (int i = 0; i < stars.length; i++) {
            int s = ran.nextInt(5) + 1;
            stars[i] = new Star(s,s,(Constants.WINDOWWIDTH/density)*i,ran.nextInt(Constants.WINDOWHEIGTH));
        } 
    }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WINDOWWIDTH, Constants.WINDOWHEIGTH);
        for (int i = 0; i < stars.length; i++) {
            g.setColor(Color.WHITE);
            g.fillOval(stars[i].x - stars[i].speed, stars[i].y, stars[i].size, stars[i].size);
            stars[i].x = stars[i].x - stars[i].speed;
            if(stars[i].x < 0){
                int s = ran.nextInt(5) + 1;
                stars[i] = new Star(s,s,Constants.WINDOWWIDTH+10,ran.nextInt(Constants.WINDOWHEIGTH));
            }
        }

        g.drawImage(new ImageIcon("res/img/ship/" + ship.getName() + ".png").getImage(), ship.getX(), ship.getY(), null);
        for (int i = 0; i < ship.getWeapons().size(); i++) {
            //TODO if isMissile and stuff
            g.drawImage(new ImageIcon("res/img/weapon/" + ship.getWeapons().get(i).getName() + ".png").getImage(), ship.getWeapons().get(i).getX(), ship.getWeapons().get(i).getY(), null);
        }
        for (int i = 0; i < particles.size(); i++) {
            g.drawImage(new ImageIcon("res/img/fx/" + particles.get(i).getName() + ".png").getImage(), particles.get(i).getX(), particles.get(i).getY(), null);
            particles.get(i).setX(particles.get(i).getX() + particles.get(i).getSpeed());
            if(particles.get(i).getX() > Constants.WINDOWWIDTH){
                particles.remove(i);
            }
        }
        g.drawImage(new ImageIcon("res/img/button/Generator.png").getImage(), 10, Constants.WINDOWHEIGTH-160, null);
        if(ship.isShowInterior()){
            for (int i = 0; i < ship.getRooms().size(); i++) {
                g.drawImage(new ImageIcon("res/img/room/Room.png").getImage(), ship.getRooms().get(i).getX(), ship.getRooms().get(i).getY(), null);
                for (int j = 0; j < ship.getRooms().get(i).getTiles().length; j++) {
                    g.drawImage(new ImageIcon("res/img/room/tile.png").getImage(), ship.getRooms().get(i).getTiles()[j].getX(), ship.getRooms().get(i).getTiles()[j].getY(), null);
                }
            }
        }
        for (int i = 0; i < ship.getComponents().size(); i++) {
            if(ship.getComponents().get(i) instanceof ShipTurbines){
                if(ship.getComponents().get(i).getAnimCounter() == 0){
                    ship.getComponents().get(i).setAnimCounter(1);
                    g.drawImage(new ImageIcon("res/img/fx/" + ship.getComponents().get(i).getName() + ".png").getImage(), ship.getComponents().get(i).getX(), ship.getComponents().get(i).getY(), null);
                }else{
                    ship.getComponents().get(i).setAnimCounter(0);
                }
            }else if(ship.getComponents().get(i) instanceof ShipGenerator){
                if(ship.isShowInterior()){
                    g.drawImage(new ImageIcon("res/img/component/Generator.png").getImage(), ship.getComponents().get(i).getX()+15, ship.getComponents().get(i).getY()+15, null);
                }    
                this.updateAndPaintEnergy((ShipGenerator)ship.getComponents().get(i), g);
            }else if(ship.getComponents().get(i) instanceof ShipBridge){
                if(ship.isShowInterior()){
                    g.drawImage(new ImageIcon("res/img/component/Bridge.png").getImage(), ship.getComponents().get(i).getX()+15, ship.getComponents().get(i).getY()+15, null);
                }    
                paintSubsystemForSubsystem("Bridge", i, Constants.WINDOWHEIGTH-67, ship.getComponents().get(i).getMaxEnergy(), ship.getComponents().get(i).getActEnergy(), g);
            }else if(ship.getComponents().get(i) instanceof ShipLarzarett){
                if(ship.isShowInterior()){
                    g.drawImage(new ImageIcon("res/img/component/Lazarett.png").getImage(), ship.getComponents().get(i).getX()+15, ship.getComponents().get(i).getY()+15, null);
                }    
                paintSubsystemForSubsystem("Lazarett", i, Constants.WINDOWHEIGTH-67, ship.getComponents().get(i).getMaxEnergy(), ship.getComponents().get(i).getActEnergy(), g);
            }else if(ship.getComponents().get(i) instanceof ShipOxygen){
                if(ship.isShowInterior()){
                    g.drawImage(new ImageIcon("res/img/component/Oxygen.png").getImage(), ship.getComponents().get(i).getX()+15, ship.getComponents().get(i).getY()+15, null);
                }    
                paintSubsystemForSubsystem("Oxygen", i, Constants.WINDOWHEIGTH-67, ship.getComponents().get(i).getMaxEnergy(), ship.getComponents().get(i).getActEnergy(), g);
            }else if(ship.getComponents().get(i) instanceof ShipShield){
                if(ship.isShowInterior()){
                    g.drawImage(new ImageIcon("res/img/component/Shield.png").getImage(), ship.getComponents().get(i).getX()+15, ship.getComponents().get(i).getY()+15, null);
                }    
                paintSubsystemForSubsystem("Shield", i, Constants.WINDOWHEIGTH-67, ship.getComponents().get(i).getMaxEnergy(), ship.getComponents().get(i).getActEnergy(), g);
            }else if(ship.getComponents().get(i) instanceof ShipWeapons){
                if(ship.isShowInterior()){
                    g.drawImage(new ImageIcon("res/img/component/Weapon.png").getImage(), ship.getComponents().get(i).getX()+15, ship.getComponents().get(i).getY()+15, null);
                }    
                paintSubsystemForSubsystem("Weapon", i, Constants.WINDOWHEIGTH-67, ship.getComponents().get(i).getMaxEnergy(), ship.getComponents().get(i).getActEnergy(), g);
                
            }
        }
        paintWeaponbuttons(g);
        for (int i = 0; i < buttons.size(); i++) {
            if(buttons.get(i).isActive()){
                g.drawImage(new ImageIcon("res/img/button/" + buttons.get(i).getName() + ".png").getImage(), buttons.get(i).getX(), buttons.get(i).getY(), null);
            }else{
                g.drawImage(new ImageIcon("res/img/button/" + buttons.get(i).getName() + "Off.png").getImage(), buttons.get(i).getX(), buttons.get(i).getY(), null);
            }
        }
        for (int i = 0; i < ship.getHp(); i++) {
            g.setColor(Color.GREEN);
            g.fillRect(((i+1)*20), 20, 15, 30);
        }
    }

    @Override
    public void run() {
        while(true){
            this.repaint();
            for (int i = 0; i < ship.getWeapons().size(); i++) {
                if(ship.getWeapons().get(i).getActCooldown() < ship.getWeapons().get(i).getCooldown()){
                    ship.getWeapons().get(i).setActCooldown(ship.getWeapons().get(i).getActCooldown()+1);
                }else if(ship.getWeapons().get(i).isFiring()){
                    ship.getWeapons().set(i, fireWeapon(ship.getWeapons().get(i)));
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void updateAndPaintEnergy(ShipGenerator s, Graphics g){
        s.setActEnergy(s.getMaxEngerySupply());
        for (int i = 0; i < ship.getComponents().size(); i++) {
            if( ! (ship.getComponents().get(i) instanceof ShipGenerator)){
                s.setActEnergy(s.getActEnergy()-ship.getComponents().get(i).getActEnergy());
            }
        }
        g.setColor(Color.GREEN);
        for (int i = 0; i < s.getMaxEngerySupply(); i++) {
            if(s.getActEnergy() >= i){
                g.fillRect(21, Constants.WINDOWHEIGTH-(((i+1)*7)+47), 34, 5);    
            }else{
                g.drawRect(21, Constants.WINDOWHEIGTH-(((i+1)*7)+47), 34, 5);
            }            
        }
    }
    
    private void paintSubsystemForSubsystem(String name, int x, int y, int maxEnergy, int actEnergy, Graphics g){
        g.drawImage(new ImageIcon("res/img/button/Panel/" + name + ".png").getImage(), 80+((x-1)*30), y, null);
        g.setColor(Color.GREEN);
        for (int i = 0; i < maxEnergy; i++) {
            if(actEnergy > i){
                g.fillRect(80+((x-1)*30), y-(((i+1)*7)+10), 23, 5);    
            }else{
                g.drawRect(80+((x-1)*30), y-(((i+1)*7)+10), 23, 5);
            } 
        }
    }
    
    private void paintWeaponbuttons(Graphics g){
        for (int i = 0; i < ship.getWeapons().size(); i++) {
            g.drawImage(new ImageIcon("res/img/button/change.png").getImage(), 385, 670+(i*27), null);
            if(selectedWeapon != null && selectedWeapon.getId() == ship.getWeapons().get(i).getId()){
                g.drawImage(new ImageIcon("res/img/button/selected.png").getImage(), 298, 668+(i*27), null);
            }
            if(ship.getWeapons().get(i).getActCooldown() < ship.getWeapons().get(i).getCooldown()){
                g.drawImage(new ImageIcon("res/img/weapon/cooldown/" + ship.getWeapons().get(i).getName() + ".png").getImage(), 300, 670+(i*27), null);
            }else{
                g.drawImage(new ImageIcon("res/img/weapon/" + ship.getWeapons().get(i).getName() + ".png").getImage(), 300, 670+(i*27), null);
            }
            for (int j = 0; j < ship.getWeapons().get(i).getMaxEnergy(); j++) {
                if(ship.getWeapons().get(i).getActEnergy() > j){
                    g.fillRect(355+(j*7), 670+(i*27), 5, 20);
                }else{
                    g.drawRect(355+(j*7), 670+(i*27), 5, 20);
                } 
            }
        }
    }
    
    private class Star {
        
        int size;
        int speed;
        int x;
        int y;
        
        public Star(int size, int speed, int x, int y) {
            super();
            this.size = size;
            this.speed = speed;
            this.x = x;
            this.y = y;
        }        
    }
    
    private class clickiBunit implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX());
            System.out.println(e.getY());
            if(subsystems.contains(e.getPoint())){
                int a = ((e.getX()-80)/30) +1;
                if( ! (ship.getComponents().get(a) instanceof ShipWeapons)){
                    if(e.getButton() == MouseEvent.BUTTON1){
                        if(ship.getComponents().get(a).getActEnergy() < ship.getComponents().get(a).getMaxEnergy() && ship.getComponents().get(0).getActEnergy() >= 0){
                            ship.getComponents().get(a).setActEnergy(ship.getComponents().get(a).getActEnergy()+1);    
                        }
                    }else if(e.getButton() == MouseEvent.BUTTON3){
                        if(ship.getComponents().get(a).getActEnergy() > 0){
                            ship.getComponents().get(a).setActEnergy(ship.getComponents().get(a).getActEnergy()-1);    
                        }
                    }
                }
                return;
            }
            if(weaponButtons.contains(e.getPoint())){
                int a = ((e.getY()-670)/27);
                if(e.getX() >= 385){
                    if(e.getButton() == MouseEvent.BUTTON1){
                        if(ship.getWeapons().get(a).getActEnergy() < ship.getWeapons().get(a).getMaxEnergy() && ship.getComponents().get(0).getActEnergy() >= 0){
                            ship.getWeapons().get(a).setActEnergy(ship.getWeapons().get(a).getActEnergy()+1);
                            for (int i = 0; i < ship.getComponents().size(); i++) {
                                if(ship.getComponents().get(i) instanceof ShipWeapons){
                                    ship.getComponents().get(i).setActEnergy(ship.getComponents().get(i).getActEnergy()+1);  
                                }
                            }
                        }
                    }else if(e.getButton() == MouseEvent.BUTTON3){
                        if(ship.getWeapons().get(a).getActEnergy() > 0){
                            ship.getWeapons().get(a).setActEnergy(ship.getWeapons().get(a).getActEnergy()-1);
                            for (int i = 0; i < ship.getComponents().size(); i++) {
                                if(ship.getComponents().get(i) instanceof ShipWeapons){
                                    ship.getComponents().get(i).setActEnergy(ship.getComponents().get(i).getActEnergy()-1);  
                                }
                            }
                        }
                    }
                }else{
                    selectedWeapon = ship.getWeapons().get(a);
                }
                return;
            }
            if(enemyShip.contains(e.getPoint())){
                for (int i = 0; i < ship.getWeapons().size(); i++) {
                    //TODO make target
//                  ship.getWeapons().get(i).setTarget(e.getX())
                    if(selectedWeapon != null && ship.getWeapons().get(i).getId() == selectedWeapon.getId() && ship.getWeapons().get(i).getActCooldown() == ship.getWeapons().get(i).getCooldown()){
                        ship.getWeapons().set(i, fireWeapon(ship.getWeapons().get(i)));
                        selectedWeapon = null;
                    }
                }
            }
            for (int i = 0; i < buttons.size(); i++) {
                if(buttons.get(i).getPoly().contains(e.getPoint())){
                    if(buttons.get(i).isActive()){
                        buttons.get(i).setActive(false);
                        if(buttons.get(i).getName().equals("Interior")){
                            ship.setShowInterior(false);
                            break;
                        }
                    }else{
                        buttons.get(i).setActive(true);
                        if(buttons.get(i).getName().equals("Interior")){
                            ship.setShowInterior(true);
                            break;
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    }
    
    private ShipWeapon fireWeapon(ShipWeapon weapon){
        weapon.setActCooldown(0);
        weapon.setFiring(true);
        particles.add(new Shot(weapon.getName(), weapon.getX()+25, weapon.getY()+5, weapon.getId(), weapon.getSpeed()));
        //TODO attackselectedWeapon.getDmg();
        return weapon;
    }
}
