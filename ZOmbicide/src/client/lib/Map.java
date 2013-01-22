package client.lib;

import java.util.Random;

public class Map {

    private Obsticle[][] fields;
    private int x;
    private int y;
    
    public Map(int i, int j){
        this.fields = new Obsticle[i/Constants.SIZE*Constants.MAP_SIZE][j/Constants.SIZE*Constants.MAP_SIZE];
        this.x = 0;
        this.y = 0;
        this.generateFields();
    }
    
    private void generateFields(){
        Random ran = new Random();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if(i*Constants.SIZE >= x && i*Constants.SIZE <= x+Constants.WIDTH && j*Constants.SIZE >= y && j*Constants.SIZE <= y+Constants.HEIGTH){
                    fields[i][j] = new Obsticle(i*Constants.SIZE, j*Constants.SIZE, ran.nextInt(Constants.OBSTICLES.size()+Constants.DENSITY), true, true);
                    if(fields[i][j].getId() == 1){
                        fields[i][j].setWalkable(false);
                    }
                }else{
                    fields[i][j] = new Obsticle(i*Constants.SIZE, j*Constants.SIZE, ran.nextInt(Constants.OBSTICLES.size()+Constants.DENSITY), false, true);
                    if(fields[i][j].getId() == 1){
                        fields[i][j].setWalkable(false);
                    }
                }
            }
        }
    }

    public Obsticle[][] getFields() {
        return fields;
    }

    public void setFields(Obsticle[][] fields) {
        this.fields = fields;
    }

    public void scrollLevelDown(int speed) {
        y += speed;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                fields[i][j].scrollElementDown(speed);
            }
        }
    }

    public void scrollLevelUp(int speed) {
        y -= speed;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                fields[i][j].scrollElementUp(speed);
            }
        }
    }

    public void scrollLevelRight(int speed) {
        x += speed;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                fields[i][j].scrollElementRight(speed);
            }
        }
    }

    public void scrollLevelLeft(int speed) {
        x -= speed;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                fields[i][j].scrollElementLeft(speed);
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }    
    
}
