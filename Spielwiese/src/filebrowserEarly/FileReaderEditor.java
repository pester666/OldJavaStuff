package filebrowserEarly;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;

import javax.swing.DefaultListModel;
import javax.swing.JList;


import com.karneim.editor.Editor;
import com.karneim.editor.property.IntegerProperty;
import com.karneim.editor.property.StringProperty;

public class FileReaderEditor extends Editor{
	public DefaultListModel listModel = new DefaultListModel();
	public JList list = new JList(listModel);
	protected final StringProperty anzahlString = new StringProperty("anzahlString");
	protected final IntegerProperty anzahlDateien = new IntegerProperty("anzahlDateien"){
		public void onChange(){
			anzahlString.setString(anzahlDateien.toString() + " Dateien durchsucht");
		}
	};
	
	public FileReaderEditor(){
		editorInit();
	}
	
	private void editorInit(){
		add(anzahlDateien);
		add(anzahlString);
		this.revalidate();
	}
	
	public void reader(String dateiPfad, String suchBegriff, String format) throws ZipException, IOException {
		File[] dateien;
		File directory = new File(dateiPfad);
		DateiFilter filter = new DateiFilter();
		filter.setDateiFormat(format);
		dateien = directory.listFiles(filter);
		if(dateien != null){
			list.repaint();
			list.updateUI();
			for (File file : dateien) {
				anzahlDateien.setInt(anzahlDateien.toInt()+1);
				if(suchBegriff.length() != 0){
					filter.archivDatei(file, suchBegriff);
					if(filter.liste.size() > 0){
						listModel.addElement(file.getAbsolutePath());
					}
					for(int i = 0, n = filter.liste.size(); i < n; i++){
						listModel.addElement(filter.liste.get(i));
					}
				}else{
					listModel.addElement(file.getAbsolutePath());
					list.repaint();
				}	
			}
			File[] verzeichniss;
			verzeichniss = directory.listFiles(new VerzeichnissFilter());
			for (File dir : verzeichniss) {
				dateiPfad = dir.getAbsolutePath();
				reader(dateiPfad, suchBegriff, format);
			}
		}
		if(listModel.size() == 0)
			listModel.addElement("Keine Dateien");
	}
	public void resetGesamtAnzahl(){
		listModel.clear();
	}
}
