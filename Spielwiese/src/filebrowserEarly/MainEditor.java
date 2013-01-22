package filebrowserEarly;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;

import javax.swing.JFileChooser;


import com.karneim.editor.Editor;
import com.karneim.editor.Operation;
import com.karneim.editor.ValidationState;
import com.karneim.editor.property.BooleanProperty;
import com.karneim.editor.property.StringProperty;
import com.karneim.gui.KScrollPane;

public class MainEditor extends Editor {
	protected final StringProperty header = new StringProperty("header");
	protected final StringProperty suchBegriff = new StringProperty("suchBegriff");
	protected final StringProperty lbZip = new StringProperty("lbZip");
	protected final StringProperty lbJar = new StringProperty("lbJar");
	protected final FileReaderEditor fileReader = new FileReaderEditor();
	KScrollPane scrollPane = new KScrollPane(fileReader.list);
	JFileChooser fc = new JFileChooser();
	
	protected final Operation btnSuchen = new Operation("btnSuchen"){        
		public void execute() throws Exception{
			MainEditor.this.suchen();
		}
		
		public ValidationState validate(){
			if(suchBegriff.getString().startsWith("Suchbegriff") == true)
				return new ValidationState("Geben Sie einen Suchbegriff ein oder Löschen sie die Defaulteingabe");
			return null;
			
		}
	};
		
    protected final BooleanProperty isJar = new BooleanProperty("isJar") {
        public void onChange() {
        	if ( this.toBoolean()) 
        		isZip.setBoolean(false);
        }
      };
      
      protected final BooleanProperty isZip = new BooleanProperty("isZip") {
          public void onChange() {
        	  if ( this.toBoolean())
        		  isJar.setBoolean(false);
          }
        };

	public MainEditor() {
		editorInit();
	}

	private void editorInit() {
		add(header);
		header.setString("Dateien");
		add(btnSuchen);
		add(suchBegriff);
		suchBegriff.setString("Suchbegriff");
		add(isJar);
		isJar.setDescription("Es werden nur JAR-Datein herausgefiltert und nach dem Suchbegriff durchforstet");
		add(isZip);
		isZip.setDescription("Es werden nur Zip-Datein herausgefiltert und nach dem Suchbegriff durchforstet");
		add(lbZip);
		add(lbJar);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileReader.anzahlString.setString("0 Dateien durchsucht");
		this.revalidate();
	}
	
	protected void suchen() throws ZipException, IOException{
		this.btnSuchen.check();
		String format = "";
		
		if(isJar.toBoolean() == true)
			format = ".jar";
		else if(isZip.toBoolean() == true)
				format = ".zip";
		
		int returnVal = fc.showOpenDialog(scrollPane);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileReader.listModel.clear();
			fileReader.resetGesamtAnzahl();
			fileReader.anzahlDateien.setInt(0);
            File file = fc.getSelectedFile();

    		fileReader.reader(file.getAbsolutePath(), suchBegriff.getString(), format);		
		}
		header.setString(fileReader.anzahlDateien + " Dateien durchsucht/gefunden");
	}
}
