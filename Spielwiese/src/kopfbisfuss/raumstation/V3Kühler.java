package kopfbisfuss.raumstation;

import java.util.ArrayList;

public class V3Kühler extends V2Kühler {

	@SuppressWarnings("unchecked")
	V3Kühler(ArrayList lglist) {
		super(lglist);
		for (int g = 0; g < 10; g++)
			lglist.add(new SimEinheit("V3Kühler"));
	}
}
