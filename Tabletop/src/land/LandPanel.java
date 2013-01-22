package land;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import territory.Dunedain;
import territory.Gladewood;
import territory.GreatPlain;
import territory.Pesten;
import territory.Skyrim;

public class LandPanel extends JPanel{

    public boolean isVisible = true;
    Polygon p = null;
    public Polygon polySkyrim = null;
    Polygon polyPest = null;
    Polygon polyDune = null;
    Polygon polyGlade = null;
    Polygon polyPlain = null;
    boolean menu = false;
    int landCounter = 0;
    public Skyrim skyrim = new Skyrim();
    public Gladewood glade = new Gladewood();
    public Dunedain dune = new Dunedain();
    public Pesten pest = new Pesten();
    public GreatPlain plain = new GreatPlain();
    int x = 100;
    int y = 0;
    
    int sx = 237;
    int sy = 118;
    
    int px = 154;
    int py = 428;
    
    int gx = 634;
    int gy = 101;
    
    int dx = 402;
    int dy = 342;
    
    int gpx = 267;
    int gpy = 243;
    
    public LandPanel(){
        int[] xCoords = new int[]{
                50, 60, 110, 120, 120, 110, 60, 50
            };
            int[] yCoords = new int[]{
                    50, 40, 40, 50, 70, 80, 80, 70
            };
        p = new Polygon(xCoords, yCoords, 8);
        this.setDoubleBuffered(true);
        initPolygons();
        this.setLayout(new BorderLayout());
        this.addMouseMotionListener(motionListener);
        this.addMouseListener(mouseListener);
    }
    
    public void paintComponent(Graphics g){
        if(isVisible){
            g.drawImage(this.getImage("img/frame/world.png"), 0, 0, null);
            g.drawImage(this.getImage("img/frame/infopanel.png"), 0, 500, null);
            if(landCounter == 0){
                g.drawString(new String("Skyrim"), sx, sy);
                g.drawString(new String("Pesten"), px, py);
                g.drawString(new String("Gladewood"), gx, gy);
                g.drawString(new String("Dunedain"), dx, dy);
                g.drawString(new String("Great Plain"), gpx, gpy);
            }else if (landCounter == 1){
                g.drawString(new String("SKYRIM"), sx, sy);
                g.drawString(new String("Pesten"), px, py);
                g.drawString(new String("Gladewood"), gx, gy);
                g.drawString(new String("Dunedain"), dx, dy);
                g.drawString(new String("Great Plain"), gpx, gpy);
            }else if (landCounter == 2){
                g.drawString(new String("Skyrim"), sx, sy);
                g.drawString(new String("PESTEN"), px, py);
                g.drawString(new String("Gladewood"), gx, gy);
                g.drawString(new String("Dunedain"), dx, dy);
                g.drawString(new String("Great Plain"), gpx, gpy);
            }else if (landCounter == 3){
                g.drawString(new String("Skyrim"), sx, sy);
                g.drawString(new String("Pesten"), px, py);
                g.drawString(new String("GLADEWOOD"), gx, gy);
                g.drawString(new String("Dunedain"), dx, dy);
                g.drawString(new String("Great Plain"), gpx, gpy);
            }else if (landCounter == 4){
                g.drawString(new String("Skyrim"), sx, sy);
                g.drawString(new String("Pesten"), px, py);
                g.drawString(new String("Gladewood"), gx, gy);
                g.drawString(new String("DUNEDAIN"), dx, dy);
                g.drawString(new String("Great Plain"), gpx, gpy);
            }else if (landCounter == 5){
                g.drawString(new String("Skyrim"), sx, sy);
                g.drawString(new String("Pesten"), px, py);
                g.drawString(new String("Gladewood"), gx, gy);
                g.drawString(new String("Dunedain"), dx, dy);
                g.drawString(new String("GREAT PLAIN"), gpx, gpy);
            }
        }
        if(menu){
            g.drawImage(new ImageIcon("img/button/exit.png").getImage(), x , y, null);
            if(y < 200){
                y = y +1;
                repaint();
            }
        }
    }
    
    public void showMenu(){
        menu = true;
        repaint();
    }
    
    private void initPolygons(){
        int[] xCoords = new int[]{
                sx-5, sx+50, sx+50, sx-5
            };
            int[] yCoords = new int[]{
                    sy-10, sy-10, sy+15, sy+15
            };
        polySkyrim = new Polygon(xCoords, yCoords, 4);
        
        xCoords = new int[]{
                px-5, px+50, px+50, px-5
            };
       yCoords = new int[]{
                    py-10, py-10, py+15, py+15
            };
        polyPest = new Polygon(xCoords, yCoords, 4);
        
        xCoords = new int[]{
                dx-5, dx+50, dx+50, dx-5
            };
       yCoords = new int[]{
                    dy-10, dy-10, dy+15, dy+15
            };
        polyDune = new Polygon(xCoords, yCoords, 4);
        
        xCoords = new int[]{
                gx-5, gx+50, gx+50, gx-5
            };
       yCoords = new int[]{
                    gy-10, gy-10, gy+15, gy+15
            };
        polyGlade = new Polygon(xCoords, yCoords, 4);
        
        xCoords = new int[]{
                gpx-5, gpx+50, gpx+50, gpx-5
            };
       yCoords = new int[]{
                    gpy-10, gpy-10, gpy+15, gpy+15
            };
        polyPlain = new Polygon(xCoords, yCoords, 4);
    }
    
    private void changePanel(){
        if(landCounter == 1){
            this.isVisible = false;
            this.remove(dune);
            this.remove(plain);
            this.remove(glade);
            this.remove(pest);
            this.add(skyrim, BorderLayout.CENTER);
            skyrim.setActiveMode(true);
        }else
        if(landCounter == 2){
            this.isVisible = false;
            this.remove(dune);
            this.remove(plain);
            this.remove(glade);
            this.remove(skyrim);
            this.add(pest, BorderLayout.CENTER);
            pest.setActiveMode(true);
        }else
        if(landCounter == 3){
            this.isVisible = false;
            this.remove(dune);
            this.remove(plain);
            this.remove(skyrim);
            this.remove(pest);
            this.add(glade, BorderLayout.CENTER);
            glade.setActiveMode(true);
        }else
        if(landCounter == 4){
            this.isVisible = false;
            this.remove(skyrim);
            this.remove(plain);
            this.remove(glade);
            this.remove(pest);
            this.add(dune, BorderLayout.CENTER);
            dune.setActiveMode(true);
        }else
        if(landCounter == 5){
            this.isVisible = false;
            this.remove(dune);
            this.remove(skyrim);
            this.remove(glade);
            this.remove(pest);
            this.add(plain, BorderLayout.CENTER);
            plain.setActiveMode(true);
        }
    }
        
    private MouseListener mouseListener = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if(isVisible){
                landCounter = 0;
                if(polySkyrim.contains(e.getPoint())){
                    landCounter = 1;
                    changePanel();
                }else
                if(polyPest.contains(e.getPoint())){
                    landCounter = 2;
                    changePanel();
                }else
                if(polyGlade.contains(e.getPoint())){
                    landCounter = 3;
                    changePanel();
                }else
                if(polyDune.contains(e.getPoint())){
                    landCounter = 4;
                    changePanel();
                }else
                if(polyPlain.contains(e.getPoint())){
                    landCounter = 5;
                    changePanel();
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
    };
    
    private MouseMotionListener motionListener = new MouseMotionListener() {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            if(isVisible){
                landCounter = 0;
                if(polySkyrim.contains(e.getPoint())){
                    landCounter = 1;
                }else
                if(polyPest.contains(e.getPoint())){
                    landCounter = 2;
                }else
                if(polyGlade.contains(e.getPoint())){
                    landCounter = 3;
                }else
                if(polyDune.contains(e.getPoint())){
                    landCounter = 4;
                }else
                if(polyPlain.contains(e.getPoint())){
                    landCounter = 5;
                }
                repaint();  
            }
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            
        }
    };
    
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
