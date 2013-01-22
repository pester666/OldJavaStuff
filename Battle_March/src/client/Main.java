package client;

import client.menu.TileScreenFrame;
import client.multiplayer.game.MultiplayerFrame;
import client.singleplayer.SingleplayerFrame;
import lib.Constants;
import lib.Player;

public class Main {

    static TileScreenFrame tsf = null;
    static SingleplayerFrame sf = null;
    static MultiplayerFrame mf = null;
    
    public static void main(String[] args) {
        Main m = new Main();
        if(args != null && args.length > 0){
            m.init(Integer.parseInt(args[0]));    
        }else{
            m.init(Constants.MENU_MENU);
        }
    }

    private void init(int selection) {
        if(selection == Constants.MENU_MENU){
            tsf = new TileScreenFrame();    
        }else if(selection == Constants.MENU_SINGEPLAYER){

        }
    }
    
    public static void launchSingleplayer(int points, int age){
        tsf.setVisible(false);
        sf = new SingleplayerFrame();
        sf.createFrame(points, age);   
    }
    
    public static void showMenu(){
        tsf.setVisible(true);
    }
    
    public static void launchMultiplayer(Player[] players, int age, boolean isHost, String ipAdress){
        tsf.setVisible(false);
        mf = new MultiplayerFrame(players, isHost, ipAdress);
        mf.createFrame(age);  
    }
}
