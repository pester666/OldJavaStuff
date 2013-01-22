package pdf;

import gui.MAINMETHODE;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfKarte {
	
	static private final Logger LOG =Logger.getLogger(MAINMETHODE.class); 

	private Document dokument = new Document(PageSize.A4);

	private PdfPTable tableAntwort;
	private PdfPTable tableFrage;
	
	private List<String> fragenListe = new ArrayList<String>();

	private AntwortAuflistung pAntwortAuflistung;
	
	private int antwortZähler = 0;
	private int fragenZähler = 0;
	private int länge = 0;
	private int zellenZähler = 9;

	public PdfKarte() {
		super();

		tableAntwort = new PdfPTable(2);

		tableAntwort.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tableAntwort.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		tableAntwort.getDefaultCell().setFixedHeight(168);

		tableAntwort.calculateHeights(true);
		tableAntwort.setWidthPercentage(100);

		tableFrage = new PdfPTable(2);

		tableFrage.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tableFrage.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		tableFrage.getDefaultCell().setFixedHeight(168);

		tableFrage.calculateHeights(true);
		tableFrage.setWidthPercentage(100);

		pAntwortAuflistung = new AntwortAuflistung();

	}

	public void dokumentErstellen(String dokumentName, boolean fragenDokument, int listenLänge) {

		länge = listenLänge;
		antwortZähler = 0;
		fragenZähler = 0;
		zellenZähler = 9;
			try {
				dokument = new Document(PageSize.A4);
				dokument.setMargins(0, 0, 0, 0);
				// step 2: we create a writer
				@SuppressWarnings("unused")
				PdfWriter writer = PdfWriter.getInstance(dokument,	new FileOutputStream(dokumentName));
				// step 3: we open the document
				dokument.open();
				// step 4: we add a table to the document
				pAntwortAuflistung.pdfBeschriften();
				dokument.add(Chunk.NEWLINE);

				zellenSchreiben();

			} catch (DocumentException de) {
				LOG.info("Dokumenten Fehler: ", de);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Bitte Schließen Sie zuerst das geöffnete Dokument!", "Funktion nicht möglich", 2);
				LOG.info("Das Dokument wird gerade benutzt: ", e);
			}
			// step 5: we close the document
			dokument.close();
	}
	@SuppressWarnings("all")
	private void zellenSchreiben(){
		try {
			for (fragenZähler = fragenZähler; fragenZähler < länge; fragenZähler++) {
				tableFrage.addCell(fragenListe.get(fragenZähler));
				if(fragenZähler == zellenZähler){
					fragenZähler++;
					break;
				}	
			}
			if ((länge % 2) == 1 && fragenZähler == länge)
				tableFrage.addCell("");
			dokument.add(tableFrage);
			dokument.newPage();
			for (antwortZähler = antwortZähler; antwortZähler < pAntwortAuflistung.getTableInhalt().size(); antwortZähler++) {
				tableAntwort.addCell(pAntwortAuflistung.getTableInhalt().get(antwortZähler));
				if(antwortZähler == zellenZähler){
					antwortZähler++;
					break;
				}	
			}
			dokument.add(tableAntwort);
		} catch (DocumentException e) {
			LOG.error("Dokumenten Fehler: ", e);
		}
		if(antwortZähler < pAntwortAuflistung.getTableInhalt().size()){
			dokument.newPage();
			tableFrage.deleteBodyRows();
			tableAntwort.deleteBodyRows();
			zellenZähler = zellenZähler+10;
			zellenSchreiben();
		}	
	}
	
	public void setListeAntwort(String inhalt) {
		pAntwortAuflistung.setZellenInhalt(inhalt);
	}

	public void setTableFrage(String inhalt) {
		fragenListe.add(inhalt);
	}

	public List<String> getZellenInhalt() {
		return pAntwortAuflistung.getZellenInhalt();
	}

	public PdfPTable getTableFrage() {
		return tableFrage;
	}
	
	public void resetTables(){
		tableFrage.deleteBodyRows();
		tableAntwort.deleteBodyRows();
		pAntwortAuflistung.resetZellenInhalt();
		fragenListe.clear();
	}
}