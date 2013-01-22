package client.multiplayer.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import lib.Environment;

import lib.Unit;

import client.multiplayer.game.MultiplayerDeployPanel;

import client.menu.TileScreenFrame;
import lib.Constants;
import lib.Player;
import lib.RMI;

public class ChatClientGame {

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket sock;
    private Thread readerThread;
    private InputStreamReader streamReader;
    private String hostName;
    public String[] messages = new String[9];
    
    boolean isHost = false;

    public ChatClientGame(boolean isHost, String ipAdress) {
        this.isHost = isHost; 
        if (isHost) {
            hostName = "127.0.0.1";
        } else {
            hostName = ipAdress;
        }
    }

    public void printMessage(String message) {
        String[] frame = message.split(Constants.MSG_SPLITTER);
        if (frame[0].equals(Constants.MSG_SYSTEM)) {
            message = frame[1] + Constants.MSG_SPLITTER + frame[2];
        }else if(frame[0].equals(Constants.MSG_CHAT)){
            message = frame[1] + ": " + Constants.MSG_SPLITTER + frame[2];
        }else if(frame[0].equals(Constants.MSG_INFO)){
            
        }
        writer.println(message);
        writer.flush();
    }

    @SuppressWarnings("deprecation")
    public void closeConnection() {
        try {
            printMessage(RMI.REMOVE_PLAYER);
            printMessage(RMI.GET_PLAYERS);
            printMessage(Constants.MSG_SYSTEM + Constants.MSG_SPLITTER + TileScreenFrame.myself.getName() + Constants.MSG_SPLITTER + " has disconnected ...\n");
            if (readerThread != null) {
                readerThread.stop();
            }
            if (sock != null) {
                sock.close();
            }
            if (reader != null) {
                reader.close();
            }            
            if (writer != null) {
                writer.close();
            }
            if (streamReader != null) {
                streamReader.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void openConnection()
        throws IOException {
        netzwerkEinrichten();
    }

    private void netzwerkEinrichten()
        throws IOException {
        sock = new Socket(hostName, 5000);
        streamReader = new InputStreamReader(sock.getInputStream());
        reader = new BufferedReader(streamReader);
        writer = new PrintWriter(sock.getOutputStream());
        readerThread = new Thread(new EingehendReader());
        readerThread.start();
        printMessage(RMI.ADD_PLAYER);
        printMessage(RMI.GET_PLAYERS);
        printMessage(Constants.MSG_SYSTEM + Constants.MSG_SPLITTER + TileScreenFrame.myself.getName() + Constants.MSG_SPLITTER + " has connected\n");
    }
    
    private void ping() throws IOException{
        sock = new Socket(hostName, 5000);
        if (sock != null) {
            sock.close();
        }
    }
    
    public void pingHost() throws IOException{
        this.ping();
    }

    private class EingehendReader implements Runnable {

        public void run() {

            String nachricht;

            try {
                while ((nachricht = reader.readLine()) != null) {
                    String[] frame = null;
                    if(nachricht.contains(Constants.MSG_SPLITTER)){
                        frame = nachricht.split(Constants.MSG_SPLITTER);
                        System.out.println(frame[0]);
                        if(frame[0].equals(Constants.MSG_INFO)){
                            if(frame[2].equals(RMI.GET_I)){
                                MultiplayerDeployPanel.players[Integer.parseInt(frame[3])] = new Player(null, frame[4], 0, frame[5], Boolean.getBoolean(frame[6]));
                            }
                        }
                    }
                }
            } catch (IOException e) {
                printMessage(Constants.MSG_SYSTEM + Constants.MSG_SPLITTER + TileScreenFrame.myself.getName() + Constants.MSG_SPLITTER + " konnte Daten vom Server nicht empfangen!\n");
            }
        }
    }
    
    public void getPlayers(){
        for (int i = 0; i < 5; i++) {
            printMessage(RMI.GET_PLAYER_I + i);
        }
    }

    public void moveUnit(int oldX, int oldY, int x, int y) {
        // TODO Auto-generated method stub
        
    }

    public void removeUnit(int clickedTileNrX, int clickedTileNrY) {
        // TODO Auto-generated method stub
        
    }

    public void createUnit(Unit unit) {
        // TODO Auto-generated method stub
        
    }

    public void sendTerrain(Environment[][] envMap) {
        // TODO Auto-generated method stub
        
    }
}
