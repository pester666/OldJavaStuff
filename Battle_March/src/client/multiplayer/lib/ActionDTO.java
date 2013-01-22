package client.multiplayer.lib;

public class ActionDTO extends TransferDTO {

    protected int clickX;
    protected int clickY;
    
    /**
     * Konstruktor f�r
     * Action-DTO
     */
    public ActionDTO(String playerName, int clickX, int clickY){
        this.playerName = playerName;
        this.clickX = clickX;
        this.clickY = clickY;
    }
}
