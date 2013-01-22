package kopfbisfuss.universal;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

@SuppressWarnings("serial")
public class DienstServerImpl extends UnicastRemoteObject implements
		DienstServer {

	HashMap<String, Dienst> dienstListe;

	public DienstServerImpl() throws RemoteException {
		diensteEinrichten();
	}

	private void diensteEinrichten() {
		dienstListe = new HashMap<String, Dienst>();
		dienstListe.put("Würfeldienst", new WürfelDienst());
		dienstListe.put("Wochentagedienst", new Wochentagdienst());
		dienstListe.put("Musikvideodienst", new MiniMusikDienst());
	}

	public Object[] getDienstListe() {
		System.out.println("in Remote");
		return dienstListe.keySet().toArray();
	}

	public Dienst getDienst(Object dienstSchlüssel) throws RemoteException {
		Dienst derDienst = dienstListe.get(dienstSchlüssel);
		return derDienst;
	}

	public static void main(String[] args) {
		try {
			Naming.rebind("DienstServer", new DienstServerImpl());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Remote-Dienst läuft");
	}

}
