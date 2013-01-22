package kopfbisfuss.chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class EinfacherChatClient {

    private JTextField ausgehend;
    private JTextArea eingehend;
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket sock;
    private JButton sendenButton;
    private Thread readerThread;
    private InputStreamReader streamReader;
    private String name;
    private String hostName;

    public static void main(String[] args) {

        EinfacherChatClient client = new EinfacherChatClient();
        client.los();
    }

    private void los() {

        String host = (String)JOptionPane.showInputDialog(null, "Bitte gib die exakte Bezeichnung des Computernamens ein\nwelcher den Server gestartet hat (z.B. HV000192):", "Server auswählen", JOptionPane.QUESTION_MESSAGE, null, null, "");
        if ((host != null)) {
            if (host.length() > 0) {
                hostName = host;
            } else {
                hostName = "127.0.0.1";
            }
        }

        String user = (String)JOptionPane.showInputDialog(null, "Bitte gib deinen Namen ein:", "Name wählen", JOptionPane.QUESTION_MESSAGE, null, null, "");
        if ((user != null)) {
            if (user.length() > 0) {
                name = user + ": ";
            } else {
                name = "Anonymus: ";
            }
        }
        JFrame frame = new JFrame("Chat-Client");
        JPanel hauptPanel = new JPanel();
        eingehend = new JTextArea(20, 30);
        eingehend.setLineWrap(true);
        eingehend.setWrapStyleWord(true);
        eingehend.setEditable(false);
        JScrollPane fScroller = new JScrollPane(eingehend);
        fScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ausgehend = new JTextField(20);
        sendenButton = new JButton("Senden");
        sendenButton.setMnemonic(KeyEvent.VK_S);
        SendenEnterListener sl = new SendenEnterListener();
        sendenButton.addKeyListener(sl);
        frame.addKeyListener(sl);
        eingehend.addKeyListener(sl);
        hauptPanel.addKeyListener(sl);
        ausgehend.addKeyListener(sl);
        sendenButton.addActionListener(new SendenButtonListener());
        hauptPanel.add(fScroller);
        hauptPanel.add(ausgehend);
        hauptPanel.add(sendenButton);

        netzwerkEinrichten();

        frame.add(BorderLayout.CENTER, hauptPanel);
        frame.setSize(400, 400);
        frame.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("deprecation")
            @Override
            public void windowClosing(WindowEvent e) {
                try {
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
                        writer.println(name.substring(0, name.indexOf(":")) + " has disconnected ...");
                        writer.flush();
                        writer.close();
                    }
                    if (streamReader != null) {
                        streamReader.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
        sendenButton.setEnabled(false);
        frame.setVisible(true);
    }

    private void netzwerkEinrichten() {

        try {
            sock = new Socket(hostName, 5000);
            streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            readerThread = new Thread(new EingehendReader());
            readerThread.start();
            eingehend.append("Netzwerkverbindung steht\n\n");
            writer.println(name.substring(0, name.indexOf(":")) + " has connected\n");
            writer.flush();
        } catch (IOException ex) {
            sendenButton.setEnabled(false);
            ausgehend.setEnabled(false);
            eingehend.append("Es konnte keine Verbindung zum Server hergestellt werden!\n\n");
        }
    }

    private class SendenButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            writer.println(name + ausgehend.getText());
            writer.flush();
            ausgehend.setText("");
            ausgehend.requestFocus();
        }
    }

    private class SendenEnterListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendenButton.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (ausgehend.getText().length() > 0) {
                sendenButton.setEnabled(true);
            } else {
                sendenButton.setEnabled(false);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }

    private class EingehendReader implements Runnable {

        public void run() {

            String nachricht;

            try {
                while ((nachricht = reader.readLine()) != null) {
                    eingehend.append(nachricht + "\n");
                }
            } catch (IOException e) {
                eingehend.append("Konnte Daten vom Server nicht empfangen!\n");
                writer.println(name.substring(0, name.indexOf(":")) + " konnte Daten vom Server nicht empfangen!\n");
                writer.flush();
            }

        }
    }
}
