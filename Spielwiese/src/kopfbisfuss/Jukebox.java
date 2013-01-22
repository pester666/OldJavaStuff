package kopfbisfuss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class Jukebox {

	ArrayList<Song> songList = new ArrayList<Song>();

	public static void main(String[] args) {
		new Jukebox().los();
	}

	class K�nstlerVergleich implements Comparator<Song> {
		public int compare(Song eins, Song zwei) {
			return eins.getK�nstler().compareTo(zwei.getK�nstler());
		}
	}

	public void los() {
		getSongs();
		System.out.println(songList);
		Collections.sort(songList);
		System.out.println(songList);

		TreeSet<Song> songSet = new TreeSet<Song>();
		songSet.addAll(songList);
		System.out.println(songSet);

		K�nstlerVergleich k�nstlerVergleich = new K�nstlerVergleich();
		Collections.sort(songList, k�nstlerVergleich);

		System.out.println(songList);
	}

	void getSongs() {
		try {
			File datei = new File("SongListPlus.txt");
			BufferedReader reader = new BufferedReader(new FileReader(datei));
			String zeile = null;
			while ((zeile = reader.readLine()) != null) {
				addSong(zeile);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void addSong(String zuParsendeZeile) {
		String[] tokens = zuParsendeZeile.split("/");

		Song n�chsterSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
		songList.add(n�chsterSong);
	}

}
