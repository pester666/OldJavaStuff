package lib;

public class Player {

    private Unit[] units = null;
    private String name = "";
    private int points = 0;
    private String race = "";
    private boolean isReady = false;
    
    public Player(Unit[] units, String name, int points, String race, boolean isReady) {
        super();
        this.units = units;
        this.name = name;
        this.points = points;
        this.race = race;
        this.isReady = isReady;
    }

    public Unit[] getUnits() {
        return units;
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }
}
