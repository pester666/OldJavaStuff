package client.multiplayer.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import lib.Constants;
import lib.Player;
import lib.RMI;

public class ChatServerGame extends Thread{

    private ArrayList<PrintWriter> clientAusgabeStröme;
    public String log;
    public Player[] p = new Player[5];
//    public String[] players = new String[5];
//    public String[] playerRaces = new String[5];
    public boolean[] allPlayersReady = new boolean[5];
    private int actPoints = 500;
    private int actAge = Constants.FANTASY;
    public boolean shouldRun = true;
    private int playerCount = 0;

    private class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            String nachricht;
            try {
                while ((nachricht = reader.readLine()) != null) {
                    if(nachricht.contains(Constants.MSG_SPLITTER)){
                        String[] frame = nachricht.split(Constants.MSG_SPLITTER);
                        if(frame[0].equals(Constants.MSG_INFO)){
                            nachricht = infoMsgs(nachricht, frame);
                        }
                    }
                    esAllenWeitersagen(nachricht);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void addPlayer(String name){
        for (int i = 0; i < p.length; i++) {
            if(p[i].getName().equals("Open")){
                p[i].setName(name);
                p[i].setRace(Constants.RACE_ORKS);
                playerCount++;
                break;
            }
        }
        for (int i = 0; i < p.length; i++) {
            if(p[i].getName().equals("Open")){
                p[i].setRace(" ");
            } 
        }
    }
    
    private void removePlayer(String name){
        // i = 1 wegen host kann nicht verlassen bzw fliegen alle raus
        for (int i = 1; i < p.length; i++) {
            if(p[i].getName().equals(name)){
                p[i].setName("Open");
                p[i].setRace(" ");
                playerCount--;
                break;
            }
        }
    }
    
    private void makePlayerReady(String name){
        //i = 1 wegen host kann nicht ready machen sonder nur starten
        for (int i = 1; i < p.length; i++) {
            if(p[i].getName().equals(name)){
                allPlayersReady[i] = true;
                break;
            }
        }
        for (int i = 0; i < p.length; i++) {
            if(p[i].getName().equals("Open")){
                allPlayersReady[i] = true;
            }
        }
    }
    
    private void makePlayerUnready(String name){
        //i = 1 wegen host kann nicht ready machen sonder nur starten
        for (int i = 1; i < p.length; i++) {
            if(p[i].getName().equals(name)){
                allPlayersReady[i] = false;
                break;
            }
        }
    }
        
    private String areAllPlayersReady(){
        for (int i = 1; i < allPlayersReady.length; i++) {
            if(!allPlayersReady[i] || playerCount <= 1){
                return RMI.UNREADY;
            }
        }
        return RMI.ALL_READY;
    }
        
    public void run() {

        clientAusgabeStröme = new ArrayList<PrintWriter>();

        try {
            ServerSocket serverSock = new ServerSocket(5000);
            log = log + "Server gestaret\n";
            while (true && shouldRun) {
                serverSock.setSoTimeout(1000);
                Socket clientSocket;
                try {
                    clientSocket = serverSock.accept();
                } catch (Exception e) {
                    continue;
                }
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientAusgabeStröme.add(writer);
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                log = log + "Habe eine Verbindung von " + clientSocket.getLocalAddress() + "\n";
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String infoMsgs(String nachricht, String[] frame) {
        //Spieler hinzu
        if (frame[2].equals(RMI.ADD)) {
            addPlayer(frame[1]);
            //Spieler weg
        } else if (frame[2].equals(RMI.REM)) {
            removePlayer(frame[1]);
            //Welche Spieler sind da
        } else if (frame[2].equals(RMI.WHO)) {
            //Change age
        } else if (frame[2].equals(RMI.AGE)) {
            actAge = Integer.parseInt(frame[3]);
            //Change Points    
        } else if (frame[2].equals(RMI.POINTS)) {
            actPoints = Integer.parseInt(frame[3]);
            //Make player ready
        } else if (frame[2].equals(RMI.READY)) {
            makePlayerReady(frame[1]);
            //Make player unready
        } else if (frame[2].equals(RMI.UNREADY)) {
            makePlayerUnready(frame[1]);
        }
        nachricht = getInfos(RMI.WHO);
        if(frame[2].equals(RMI.START)){
            nachricht = Constants.MSG_SYSTEM + Constants.MSG_SPLITTER + RMI.START;
        }
        if (frame[2].equals(RMI.GET_I)){
            nachricht = getPlayer(frame[1], frame[3]);
        }
        return nachricht;
    }
    
    private String getPlayer(String caller, String i){
        String nachricht = Constants.MSG_INFO + Constants.MSG_SPLITTER + caller + Constants.MSG_SPLITTER + RMI.GET_I;
        nachricht = nachricht + i + Constants.MSG_SPLITTER;
        nachricht = nachricht + p[Integer.parseInt(i)].getName() + Constants.MSG_SPLITTER;
        nachricht = nachricht + p[Integer.parseInt(i)].getRace() + Constants.MSG_SPLITTER;
        nachricht = nachricht + p[Integer.parseInt(i)].isReady();
        
        return nachricht;
    }
  
    private String getInfos(String rmi){
        String nachricht = Constants.MSG_INFO + Constants.MSG_SPLITTER + rmi;
        for (int i = 0; i < p.length; i++) {
            nachricht = nachricht + Constants.MSG_SPLITTER + p[i].getName();
        }
        nachricht = nachricht + Constants.MSG_SPLITTER + actAge;
        nachricht = nachricht + Constants.MSG_SPLITTER + actPoints;
        nachricht = nachricht + Constants.MSG_SPLITTER + areAllPlayersReady();
        for (int i = 0; i < p.length; i++) {
            nachricht = nachricht + Constants.MSG_SPLITTER + p[i].getRace();
        }
        return nachricht;
    }

    private void esAllenWeitersagen(String nachricht) {

        @SuppressWarnings("rawtypes")
        Iterator it = clientAusgabeStröme.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter)it.next();
                writer.println(nachricht);
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
