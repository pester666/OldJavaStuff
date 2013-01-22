package kopfbisfuss.quiz;

public class QuizKarte {

	private String frage;
	private String antwort;

	public String getFrage() {
		return this.frage;
	}

	public QuizKarte(String frage, String antwort) {
		super();
		this.frage = frage;
		this.antwort = antwort;
	}

	public String getAntwort() {
		return this.antwort;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Frage: ");
		sb.append(frage == null ? "[null]" : frage);
		sb.append(", Antwort: ");
		sb.append(antwort == null ? "[null]" : antwort);
		return sb.toString();
	}
}
