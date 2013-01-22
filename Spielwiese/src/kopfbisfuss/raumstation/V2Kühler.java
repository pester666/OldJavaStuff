package kopfbisfuss.raumstation;

import java.util.ArrayList;

public class V2Kühler {

	@SuppressWarnings("unchecked")
	V2Kühler(ArrayList list) {
		for (int x = 0; x < 5; x++)
			list.add(new SimEinheit("V2Kühler"));
	}
}
