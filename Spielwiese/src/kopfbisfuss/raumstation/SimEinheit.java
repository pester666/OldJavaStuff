package kopfbisfuss.raumstation;

public class SimEinheit {

	String botTyp;

	SimEinheit(String typ) {
		botTyp = typ;
	}

	int energieVerbrauch() {
		if ("W�rmespeicher".equals(botTyp))
			return 2;
		else
			return 4;
	}
}
