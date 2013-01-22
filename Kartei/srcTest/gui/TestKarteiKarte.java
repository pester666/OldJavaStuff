package gui;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestKarteiKarte implements TestKarteiOberklasse{

	public static class GleicheWerte{
		int integer;

		private GleicheWerte(int x) {
			super();
			this.integer = x;
		}

		@Override
		public int hashCode() {
			return integer;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (o == this)
				return true;
			if (!o.getClass().equals(getClass()))
				return false;
			GleicheWerte gw=(GleicheWerte)o;
			return gw.integer==integer;
		}
	}
	
	KarteiKarte karte1 = new KarteiKarte(666, "bla", "dida", true, new Date());
	KarteiKarte karte2;
	KarteiKarte karte3 = new KarteiKarte((int)(Math.random()*27), "mhpf", " zonk", false, new Date());
	KarteiFuerTest karte4 = new KarteiFuerTest (1, "bla", "tüt", false, new Date());
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		karte3.setId((int)(Math.random()*20));
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEqualsKarteEins(){
		assertTrue(KARTE_1.equals(KARTE_1));
		assertTrue(KARTE_2.equals(KARTE_2));
		assertTrue(karte1.equals(karte1));
		assertTrue(karte3.equals(karte3));
	}
	
	@Test
	public void testEqualsKarteZwei(){
		assertFalse(KARTE_1.equals(KARTE_2));
	}

	@Test
	public void testEqualsK(){
		assertFalse(KARTE_1.equals(karte1));
	}
	
	@Test
	public void testEqualsKK(){
		assertFalse(KARTE_1.equals(karte2));
	}
	
	@Test
	public void testEqualsClass(){
		assertFalse(KARTE_1.equals(karte4));
	}
	
	@Test
	public void testEqualsFalsch(){
		assertFalse(KARTE_1.equals("falsch"));
	}
	
	@Test
	public void testEquals80(){
		assertFalse(KARTE_1.equals(80));
	}

	@Test
	public void testEqualsNull(){
		assertFalse(KARTE_1.equals(null));
	}


	@Test
	public void testMist1(){
		List<String> l= new ArrayList<String>();
		for(int i = 1;i<5;i++){
			l.add("sepp");
		}
		assertTrue(l.size()==4);
		
		Set<String> s = new HashSet<String>();
		for(int i = 1;i<5;i++){
			s.add("sepp");
		}
		assertTrue(s.size()==1);
		Set<GleicheWerte> qset = new HashSet<GleicheWerte>();
		for(int i = 1;i<5;i++){
			qset.add(new GleicheWerte(i));
		}
		assertTrue(qset.size()==4);
	}
}
