package editor;

import javax.swing.JButton;

public class MyButton extends JButton {

    private int id;

    //zum Button erstellen mit id aus der DB
    public MyButton(String s, int id) {
        super(s);
        this.id = id;
    }

    //Abfragen der ID um alles andere aus der DB zu holen 
    public int getID() {
        return id;
    }
}