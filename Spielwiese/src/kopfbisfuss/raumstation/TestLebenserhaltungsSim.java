package kopfbisfuss.raumstation;

import java.util.ArrayList;

public class TestLebenserhaltungsSim {

	@SuppressWarnings("unchecked")
	public TestLebenserhaltungsSim() {
		ArrayList aList = new ArrayList();
		@SuppressWarnings("unused")
		V2Kühler v2 = new V2Kühler(aList);
		@SuppressWarnings("unused")
		V3Kühler v3 = new V3Kühler(aList);
		for (int z = 0; z < 20; z++) {
			@SuppressWarnings("unused")
			WärmespeicherBot speicher = new WärmespeicherBot(aList);
		}	
	}
}
