package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class KarteiKastenDAODBImpl implements KarteikastenDAO {
	static private final Logger LOG = Logger.getLogger(Mainframe.class);

	private static final SimpleDateFormat DF_TT_MM_JJJJ_TIME = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	/**
	 * Lädt die Namen der Lektionen in der Datenbank und fügt sie der Liste hinzu
	 */
	@Override
	public List<String> getLektionen() throws SQLException {
		PreparedStatement ps = null;
		ResultSet result = null;
		List<String> liste = new ArrayList<String>();
		Connection 	dbCon = this.getUDBConnection();
		try {
			StringBuffer sb = new StringBuffer("select l.lk_name from lektionen l");
			String sqlString = sb.toString();
			ps = dbCon.prepareStatement(sqlString);
			result = ps.executeQuery();
			while (result.next()) {
				String name = result.getString("lk_name");
				liste.add(name);
			}
			if (liste.size() <= 0)
				liste.add("Keine Lektionen");
			return liste;
		} catch (SQLException ex) {
			LOG.error("Fehler beim Auslesen der Lektionen: ", ex);
			throw ex;
		} finally {
			closePreparedStatement(ps);
			closeResultSet(result);
			closeConnection(dbCon);
		}
	}
	
	/**
	 * Speichert eine veränderte Karte ab. Entweder sie wurde neu Hinzugefügt, Bearbeitet oder in ihrer Phasennummer sowie Abfragedatum verändert.
	 * @param index : Wird nicht benötigt
	 * @param karte : Die in der GUI aktuell angezeigte Karte
	 * @param lektionsName : Der Name der Lektion
	 * @param neueKarte : Gibt an, welche Aktion ausgeführt wird. Bei True wird eine Karte hinzugefügt, bei False wird eine Karte geUpdatet
	 * @param neueLektion : Gibt an, ob eine Karte ohne zugehörige Lektion erstellt wurde. Wenn dem so ist, wird eine neue Erzeugt
	 */
	@Override
	public void karteSpeichern(int index, KarteiKarte karte, String lektionsName, boolean neueKarte, boolean neueLektion) throws Exception {
		
		PreparedStatement ps = null;
		ResultSet result = null;
		Connection dbCon = this.getUDBConnection();
		StringBuffer sb;
		String sqlString;
		// Die Datei existiert bereits
		if (neueKarte == true) {
			try {
				if(neueLektion == true){
					sb = new StringBuffer("insert into lektionen values((select max(l.lk_id+1) from lektionen l),?)");
					sqlString = sb.toString();
					ps = dbCon.prepareStatement(sqlString);
					ps.setString(1, lektionsName);
					result = ps.executeQuery();
					LOG.info("Eine Lektion wurde hinzugefügt");
				}
				sb = new StringBuffer("insert into karten values ((select l.lk_id from lektionen l where lk_name = ?), 0, 0, ?, ?, ?, null)");
				sqlString = sb.toString();
				ps = dbCon.prepareStatement(sqlString);
				ps.setString(1, lektionsName);
				ps.setString(2, karte.getFrage());
				ps.setString(3, karte.getAntwort());
				ps.setString(4, DF_TT_MM_JJJJ_TIME.format(new Date()));
				result = ps.executeQuery();
				LOG.info("Eine Karte wurde hinzugefügt");
			} catch (SQLException ex) {
				LOG.error("Fehler beim Einfügen der Karte: ", ex);
				throw ex;
			} finally {
				closePreparedStatement(ps);
				closeResultSet(result);
				closeConnection(dbCon);
			}
		} else {
			try {
				if(neueLektion == true){
					String lektionsNameNeu = lektionsName.substring(0, lektionsName.indexOf("."));
					String lektionsNameAlt = lektionsName.substring(lektionsName.indexOf(".")+1);
					sb = new StringBuffer("update lektionen set lk_name = ? where lk_id = (select lk_id from lektionen where lk_name = ?)");
					sqlString = sb.toString();
					ps = dbCon.prepareStatement(sqlString);
					ps.setString(1, lektionsNameNeu);
					ps.setString(2, lektionsNameAlt);
					result = ps.executeQuery();
					LOG.info("Die Lektion " + lektionsNameAlt + " wurde umbenannt in " + lektionsNameNeu);
				}else{
					sb = new StringBuffer("update karten set frage = ?, antwort = ?, phasen_nr = ?, abf_date = ? where kt_id = ?");
					sqlString = sb.toString();
					ps = dbCon.prepareStatement(sqlString);
					ps.setString(1, karte.getFrage());
					ps.setString(2, karte.getAntwort());
					ps.setInt(3, karte.getPhasenNummer());
					ps.setString(4, DF_TT_MM_JJJJ_TIME.format(new Date()));
					ps.setInt(5, karte.getId());
					result = ps.executeQuery();
					LOG.info("Eine Karte wurde aktualisiert");
				}	
			} catch (SQLException ex) {
				LOG.error("Fehler beim Updaten der Karte: ", ex);
				throw ex;
			} finally {
				closePreparedStatement(ps);
				closeResultSet(result);
				closeConnection(dbCon);
			}
		}
	}

	/**
	 * Lädt den ausgewählten Datensatz
	 */
	@Override
	public List<KarteiKarte> kartenLaden(String lektionsName) throws Exception {
		PreparedStatement ps = null;
		ResultSet result = null;
		ArrayList<KarteiKarte> liste = new ArrayList<KarteiKarte>();
		Connection dbCon = this.getUDBConnection();
		try {
			StringBuffer sb = new StringBuffer("select k.kt_id, k.phasen_nr, K.FRAGE, k.antwort, K.ABF_DATE, K.EF_DATE from karten k, lektionen l where k.lk_id = l.lk_id and l.lk_name = ?");
			String sqlString = sb.toString();
			ps = dbCon.prepareStatement(sqlString);
			ps.setString(1, lektionsName);
			result = ps.executeQuery();
			while (result.next()) {
				int id = result.getInt("kt_id");
				int phase = result.getInt("phasen_nr");
				String frage = result.getString("frage");
				String antwort = result.getString("antwort");
				Timestamp ef = result.getTimestamp("ef_date");
				Timestamp abf = result.getTimestamp("abf_date");

				KarteiKarte k = new KarteiKarte(id, frage, antwort, false, ef);
				k.setAbfrageDatum(abf);
				k.setPhasenNummer(phase);
				liste.add(k);
			}
			return liste;
		} catch (SQLException ex) {
			LOG.error("Fehler beim Laden der Karten: ", ex);
			throw ex;
		} finally {
			closePreparedStatement(ps);
			closeResultSet(result);
			closeConnection(dbCon);
		}
	}

	/**
	 * Löscht den ausgewählten Kartensatz sowie alle dazugehörigen Karten aus der Datenbank
	 */
	@Override
	public void kartensatzLoeschen(String lektionsName)throws SQLException {
		PreparedStatement ps = null;
		ResultSet result = null;
		Connection dbCon = null;
		try {
			dbCon = this.getUDBConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			StringBuffer sb = new StringBuffer("delete from karten where lk_id = (select l.lk_id from lektionen l where l.lk_name = ?)");
			String sqlString = sb.toString();
			ps = dbCon.prepareStatement(sqlString);
			ps.setString(1, lektionsName);
			result = ps.executeQuery();
			
			sb = new StringBuffer("delete from lektionen where lk_name = ?");
			sqlString = sb.toString();
			ps = dbCon.prepareStatement(sqlString);
			ps.setString(1, lektionsName);
			result = ps.executeQuery();
		} catch (SQLException ex) {
			LOG.error("Fehler beim Löschen einer Lektion: ", ex);
			throw ex;
		} finally {
			closePreparedStatement(ps);
			closeResultSet(result);
			closeConnection(dbCon);
		}
	}

	/**
	 * Hat bei der Datenbankimplementierung keine Funktion
	 */
	@Override
	public int nextKartenID(String lektionsName){
		return 0;
	}

	private Connection getUDBConnection() throws SQLException {
		Connection con = DriverManager.getConnection(Config.getProperty(
				"db.udb.url", "afs"), Config.getProperty("db.udb.user", "fg"),
				Config.getProperty("db.udb.password", "asf"));
		return con;
	}

	private void closeConnection(Connection dbCon) {
		try {
			if (dbCon != null)
				dbCon.close();
		} catch (SQLException ex) {
			LOG.error("Error closing conection", ex);
		}
	}

	private void closePreparedStatement(PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException ex) {
			LOG.error("Error closing PreparedStatement", ex);
		}
	}

	private void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException ex) {
			LOG.error("Error closing ResultSet", ex);
		}
	}
}
