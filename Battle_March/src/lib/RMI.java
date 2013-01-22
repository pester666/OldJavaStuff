package lib;

import client.menu.TileScreenFrame;

public class RMI {
    
    public static final String WHO = "who";
    public static final String ADD = "add";
    public static final String REM = "rem";
    public static final String AGE = "age";
    public static final String POINTS = "pts";
    public static final String READY = "rdy";
    public static final String UNREADY = "unrdy";
    public static final String ALL_READY = "allrdy";
    public static final String RACE = "race";
    public static final String START = "start";
    public static final String GET_I = "getI";
    
    public static String GET_PLAYERS = Constants.MSG_INFO + 
                                                Constants.MSG_SPLITTER + 
                                                TileScreenFrame.myself.getName() + 
                                                Constants.MSG_SPLITTER + 
                                                RMI.WHO;
    
    public static final String REMOVE_PLAYER = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.REM;
    
    public static String ADD_PLAYER = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.ADD;
    
    public static String CHANGE_AGE = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.AGE;

    public static String CHANGE_POINTS = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.POINTS;
    
    public static String MAKE_PLAYER_READY = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.READY;
    
    public static String MAKE_PLAYER_UNREADY = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.UNREADY;
    
    public static String CHANGE_RACE = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.RACE;
    
    public static String START_GAME = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.START;
    
    public static final String GET_PLAYER_I = Constants.MSG_INFO + 
    Constants.MSG_SPLITTER + 
    TileScreenFrame.myself.getName() + 
    Constants.MSG_SPLITTER + 
    RMI.GET_I +
    Constants.MSG_SPLITTER;
}

