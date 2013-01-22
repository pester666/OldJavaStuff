package kopfbisfuss.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TippDesTagesServer {

	String[] tippListe = {
			"Essen Sie weniger Chips und Gummibärchen.",
			"Gönnen Sie sich ein kaltes Bierchen - Sie haben es sich verdient!",
			"Mit einem Wort: Unmöglich!",
			"Sie sollten wirklich mal wieder zum Friseur gehen.",
			"Holen Sie sich die engen Jeans. Nein, Sie sehen darin NICHT dick aus.",
			"Seien Sie erlich, nur heute. Sagen Sie Ihrem Chef, was Sie *wirklich* denken.",
			"Dieser Grünton steht Ihnen eigentlich nicht besonders...",
			"Saegn Sie ihrem Chef, der Bericht muss warten. Pulverschnee in Garmisch." };

	public void los() {

		try {

			ServerSocket serverSock = new ServerSocket(4242);

			while (true) {

				Socket sock = serverSock.accept();

				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				String tipp = getTipp();
				writer.println(tipp);
				writer.close();
				System.out.println(tipp);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private String getTipp() {
		int zufallszahl = (int) (Math.random() * tippListe.length);
		return tippListe[zufallszahl];
	}

	public static void main(String[] args) {

		TippDesTagesServer server = new TippDesTagesServer();
		server.los();
	}

}
