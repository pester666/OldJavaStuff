package allsrc.ch15src.src;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class LoadStringBuffer {

public static void main (String[] args) {

    StringBuffer sb = null;

    try {
        FileInputStream fis = new FileInputStream("sb.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        sb = (StringBuffer) ois.readObject();

        ois.close();

    } catch (IOException ioException) {
        System.out.println(ioException);
    } catch (ClassNotFoundException classNotFoundException ) {
        System.out.println(classNotFoundException );
    }

    System.out.println("Welcome back aboard, Sir! : " + sb);
}

}
