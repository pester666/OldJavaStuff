package client.multiplayer.lib;

public class ChangePlayerDTO extends TransferDTO {

    private boolean add;
    
    /**
     * Konstruktor für
     * Chat-Message DTO
     */
    public ChangePlayerDTO(String playerName, boolean add){
        this.type = 0;
        this.playerName = playerName;
        this.add = add;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    
}
