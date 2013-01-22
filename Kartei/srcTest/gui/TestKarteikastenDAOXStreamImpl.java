package gui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestKarteikastenDAOXStreamImpl implements TestKarteiOberklasse{

	static public final String TESTDATEN_DIR = "junittestdaten";
		
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("junit.test", "true");
		File file = new File(TESTDATEN_DIR);
		if(file.exists()){
			if(file.isFile()){
				file.delete();
				file.mkdir();
			}
		}else{
			file.mkdir();
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass(){
		File f = new File(TESTDATEN_DIR);
		File[] files= f.listFiles();
		for (int i = 0, length = files.length; i < length; i++) {
			files[i].delete();
		}
		f.delete();
		
	}

	@Test(expected=Exception.class)
	public void testSpeichern() throws Exception{
		ArrayList<KarteiKarte> liste = new ArrayList<KarteiKarte>();
		
		karte.karteSpeichern(0, liste.get(0), null, false, false);
		fail("hier sollte eine Exception fliegen");

		karte.karteSpeichern(0, liste.get(0), "", false, false);
		fail("hier sollte eine Exception fliegen");
		
		karte.karteSpeichern(0, liste.get(0), TESTDATEN_DIR + File.separator + "sepp.xml", false, false);
		File sepp = new File(TESTDATEN_DIR + File.separator + "sepp.xml");
		assertTrue(sepp.exists() && sepp.isFile());

		List<KarteiKarte> geladen = karte.kartenLaden(TESTDATEN_DIR + File.separator + "sepp.xml");
		assertNotNull("Es sollte eine leere Liste kommen", geladen);
		assertTrue(geladen.size() == 0);	
		
		liste.add(KARTE_1);
		liste.add(KARTE_2);
		
		karte.karteSpeichern(0, liste.get(0), TESTDATEN_DIR + File.separator + "sepp.xml", false, false);
		assertTrue(karte.kartenLaden(TESTDATEN_DIR + File.separator + "sepp.xml").size() == 2);
	}

	@Test(expected = Exception.class)
	public void testKartenLaden() throws Exception{
		ArrayList<KarteiKarte> zweiKarten = new ArrayList<KarteiKarte>();
		ArrayList<KarteiKarte> eineKarte = new ArrayList<KarteiKarte>();

		zweiKarten.add(KARTE_1);
		zweiKarten.add(KARTE_2);
		karte.karteSpeichern(0, zweiKarten.get(0), TESTDATEN_DIR + File.separator+"hans.xml", false, false);
		eineKarte.addAll(karte.kartenLaden(TESTDATEN_DIR + File.separator+"hans.xml"));
		assertTrue(eineKarte.size() == 2);
		assertTrue(zweiKarten.equals(eineKarte));
	}
	
	@Test
	public void testKarteSpeichern(){
		
		try {
			karte.karteSpeichern(6, KARTE_1, TESTDATEN_DIR, true, false);
			fail("hier soll eine exception fliegen");
		} catch (Exception e) {
		}
		
		try {
			karte.karteSpeichern(0, KARTE_2, TESTDATEN_DIR, false, false);
			fail("hier soll eine exception fliegen");
		} catch (Exception e) {
		}
	}
	
	@Test(expected = AssertionError.class)
	public void testKartensatzLoeschen(){
		karte.kartensatzLoeschen(TESTDATEN_DIR);
		fail("hier soll eine exception fliegen");
	}
	
	@Test(expected = AssertionError.class)
	public void testNextKartenID(){
		karte.nextKartenID(TESTDATEN_DIR);
		fail("hier soll eine exception fliegen");
	}
	
	@Test
	public void testKartenSpeichern(){
		ArrayList<KarteiKarte> zweiKarten = new ArrayList<KarteiKarte>();
		zweiKarten.add(KARTE_1);
		zweiKarten.add(KARTE_2);
		try {
			karte.listeAufPlatteSchreiben(zweiKarten, TESTDATEN_DIR);
			fail("hier soll eine exception fliegen");
		} catch (IOException e) {
		}
	}
}
