package minesweeper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{

    private final Image field = new ImageIcon("src/minesweeper/resources/field.PNG").getImage();
    private final Image field_empty = new ImageIcon("src/minesweeper/resources/field_empty.PNG").getImage();
    private final Image field_mine = new ImageIcon("src/minesweeper/resources/field_mine.PNG").getImage();
    private final Image field_one = new ImageIcon("src/minesweeper/resources/field_one.PNG").getImage();
    private final Image field_two = new ImageIcon("src/minesweeper/resources/field_two.PNG").getImage();
    private final Image field_three = new ImageIcon("src/minesweeper/resources/field_three.PNG").getImage();
    private final Image field_four = new ImageIcon("src/minesweeper/resources/field_four.PNG").getImage();
    private final Image field_five = new ImageIcon("src/minesweeper/resources/field_five.PNG").getImage();
    private final Image field_flag = new ImageIcon("src/minesweeper/resources/field_flag.PNG").getImage();
    private final Image field_clicked = new ImageIcon("src/minesweeper/resources/field_clicked.PNG").getImage();
    private int mineCount = 0;
    private int diff = 1;
    private int minesLeft = 0;
    private int time = 0;
    private int realMinesLeft = 9999;
    private int size = 32;
    Status s;
    Field[][] fields = new Field[10][10];
    ClickListener cl;
    private List<Field> fieldArray = new ArrayList<Field>();
    
    public Panel(){
        this.resetBoard(this.diff);
        this.s = new Status(this.minesLeft, this.time);
        cl = new ClickListener();
        this.addMouseListener(cl);
    }
    
    public Status getStatusPanel(){
        return s;
    }
    
    @SuppressWarnings("deprecation")
    public void resetBoard(int i) {
        diff = i;
        if(diff == 1){
            mineCount = 10;
            minesLeft = 10;
            realMinesLeft = 10;
            time = 0;
            fields = new Field[8][8];
        }else
        if(diff == 2){
            mineCount = 40;
            minesLeft = 40;
            realMinesLeft = 40;
            time = 0;
            fields = new Field[16][16];
        }else
        if(diff == 3){
            mineCount = 99;
            minesLeft = 99;
            realMinesLeft = 99;
            time = 0;
            fields = new Field[30][16];
        }
        if(s != null){
            this.s.reset(minesLeft);   
            if(t != null){
                this.t.stop();
                t = null;    
            }
        }
        if(cl != null){
            cl.reset();
        }
        this.drawBoardAndSetMines();
    }
    private Random ran = new Random();
    private void drawBoardAndSetMines(){    
        while(hasGeneratedMine()){
            
        }
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if(fields[i][j] == null){
                    fields[i][j] = new Field(false);
                    fields[i][j].setMinesAround(this.getMinesAroundInt(i, j));
                    fields[i][j].setName(this.getMinesAround(i, j));
                }
            }
        }
        this.repaint();
    }
    
    public int getFieldSize(){
        return size;
    }
    
    private boolean hasGeneratedMine(){
        if(mineCount <= 0){
            return false;
        }
        int x = ran.nextInt(fields.length);
        int y = ran.nextInt(fields[0].length);
        if(fields[x][y] == null && this.getMinesAroundInt(x, y) <= 5){
            fields[x][y] = new Field(true);
            mineCount--;
        }
        return true;
    }
    
    public void paintComponent(Graphics g){
        if(fields[0] != null){
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {
                    if(fields[i][j].isRightClicked()){
                        g.drawImage(field_clicked, i*size, j*size, size, size, null);
                    }else{
                        if(fields[i][j].isRevealed()){
                            if(fields[i][j].isMine()){
                                g.drawImage(field_mine, i*size, j*size, size, size,  null);
                            }else{
                                if(fields[i][j].getName().equals("one")){
                                    g.drawImage(field_one, i*size, j*size, size, size,  null);
                                }else if(fields[i][j].getName().equals("two")){
                                    g.drawImage(field_two, i*size, j*size, size, size,  null);
                                }else if(fields[i][j].getName().equals("three")){
                                    g.drawImage(field_three, i*size, j*size, size, size,  null);
                                }else if(fields[i][j].getName().equals("four")){
                                    g.drawImage(field_four, i*size, j*size, size, size,  null);
                                }else if(fields[i][j].getName().equals("five")){
                                    g.drawImage(field_five, i*size, j*size, size, size,  null);
                                }else if(fields[i][j].getName().equals("empty")){
                                    g.drawImage(field_empty, i*size, j*size, size, size,  null);
                                }
                            }
                        }else{
                            if(fields[i][j].isFlagged()){
                                g.drawImage(field_flag, i*size, j*size, size, size,  null);
                            }else{
                                g.drawImage(field, i*size, j*size, size, size,  null);    
                            }
                            
                        }
                    }
                }
            }
        }
    }
    
    private void checkVictory(){
        if(realMinesLeft == 0){
            JOptionPane.showMessageDialog(this,"Close Enough!");
            this.resetBoard(diff);
        }
    }
    
    public void setFlag(int x, int y){
        fields[x][y].setRightClicked(false);
        if( ! fields[x][y].isRevealed()){
            if(fields[x][y].isFlagged()){
                fields[x][y].setFlagged(false);
                if(fields[x][y].isMine()){
                    this.realMinesLeft++;
                }
                this.minesLeft++;
            }else{
                if(minesLeft > 0){
                    fields[x][y].setFlagged(true);
                    if(fields[x][y].isMine()){
                        this.realMinesLeft--;
                    }
                    this.minesLeft--;
                }
            }
            this.s.setMinesLeft(minesLeft);
            System.out.println("RML: " + this.realMinesLeft);
            this.checkVictory();
        }
    }
    
    public void clickRight(int x, int y, boolean isClicking){
        if( ! fields[x][y].isRevealed()){
            fields[x][y].setRightClicked(isClicking);    
        }
    }
    
    public void clickLeft(int x, int y){
        fieldArray.clear();
        fields[x][y].setRightClicked(false);
        fields[x][y].setRevealed(true);
        if(fields[x][y].isMine()){
            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {
                    if(fields[i][j].isMine()){
                        fields[i][j].setRevealed(true);
                    }
                }
            }
            JOptionPane.showMessageDialog(this,"Y U NO !");
            this.resetBoard(this.diff);
        }else{
            checkNextFieldIsEmpty(x, y, 0);    
        }
    }
    
    private void checkNextFieldIsEmpty(int x, int y, int emptyCount){
        int i = 0;
        int j = -1;
//        int empty = 0;
        if ((x + i) < fields.length && (y + j) < fields[0].length && (x + i) >= 0 && (y + j) >= 0) {
            if(fields[x + i][y + j] != null && !fields[x + i][y + j].isFlagged() && !fields[x + i][y + j].isMine() && ! fields[x + i][y + j].isRevealed()){
                if(fields[x + i][y + j].getMinesAround() == 0 && ! fields[x + i][y + j].isInList()){
                    fields[x + i][y + j].setX(x+i);
                    fields[x + i][y + j].setY(y+j);
                    fields[x + i][y + j].setInList(true);
                    fieldArray.add(fields[x + i][y + j]);
//                    fields[x + i][y + j].setRevealed(true);
//                    emptyCount++;
//                    empty++;
                    checkNextFieldIsEmpty(x+i, y+j, emptyCount);
                    emptyCount = fieldArray.size();
                }else if(emptyCount > 1){
                    fields[x + i][y + j].setRevealed(true);
                }
            }
        }

        j = 1;
        if ((x + i) < fields.length && (y + j) < fields[0].length && (x + i) >= 0 && (y + j) >= 0) {
            if(fields[x + i][y + j] != null && !fields[x + i][y + j].isFlagged() && !fields[x + i][y + j].isMine() && ! fields[x + i][y + j].isRevealed()){
                if(fields[x + i][y + j].getMinesAround() == 0 && ! fields[x + i][y + j].isInList()){
                    fields[x + i][y + j].setX(x+i);
                    fields[x + i][y + j].setY(y+j);
                    fields[x + i][y + j].setInList(true);
                    fieldArray.add(fields[x + i][y + j]);
//                    fields[x + i][y + j].setRevealed(true);
//                    emptyCount++;
//                    empty++;
                    checkNextFieldIsEmpty(x+i, y+j, emptyCount);
                    emptyCount = fieldArray.size();
                }else if(emptyCount > 1){
                    fields[x + i][y + j].setRevealed(true);
                }
            }
        }

        i = -1;
        j = 0;
        if ((x + i) < fields.length && (y + j) < fields[0].length && (x + i) >= 0 && (y + j) >= 0) {
            if(fields[x + i][y + j] != null && !fields[x + i][y + j].isFlagged() && !fields[x + i][y + j].isMine() && ! fields[x + i][y + j].isRevealed()){
                if(fields[x + i][y + j].getMinesAround() == 0 && ! fields[x + i][y + j].isInList()){
                    fields[x + i][y + j].setX(x+i);
                    fields[x + i][y + j].setY(y+j);
                    fields[x + i][y + j].setInList(true);
                    fieldArray.add(fields[x + i][y + j]);
//                    fields[x + i][y + j].setRevealed(true);
//                    emptyCount++;
//                    empty++;
                    checkNextFieldIsEmpty(x+i, y+j, emptyCount);
                    emptyCount = fieldArray.size();
                }else if(emptyCount > 1){
                    fields[x + i][y + j].setRevealed(true);
                }
            }
        }

        i = 1;
        if ((x + i) < fields.length && (y + j) < fields[0].length && (x + i) >= 0 && (y + j) >= 0) {
            if(fields[x + i][y + j] != null && !fields[x + i][y + j].isFlagged() && !fields[x + i][y + j].isMine() && ! fields[x + i][y + j].isRevealed()){
                if(fields[x + i][y + j].getMinesAround() == 0 && ! fields[x + i][y + j].isInList()){
                    fields[x + i][y + j].setX(x+i);
                    fields[x + i][y + j].setY(y+j);
                    fields[x + i][y + j].setInList(true);
                    fieldArray.add(fields[x + i][y + j]);
//                    fields[x + i][y + j].setRevealed(true);
//                    emptyCount++;
//                    empty++;
                    checkNextFieldIsEmpty(x+i, y+j, emptyCount);
                    emptyCount = fieldArray.size();
                }else if(emptyCount > 1){
                    fields[x + i][y + j].setRevealed(true);
                }
            }
        }
        if(fieldArray.size() > 2){
            for (int k = 0; k < fieldArray.size(); k++) {
                fields[fieldArray.get(k).getX()][fieldArray.get(k).getY()].setRevealed(true);
            }  
        }
    }
    
    private String getMinesAround(int x, int y){
        int count = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((x + i) < fields.length && (y + j) < fields[0].length && (x + i) >= 0 && (y + j) >= 0) {
                        if (fields[x + i][y + j] != null && fields[x + i][y + j].isMine()) {
                            count++;
                        }
                    }
                }
            }
        switch (count) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            default:
                break;
        }
        return "empty";
    }
    
    private int getMinesAroundInt(int x, int y){
        int count = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((x + i) < fields.length && (y + j) < fields[0].length && (x + i) >= 0 && (y + j) >= 0) {
                        if (fields[x + i][y + j] != null && fields[x + i][y + j].isMine()) {
                            count++;
                        }
                    }
                }
            }
            return count;
    }

    @Override
    public void run() {
        while(true){
            this.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private int clickedX;
    private int clickedY;
    private Thread t;
    
    private class ClickListener implements MouseListener{

        private boolean left = false;
        private boolean right = false;
        private boolean catcher = false;
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(t == null){
                t = new Thread(s);
                t.start();
            }
            clickedX = e.getX()/size;
            clickedY = e.getY()/size;
            if(insidePanel(clickedX, clickedY)){
                if(e.getButton() == MouseEvent.BUTTON1){
                    left = true;
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    right = true;
                }
                if(left && right){
                    markFields(e.getX()/size, e.getY()/size, true);
                }else{
                    clickRight(e.getX()/size, e.getY()/size, true);      
                }
            }
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(insidePanel(e.getX()/size, e.getY()/size)){
                if(left && right){
                    markFields(clickedX, clickedY, false);
                    catcher = true;
                }else{
                    if(catcher){
                        catcher = false;
                    }else{
                        if(clickedX != e.getX()/size || clickedY != e.getY()/size){
                            clickRight(clickedX, clickedY, false);    
                        }else{
                            if(e.getButton() == MouseEvent.BUTTON1){
                                clickLeft(e.getX()/size, e.getY()/size);
                            }else if(e.getButton() == MouseEvent.BUTTON3){
                                setFlag(e.getX()/size, e.getY()/size);
                            }
                        }
                    }
                }
                if(e.getButton() == MouseEvent.BUTTON1){
                    left = false;
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    right = false;
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
        public void reset(){
            left = false;
            right = false;
            catcher = false;
        }
    }
    
    private void markFields(int x, int y, boolean mark){
        int flagsAround = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((x + i) < fields.length && (y + j) < fields[0].length && (x + i) >= 0 && (y + j) >= 0) {
                    if (fields[x + i][y + j] != null){ 
                        if( ! fields[x + i][y + j].isRevealed() && ! fields[x + i][y + j].isFlagged()) {
                            fields[x + i][y + j].setRightClicked(mark);
                        }
                        if(fields[x + i][y + j].isFlagged()){
                            flagsAround++;
                        }
                    }
                }
            }
        }
        if(fields[x][y].getMinesAround() == flagsAround){
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((x + i) < fields.length && (y + j) < fields[0].length && (x + i) >= 0 && (y + j) >= 0) {
                        if (fields[x + i][y + j] != null && ! fields[x + i][y + j].isRevealed() && ! fields[x + i][y + j].isFlagged()){ 
                            clickLeft(x + i,y + j);
                        }
                    }
                }
            }
        }
    }
    
    private boolean insidePanel(int x, int y){
        if (x < fields.length && y < fields[0].length && x >= 0 && y >= 0){
            return true;
        }
        return false;
    }
    
}
