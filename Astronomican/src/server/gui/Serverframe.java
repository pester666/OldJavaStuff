package server.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Serverframe {

    private List<PrintWriter> clientStream;
    
    public Serverframe(){
        JFrame frame = new JFrame("Serverframe");
        frame.setSize(200,100);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JLabel status = new JLabel("Server aktiv . . .");
        frame.add(status);
        frame.setVisible(true);
        
        clientStream = new ArrayList<PrintWriter>();

        try {
            ServerSocket serverSock = new ServerSocket(5000);
            while (true) {
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientStream.add(writer);
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
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
                    System.out.println(nachricht);
                    print(nachricht);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void print(String nachricht) {

        Iterator<PrintWriter> it = clientStream.iterator();
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
