package client.multiplayer.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import client.menu.TileScreenFrame;
import client.multiplayer.LobbyPanelHost;
import client.multiplayer.LobbyPanelJoin;
import lib.Constants;
import lib.RMI;

public class ChatClientLobby {

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket sock;
    private Thread readerThread;
    private InputStreamReader streamReader;
    private String hostName;
    public String[] messages = new String[9];
    
    boolean isHost = false;

    public ChatClientLobby(boolean isHost, String ipAdress) {
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
                        if(frame[0].equals(Constants.MSG_INFO)){
                            if(frame[1].equals(RMI.WHO)){
                                for (int i = 2; i < frame.length; i++) {
                                    if(i < 7){
                                        if(isHost){
                                            LobbyPanelHost.p[i-2].setName(frame[i]);
                                        }else{
                                            LobbyPanelJoin.p[i-2].setName(frame[i]);
                                        } 
                                    }else if(i > 9){
                                        LobbyPanelHost.p[i-10].setRace(frame[i]);
                                        LobbyPanelJoin.p[i-10].setRace(frame[i]);
                                    }
                                }
                                LobbyPanelJoin.updateSettings(Integer.parseInt(frame[7]), Integer.parseInt(frame[8]));
                                if(frame[9].equals(RMI.ALL_READY)){
                                    LobbyPanelHost.allPlayersReady = true; 
                                }else{
                                    LobbyPanelHost.allPlayersReady = false;
                                }
                            }
                        } else if (frame[0].equals(Constants.MSG_SYSTEM)) {
                            if (frame[1].equals(RMI.START)) {
                                LobbyPanelJoin.startTheGame(hostName);
                                closeConnection();
                            } else {
                                for (int i = 0; i < messages.length; i++) {
                                    if (i + 1 < messages.length) {
                                        messages[i] = messages[i + 1];

                                    } else {
                                        messages[i] = frame[0] + frame[1];
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                printMessage(Constants.MSG_SYSTEM + Constants.MSG_SPLITTER + TileScreenFrame.myself.getName() + Constants.MSG_SPLITTER + " konnte Daten vom Server nicht empfangen!\n");
            }
        }
    }
    
    public void changeAge(int age){
        printMessage(RMI.CHANGE_AGE + Constants.MSG_SPLITTER + age);
    }
    
    public void changePoints(int points){
        printMessage(RMI.CHANGE_POINTS + Constants.MSG_SPLITTER + points);
    }
    
    public void setReadyStatus(boolean isNowReady){
        if(isNowReady){
            printMessage(RMI.MAKE_PLAYER_READY);
        }else{
            printMessage(RMI.MAKE_PLAYER_UNREADY);
        }
    }
    
    public void changeRace(String actRace){
        printMessage(RMI.CHANGE_RACE + Constants.MSG_SPLITTER + actRace);
    }
    
    public void startTheGame(){
        printMessage(RMI.START_GAME);
    }
}
