package client.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import lib.Constants;

import client.multiplayer.JoinPanel;
import client.multiplayer.LobbyPanelHost;
import client.multiplayer.LobbyPanelJoin;
import lib.Player;

@SuppressWarnings("serial")
public class TileScreenFrame extends JFrame {
    
    public static Player myself = new Player(null, "Spieler", 0, Constants.RACE_ORKS, false);
    private MainMenuPanel pnlMain = new MainMenuPanel();
    
    public TileScreenFrame(){
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sanacorp Tabletop");
        this.add(pnlMain);
        this.setVisible(true);
        this.setResizable(false);
        this.addKeyListener(new KeyListener(){

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if(JoinPanel.isVisible){
                    if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                        if(pnlMain.pnlMulti.pnlJoin.ipAdress.length()-1 >= 0){
                            pnlMain.pnlMulti.pnlJoin.ipAdress = pnlMain.pnlMulti.pnlJoin.ipAdress.substring(0, pnlMain.pnlMulti.pnlJoin.ipAdress.length()-1);
                            pnlMain.pnlMulti.pnlJoin.updateShownIp();
                        }
                    }else
                    if(e.getKeyChar() == KeyEvent.VK_ENTER){
                        pnlMain.pnlMulti.pnlJoin.pressJoin();
                    }else{
                        pnlMain.pnlMulti.pnlJoin.ipAdress = pnlMain.pnlMulti.pnlJoin.ipAdress + e.getKeyChar();
                        pnlMain.pnlMulti.pnlJoin.updateShownIp();
                    }
                }else if(LobbyPanelHost.isVisible){
                    if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                        if(pnlMain.pnlMulti.pnlLobby.chatTxt.length()-1 >= 0){
                            pnlMain.pnlMulti.pnlLobby.chatTxt = pnlMain.pnlMulti.pnlLobby.chatTxt.substring(0, pnlMain.pnlMulti.pnlLobby.chatTxt.length()-1);
                            pnlMain.pnlMulti.pnlLobby.updateChat();
                        }
                    }else
                    if(e.getKeyChar() == KeyEvent.VK_ENTER){
                        pnlMain.pnlMulti.pnlLobby.sendMessage(pnlMain.pnlMulti.pnlLobby.chatTxt);
                    }else{
                        pnlMain.pnlMulti.pnlLobby.chatTxt = pnlMain.pnlMulti.pnlLobby.chatTxt + e.getKeyChar();
                        pnlMain.pnlMulti.pnlLobby.updateChat();
                    }
                }else if(OptionsPanel.isVisible && OptionsPanel.isNameChanging){
                    if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                        if(pnlMain.pnlOpti.actName.length()-1 >= 0){
                            pnlMain.pnlOpti.actName = pnlMain.pnlOpti.actName.substring(0, pnlMain.pnlOpti.actName.length()-1);
                            pnlMain.pnlOpti.updatePlayername();
                        }    
                    }else
                    if(e.getKeyChar() == KeyEvent.VK_ENTER){
                        TileScreenFrame.myself.setName(pnlMain.pnlOpti.actName);
                        OptionsPanel.isNameChanging = false;
                        pnlMain.pnlOpti.updatePlayername();
                    }else{
                        pnlMain.pnlOpti.actName = pnlMain.pnlOpti.actName + e.getKeyChar();
                        pnlMain.pnlOpti.updatePlayername();
                    }
                }else if(LobbyPanelJoin.isVisible){
                    if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                        if(pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.chatTxt.length()-1 >= 0){
                            pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.chatTxt = pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.chatTxt.substring(0, pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.chatTxt.length()-1);
                            pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.updateChat();
                        }
                    }else
                    if(e.getKeyChar() == KeyEvent.VK_ENTER){
                        pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.sendMessage(pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.chatTxt);
                    }else{
                        pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.chatTxt = pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.chatTxt + e.getKeyChar();
                        pnlMain.pnlMulti.pnlJoin.pnlLobbyJoin.updateChat();
                    }
                }
            }
            
        });
    }
}
