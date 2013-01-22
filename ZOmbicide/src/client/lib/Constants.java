package client.lib;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Constants {

    public static int TIMELEFT = 600;
    public static int TIMEWIN = 1800;
    public static int WIDTH = 800;
    public static int HEIGTH = 600;
    public static int DENSITY = 5;
    public static int SIZE = 64;
    public static int MAP_SIZE = 3;
    public static int SCROLL_DISTANCE = 200;
    
    public static Image IMG_PLAYER = new ImageIcon("res/img/player.png").getImage();
    public static java.util.Map<Integer, Image> OBSTICLES = new java.util.HashMap<Integer, Image>();

}
