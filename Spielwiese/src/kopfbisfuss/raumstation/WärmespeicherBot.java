package kopfbisfuss.raumstation;

import java.util.ArrayList;

public class WärmespeicherBot {

	@SuppressWarnings("unchecked")
	WärmespeicherBot(ArrayList rlist) {
		rlist.add(new SimEinheit("Wärmespeicher"));
	}
}
