package minesweeper;

public class Field {

    private boolean isMine = false;
    private boolean isFlagged = false;
    private boolean isRevealed = false;
    private int minesAround = 0;
    private String name = "";
    private boolean isRightClicked = false;
    private int x = 0;
    private int y = 0;
    private boolean isInList = false;
    
    public boolean isInList() {
        return isInList;
    }

    public void setInList(boolean isInList) {
        this.isInList = isInList;
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

    public Field(boolean isMine){
        this.isMine = isMine;
    }
       
    public boolean isRightClicked() {
        return isRightClicked;
    }

    public void setRightClicked(boolean isRightClicked) {
        this.isRightClicked = isRightClicked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean isFlagged) {
        this.isFlagged = isFlagged;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean isRevealed) {
        this.isRevealed = isRevealed;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }
}
