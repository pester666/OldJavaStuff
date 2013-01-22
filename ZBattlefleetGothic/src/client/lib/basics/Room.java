package client.lib.basics;



public class Room {
//a ship is made of rooms and shrooms
    
    protected int length;
    protected int heigth;
    protected String name;
    protected int hp;
    protected boolean hasAirSupply;
    protected int x;
    protected int y;
    protected int id;
    protected Tile[] tiles;
    
    public Room(int length, int heigth, int hp, int x, int y, int id, boolean isSmall, boolean horizontal) {
        super();
        this.length = length;
        this.heigth = heigth;
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.id = id;
        if(isSmall){
            tiles = new Tile[2];
            if(horizontal){
                tiles[0] = new Tile(x+1, y+1, 0);
                tiles[1] = new Tile(x+25, y+1, 0);
            }else{
                tiles[0] = new Tile(x+1, y+1, 0);
                tiles[1] = new Tile(x+1, y+25, 0);
            }
        }else{
            tiles = new Tile[4];
            tiles[0] = new Tile(x+1, y+1, 0);
            tiles[1] = new Tile(x+25, y+1, 0);
            tiles[2] = new Tile(x+1, y+25, 0);
            tiles[3] = new Tile(x+25, y+25, 0);
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isHasAirSupply() {
        return hasAirSupply;
    }

    public void setHasAirSupply(boolean hasAirSupply) {
        this.hasAirSupply = hasAirSupply;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
    
    
}
