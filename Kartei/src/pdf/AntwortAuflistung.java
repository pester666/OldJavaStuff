package pdf;

import java.util.ArrayList;
import java.util.List;

public class AntwortAuflistung {

	private List<String> zellenInhalt = new ArrayList<String>();
	private List<String> tableInhalt = new ArrayList<String>();

	public AntwortAuflistung() {
	}

	public List<String> getZellenInhalt() {
		return zellenInhalt;
	}

	public void setZellenInhalt(String inhalt) {
		zellenInhalt.add(inhalt);
	}
	
	public void resetZellenInhalt() {
		zellenInhalt.clear();
		tableInhalt.clear();
	}

	public List<String> getTableInhalt() {
		return tableInhalt;
	}

	public void pdfBeschriften() {
		if ((zellenInhalt.size() & 1) == 0) {
			int k = 2;

			for (int i = 0, length = zellenInhalt.size(); i < length; i++) {
				tableInhalt.add(zellenInhalt.get((k - 1)));

				if ((k & 1) == 0)
					k = (k - 1);
				else
					k = (k + 3);
			}
		} else if ((zellenInhalt.size() & 1) == 1) {
			int k = 1;

			for (int i = 0, length = zellenInhalt.size(); i < length; i++) {
				if (k >= zellenInhalt.size()) {
					k = (zellenInhalt.size() - 1);
					tableInhalt.add("");
				}
				tableInhalt.add(zellenInhalt.get((k)));

				if ((i & 1) == 0)
					k = (k - 1);
				else
					k = (k + 3);
			}
		}
	}
}
