package kopfbisfuss.raumstation;

import java.util.ArrayList;

public class TestLebenserhaltungsSim {

	@SuppressWarnings("unchecked")
	public TestLebenserhaltungsSim() {
		ArrayList aList = new ArrayList();
		@SuppressWarnings("unused")
		V2K�hler v2 = new V2K�hler(aList);
		@SuppressWarnings("unused")
		V3K�hler v3 = new V3K�hler(aList);
		for (int z = 0; z < 20; z++) {
			@SuppressWarnings("unused")
			W�rmespeicherBot speicher = new W�rmespeicherBot(aList);
		}	
	}
}
