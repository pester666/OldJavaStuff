package allsrc.ch15src.src;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SaveArray {

public static void main (String[] args) {

    Object x = new Object[] {new Thread(), null};
    try {
        FileOutputStream fos = new FileOutputStream("array.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject( x );

        oos.close();

    } catch (IOException ioException) {
        System.out.println(ioException);
    }

    System.out.println(x instanceof Serializable );
}

}
