package kopfbisfuss.raumstation;

public class SimEinheit {

	String botTyp;

	SimEinheit(String typ) {
		botTyp = typ;
	}

	int energieVerbrauch() {
		if ("Wärmespeicher".equals(botTyp))
			return 2;
		else
			return 4;
	}
}
