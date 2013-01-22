package allsrc.ch15src.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameState implements Serializable {

private Player player = new Player(3);

public static void main (String[] args) {

    try {
        FileOutputStream fos = new FileOutputStream("game.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject( new GameState() );
        oos.close();

        FileInputStream fis = new FileInputStream("game.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        System.out.println( ois.readObject() );

        ois.close();

    } catch (Exception exception) {
        System.out.println(exception);
    }
}

public String toString() {

    return "GameState[ " + player.toString() + " ]";
}
}

class Player implements Serializable {

private short livesLeft;

/*package*/ Player(int lives) {
    livesLeft = (short) lives;
}

private void writeObject(ObjectOutputStream s) throws IOException {
    System.out.println("Encrypting lives left...");
    s.writeByte(- livesLeft);
}

private void readObject(ObjectInputStream s) throws IOException {
    System.out.println("Decrypting lives left...");
    livesLeft = (short) - s.readByte();
}

public String toString() {

    return "Player[" + livesLeft + "]";
}
}
