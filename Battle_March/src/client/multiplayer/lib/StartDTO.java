package client.multiplayer.lib;

public class StartDTO extends TransferDTO {

    private boolean arePlayersReady;
    private boolean start;  
    
    /**
     * Konstruktor für
     * Start DTO
     */
    public StartDTO(String playerName, boolean checkIfPlayersReady, boolean start){
        this.type = 3;
        this.playerName = playerName;
        this.arePlayersReady = checkIfPlayersReady;
        this.start = start;
    }

    public boolean isArePlayersReady() {
        return arePlayersReady;
    }

    public void setArePlayersReady(boolean arePlayersReady) {
        this.arePlayersReady = arePlayersReady;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
    
    
}
