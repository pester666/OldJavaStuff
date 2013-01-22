package client.runtime;

import java.util.Timer;
import java.util.TimerTask;

import client.lib.Constants;

public class Logic {

    public Logic(){
        Timer time = new Timer();
        time.schedule(new Task(), 2000, 1000);
    }
    
    private class Task extends TimerTask{

        @Override
        public void run() {
            Constants.TIMELEFT--;
        }
    }
}
