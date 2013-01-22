package client.multiplayer.lib;

public class ChatmessageDTO extends TransferDTO {

    private String chatMessage;
    
    /**
     * Konstruktor für
     * Chat-Message DTO
     */
    public ChatmessageDTO(String playerName, String chatMessage){
        this.type = 0;
        this.playerName = playerName;
        this.chatMessage = chatMessage;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
    
    
}
