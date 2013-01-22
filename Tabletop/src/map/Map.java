package map;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Map extends JPanel {

    Image[][] background = new Image[24][18];
    
    public Map(){
        fillMap();
    }
    
    public void paintComponent(Graphics g){
        g.drawImage(getImage("img/map/map.png"), 0, 0, null);
        for (int i = 0; i < background.length; i++) {
            for (int j = 0; j < background[i].length; j++) {
                g.drawImage(background[i][j], i*32, j*32, null);
            }
        }
    }
    
    private void fillMap(){
        Random ran = new Random();
        for (int i = 0; i < background.length; i++) {
            for (int j = 0; j < background[i].length; j++) {
                int bla = ran.nextInt(12);
                if (bla == 0 || bla == 1 || bla == 2) {
                    background[i][j] = new ImageIcon("img/map/grass.png").getImage();
                } else if (bla == 3 || bla == 4 || bla == 5) {
                    background[i][j] = new ImageIcon("img/map/grass2.png").getImage();
                } else if (bla == 6 || bla == 7 || bla == 8 || bla == 9) {
                    background[i][j] = new ImageIcon("img/map/grass3.png").getImage();
                } else if (bla == 10 || bla == 11) {
                    background[i][j] = new ImageIcon("img/map/tree.png").getImage();
                }
            }
        }

    }
    
    private Image getImage(String path){
        URL url = null;
        url = this.getClass().getClassLoader().getResource(path);
        if(url != null){
            return new ImageIcon(url).getImage();
        }else{
            return new ImageIcon(path).getImage();
        }
    }
    
}
