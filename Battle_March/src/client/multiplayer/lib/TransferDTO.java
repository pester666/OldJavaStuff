package client.multiplayer.lib;

import java.io.Serializable;

public abstract class TransferDTO implements Serializable{

    protected int type;
    protected String playerName;
                
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
