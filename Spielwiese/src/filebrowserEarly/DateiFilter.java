package filebrowserEarly;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class DateiFilter implements FilenameFilter{

	public String dateiFormat = "";
	List<String> liste = new ArrayList<String>();
	@Override
	public boolean accept(File f, String dateiName) {
		if(dateiFormat.length() != 0)
			return new File(f, dateiName).isFile() && dateiName.toLowerCase().endsWith(dateiFormat);
		else
			return new File(f, dateiName).isFile();
	}
	public void setDateiFormat(String format){
		dateiFormat = format;
	}
	public void archivDatei(File file, String suchText) throws ZipException, IOException{
		if(dateiFormat.length() != 0){
			ZipFile zip = new ZipFile(file);
			for(ZipEntry eintrag : Collections.list(zip.entries())){
				if(eintrag.getName().contains(suchText))
					liste.add("---> " + eintrag.getName());	
			}
		}else{
			if(file.getName().contains(suchText))
				liste.add(file.getAbsolutePath());
		}
	}
	public void resetListe(){
		liste.clear();
	}
}
