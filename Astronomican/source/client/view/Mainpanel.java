package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.lib.Asteroid;
import client.lib.Ship;

public class Mainpanel extends JPanel implements Runnable{

    private int density = 50;
    private Star[] stars;
    private Asteroid[] asteroids;
    private int windowSize = 500;
    private Random ran;
    private Ship ship;
    
    
    public Mainpanel(){
        this.setSize(windowSize, windowSize);
        this.setPreferredSize(new Dimension(windowSize, windowSize));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        stars = new Star[density];
        asteroids = new Asteroid[density/10];
        ran = new Random();
        ship = new Ship(50, 50, 5);
        
        for (int i = 0; i < stars.length; i++) {
            int s = ran.nextInt(5) + 1;
            stars[i] = new Star(s,s,(windowSize/density)*i,ran.nextInt(windowSize));
        }        
        for (int i = 0; i < asteroids.length; i++) {
            int s = ran.nextInt(5) + 1;
            asteroids[i] = new Asteroid(500+s*40, ran.nextInt(windowSize), (6-s)*20, s, s);
        }
    }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, windowSize, windowSize);
        for (int i = 0; i < stars.length; i++) {
            g.setColor(Color.WHITE);
            g.fillOval(stars[i].x - stars[i].speed, stars[i].y, stars[i].size, stars[i].size);
            stars[i].x = stars[i].x - stars[i].speed;
        }
        for (int i = 0; i < asteroids.length; i++) {
            g.setColor(Color.WHITE);
            if(asteroids[i].hp < 0){
                g.drawImage(new ImageIcon("images/env/Kaboom.png").getImage(), asteroids[i].x - asteroids[i].speed, asteroids[i].y, asteroids[i].size, asteroids[i].size, null);
                asteroids[i].x = asteroids[i].x - asteroids[i].speed;  
                for (int j = 0; j < asteroids[j].p.xpoints.length; j++) {
                    asteroids[i].p.xpoints[j] -= asteroids[i].speed;
                }
            }else{
                g.drawImage(new ImageIcon("images/env/Asteroid.png").getImage(), asteroids[i].x - asteroids[i].speed, asteroids[i].y, asteroids[i].size, asteroids[i].size, null);
                asteroids[i].x = asteroids[i].x - asteroids[i].speed;  
                for (int j = 0; j < asteroids[i].p.xpoints.length; j++) {
                    asteroids[i].p.xpoints[j] -= asteroids[i].speed;
                }
            }
        }
        if( ! ship.getLaser().isEmpty()){
            for (int i = 0; i < ship.getLaser().size(); i++) {
                g.setColor(ship.getLaser().get(i).color);
                g.fillRect(ship.getLaser().get(i).x, ship.getLaser().get(i).y, 20, 3);
                ship.getLaser().get(i).x += ship.getLaser().get(i).speed;
                if(ship.getLaser().get(i).x > windowSize){
                    ship.getLaser().remove(i);
                }
            }
        }
        if(ship.anim == 0){
            g.drawImage(new ImageIcon("images/ships/cruiserOff.png").getImage(), ship.x, ship.y, null);   
            ship.anim++;
        }else{
            g.drawImage(new ImageIcon("images/ships/cruiserON.png").getImage(), ship.x, ship.y, null);
            ship.anim = 0;
        }
        
        this.checkCollision();
        
        for (int j = 0; j < stars.length; j++) {
            if(stars[j].x < 0){
                int s = ran.nextInt(5) + 1;
                stars[j] = new Star(s,s,windowSize+10,ran.nextInt(windowSize));
            }
        }
        for (int j = 0; j < asteroids.length; j++) {
            if(asteroids[j].hp < 0){
                asteroids[j].hp--;
                if(asteroids[j].hp < 5){
                    int s = ran.nextInt(5) + 1;
                    asteroids[j] = new Asteroid(windowSize+10, ran.nextInt(windowSize), (6-s)*20, s, s);
                }
            }else if(asteroids[j].x+asteroids[j].size < 0){
                int s = ran.nextInt(5) + 1;
                asteroids[j] = new Asteroid(windowSize+10, ran.nextInt(windowSize), (6-s)*20, s, s);
            }
        }
        for (int i = 0; i < ship.getLaser().size(); i++) {
            if(ship.getLaser().get(i).x > windowSize){
                ship.getLaser().remove(i);
            }
        }
    }

    @Override
    public void run() {
        while(true){
            ship.moveShip();
            this.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void checkCollision(){
        for (int i = 0; i < asteroids.length; i++) {
            if(asteroids[i].hp > 0){
                if(asteroids[i].p.contains(ship.p.getBounds())){
                    /* TODO 29.03.2012, HolmeieP,  */
                    System.out.println("SHIP HIT!");
                }
                for (int j = 0; j < ship.getLaser().size(); j++) {
                    if(asteroids[i].p.contains(ship.getLaser().get(j).p.getBounds())){
                        asteroids[i].hp -= ship.getLaser().get(j).strength;
                        ship.getLaser().remove(j);
                    }
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

    public Ship getShip() {
        return ship;
    }
}
