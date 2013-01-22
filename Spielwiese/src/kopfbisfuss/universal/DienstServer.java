package kopfbisfuss.universal;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DienstServer extends Remote {

	Object[] getDienstListe() throws RemoteException;

	Dienst getDienst(Object dienstSchüssel) throws RemoteException;

}
