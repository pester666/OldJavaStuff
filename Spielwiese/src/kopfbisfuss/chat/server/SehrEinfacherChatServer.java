package kopfbisfuss.chat.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class SehrEinfacherChatServer {

    @SuppressWarnings("unchecked")
    private ArrayList clientAusgabeStröme;
    private JTextArea log;

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
                    esAllenWeitersagen(nachricht);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new SehrEinfacherChatServer().los();

    }

    @SuppressWarnings("unchecked")
    private void los() {

        JFrame frame = new JFrame("Server");
        log = new JTextArea(20, 30);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        JScrollPane scr = new JScrollPane(log);
        scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scr);
        frame.setSize(300, 300);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);

        clientAusgabeStröme = new ArrayList();

        try {
            ServerSocket serverSock = new ServerSocket(5000);
            log.append("Server gestaret\n");
            while (true) {
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientAusgabeStröme.add(writer);
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                log.append("Habe eine Verbindung von " + clientSocket.getLocalAddress() + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void esAllenWeitersagen(String nachricht) {

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
