package client.multiplayer.lib;

public class InfomessageDTO extends TransferDTO {

    private String infoMessage;
    
    /**
     * Konstruktor für
     * Status-Message DTO
     */
    public InfomessageDTO(String playerName, String infoMessage, boolean isStatusDTO){
        this.type = 1;
        this.playerName = playerName;
        this.infoMessage = infoMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }
    
    
}
