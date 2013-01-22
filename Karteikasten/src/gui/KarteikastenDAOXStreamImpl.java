package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class KarteikastenDAOXStreamImpl implements KarteikastenDAO {
	
	static private final Logger LOG =Logger.getLogger(KartenSpielPanel.class);
	private String basisPfad;
	
	public KarteikastenDAOXStreamImpl() {
		String basisDir="junittestdaten";
		if(System.getProperty("junit.test")==null){
			basisDir=Config.getProperty("app.dir", ".");
		}		
		this.basisPfad = basisDir+"/Lektionen/";
	}
	
	/**
	 * Schreibt die eingereichten Daten in eine XML-Datei
	 * @param kartenListe : Die Abzuspeichernde Liste
	 * @param lektionsName : Der Name der Lektion und der XML-Datei
	 */
	public void listeAufPlatteSchreiben(List<KarteiKarte> kartenListe, String lektionsName) throws IOException{
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(basisPfad+lektionsName+".xml"));
			LOG.info("Name der Gespeicherten Lektion: "+lektionsName);
			XStream xstream = new XStream(new DomDriver());
			String xml = xstream.toXML(kartenListe);
			writer.append(xml);
			writer.flush();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					LOG.error("Fehler beim Speichern");
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Lädt einen Kartensatz aus der Ausgwählten XML-Datei
	 * @param lektionsName : Die zu ladende Lektion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<KarteiKarte> kartenLaden(String lektionsName)throws Exception {
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(basisPfad+lektionsName+".xml"));
			StringBuffer sb = new StringBuffer();
			while (true) {
				String s = br.readLine();
				if (s == null)
					break;
				sb.append(s);
			}
			XStream xstream = new XStream(new DomDriver());
			return (List<KarteiKarte>) xstream.fromXML(sb.toString());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOG.error("Fehler beim Laden", e);
				}
			}
		}
	}

	/**
	 * Listet das Lektionsverzeichnis auf und gibt alle Namen zurück
	 */
	@Override
	public List<String> getLektionen() {
		File[]dateien;
		List<String> liste = new ArrayList<String>();
        File directory = new File( this.basisPfad );
        dateien = directory.listFiles();
        for ( File datei : dateien ){
        	if(datei.getName().indexOf(".xml") > 0)
        		liste.add(datei.getName().substring(0, datei.getName().indexOf(".xml")));
        	else
        		datei.delete();
        }
        if(liste.size() <= 0){
        	liste.add("Keine Datein");
        }
		return liste;
	}

	/**
	 * Speichert und Aktualisiert eine eingereichte Karte bzw die Lektion
	 */
	@Override
	public void karteSpeichern(int index, KarteiKarte karte, String lektionsName, boolean neueKarte, boolean neueLektion) throws Exception {		
		File currentFile = new File(basisPfad+lektionsName+".xml");
		if (currentFile.exists() && currentFile.isFile()){
			// Die Datei existiert bereits
			List<KarteiKarte> liste = kartenLaden(lektionsName);
			if (neueKarte == true){
				karte.setId(nextKartenID(lektionsName));
				liste.add(karte);
				LOG.info("Eine Karte wurde hinzugefügt");
			}else if(neueLektion == true){
				String kastenNeu = lektionsName.substring(0, lektionsName.indexOf("."));
				String kastenAlt = lektionsName.substring(lektionsName.indexOf(".")+1);
				currentFile = new File(basisPfad+kastenAlt+".xml");			
				liste= kartenLaden(kastenAlt);
				currentFile.delete();
				listeAufPlatteSchreiben(liste, kastenNeu);
			}else{				
				KarteiKarte karteZumUpdaten=this.findeKarteMitID(karte.getId(), liste);
				if(karteZumUpdaten==null){
					LOG.error("Karte zum Update wurde nicht gefunden!");
					throw new IllegalStateException("Karte zum updaten wurde nicht gefunden!");
				}
				karteZumUpdaten.update(karte);
				LOG.info("Eine Karte wurde aktualisiert");
			}
			if(neueLektion == false)
			listeAufPlatteSchreiben(liste, lektionsName);
		}else if(neueLektion == true && neueKarte == false){
			String kastenNeu = lektionsName.substring(0, lektionsName.indexOf("."));
			String kastenAlt = lektionsName.substring(lektionsName.indexOf(".")+1);
			currentFile = new File(basisPfad+kastenAlt+".xml");			
			List<KarteiKarte> list = kartenLaden(kastenAlt);
			currentFile.delete();
			listeAufPlatteSchreiben(list, kastenNeu);
		}else{
			// Die Datei existiert noch nicht
			List<KarteiKarte> list = new ArrayList<KarteiKarte>();
     		list.add(karte);
			listeAufPlatteSchreiben(list, lektionsName);
		}	
	}

	/**
	 * Vergibt eine neue ID an die Karte. Sucht sich die Größte ID aus der Lektion und zählt 1 drauf.
	 */
	@Override
	public int nextKartenID(String lektionsName) {
		List<KarteiKarte> kartenliste = new ArrayList<KarteiKarte>();
		try {
			kartenliste = kartenLaden(lektionsName);
		} catch (Exception e) {
			LOG.error("Fehler beim Laden", e);
		}
		int nextID = 0;
		for (KarteiKarte karteiKarte : kartenliste) {
			if (karteiKarte.getId() >= nextID) {
				nextID = karteiKarte.getId() + 1;
			}
		}
		return nextID;
	}

	@Override
	public void kartensatzLoeschen(String lektionsName) {
		File ks = new File(basisPfad+lektionsName+".xml");
		ks.delete();
	}
	/**
	 * Sucht sich die entsprechende Karte aus der Liste aus, um Sie mit der bearbeiteten Karte zu Überschreiben.
	 */
	private KarteiKarte findeKarteMitID(int id, List<KarteiKarte> list){
		for (KarteiKarte karteiKarte : list) {
			if(karteiKarte.getId()==id){
				return karteiKarte;
			}
		}
		return null;
	}
}