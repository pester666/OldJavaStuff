package kopfbisfuss.raumstation;

import java.util.ArrayList;

public class V2K�hler {

	@SuppressWarnings("unchecked")
	V2K�hler(ArrayList list) {
		for (int x = 0; x < 5; x++)
			list.add(new SimEinheit("V2K�hler"));
	}
}
