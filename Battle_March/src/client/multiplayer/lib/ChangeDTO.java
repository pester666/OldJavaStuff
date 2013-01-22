package client.multiplayer.lib;

public class ChangeDTO extends TransferDTO {

    private int changeAge;
    private int changePoints;
    private int changeRace;
    
    /**
     * Konstruktor für
     * Change DTO
     */
    public ChangeDTO(String playerName, int changeAge, int changePoints, int changeRace){
        this.type = 2;
        this.playerName = playerName;
        this.changeAge = changeAge;
        this.changePoints = changePoints;
        this.changeRace = changeRace;
    }

    public int getChangeAge() {
        return changeAge;
    }

    public void setChangeAge(int changeAge) {
        this.changeAge = changeAge;
    }

    public int getChangePoints() {
        return changePoints;
    }

    public void setChangePoints(int changePoints) {
        this.changePoints = changePoints;
    }

    public int getChangeRace() {
        return changeRace;
    }

    public void setChangeRace(int changeRace) {
        this.changeRace = changeRace;
    }
    
    
}
