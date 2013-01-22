package lib;

import java.io.Serializable;

public class State implements Serializable{

    public static int CONCIOUS = 0;
    public static int KNOCKED_DOWN = 5;
    public static int STUNNED = 10;
    public static int OUT_OF_ACTION = 666;
}
