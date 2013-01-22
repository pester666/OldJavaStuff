package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;


public class KartensatzAuswahl{

	static private final Logger LOG =Logger.getLogger(Mainframe.class); 
	
	private JButton btnLaden;
	private JButton btnLöschen;
	private JButton btnUmbenennen;
	private String aktuellerKasten;
	private JSplitPane sPane;
		
	private MainframeSteuerung parent;
	
    private JList list;
    private DefaultListModel listModel;
    
	public KartensatzAuswahl(MainframeSteuerung parent)throws Exception{
		this.parent = parent;
		JPanel plButtonBox = new JPanel();
		btnLaden = new JButton  ("Lektion  öffnen");
		btnLöschen = new JButton("Lektion löschen");
		btnUmbenennen = new JButton("Lektion ändern");
		
		btnLaden.addActionListener(new btnLadenListener());
		btnLöschen.addActionListener(new btnLöschenListener());
		btnUmbenennen.addActionListener(new btnUmbenennenListener());
			
		plButtonBox.setLayout(new BoxLayout(plButtonBox, BoxLayout.Y_AXIS));
		
        listModel = new DefaultListModel();
        
        plButtonBox.setBackground(Color.gray);
                
        KarteikastenDAO dao = new DAOFactory().getKarteikastenDAO();
        
        List<String> liste = new ArrayList<String>();
    	liste.addAll(dao.getLektionen());
    	
        for (int i = 0, length = liste.size(); i < length; i++){
            listModel.add(i, liste.get(i));
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setPreferredSize(new Dimension(60, 400));

		plButtonBox.add(Box.createRigidArea(new Dimension(0,5)));
        plButtonBox.add(btnLaden);
		plButtonBox.add(Box.createRigidArea(new Dimension(0,5)));
		plButtonBox.add(btnUmbenennen);
		plButtonBox.add(Box.createRigidArea(new Dimension(0,5)));
		plButtonBox.add(btnLöschen);
		plButtonBox.add(Box.createRigidArea(new Dimension(0,5)));
		
		sPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, list, plButtonBox);
	}
		
    public JSplitPane getSplitPane() {
		return sPane;
	}

	class btnLadenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
            int index = list.getSelectedIndex(); 
            aktuellerKasten = (String)listModel.get(index);
            parent.ladenAusführen();
        }
    }
    
	public String getAktuellerKasten() {
		return aktuellerKasten;
	}
	
	public List<String> getListModel(){
		ArrayList<String> liste = new ArrayList<String>();
		for (int i = 0, length = listModel.size(); i < length; i++) {
			liste.add((String) listModel.get(i));
		}
		return liste;
	}
	
	/**
	 * Aktualisiert die Liste wenn eine Lektion Gelöscht oder Hhinzugefügt wurde
	 */
	public void listeAktualisieren()throws Exception{
		
        DAOFactory factory = new DAOFactory();
        KarteikastenDAO dao = factory.getKarteikastenDAO();
        
        List<String> liste = dao.getLektionen();
        listModel.clear();
        for (int i = 0, length = liste.size(); i < length; i++) {
			listModel.addElement(liste.get(i));
		}
        if(listModel.size() <= 0){
        	listModel.addElement("Keine Kartensätze");
        	btnLaden.setEnabled(false);
        }
        list.repaint();
	}
	
	public int dlgLoeschenWarnung(String lektionsName){
		return JOptionPane.showConfirmDialog(sPane,
				"Wollen sie die Lektion "+ lektionsName +" mit allen Karten wirklich löschen?",
				"Kartensatz löschen.",
				JOptionPane.YES_NO_OPTION);
	}
	
	public class btnUmbenennenListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
	
			KarteikastenDAO dao = new DAOFactory().getKarteikastenDAO();
			
			String lektionsNameNeu = "";
			String lektionsNameAlt = (String)listModel.get(list.getSelectedIndex());
			
			lektionsNameNeu = (String)JOptionPane.showInputDialog(sPane, "Benennen Sie ihre neu Lektion!\n", "Speichern", JOptionPane.PLAIN_MESSAGE, null, null, "");
			if(parent.getListModelElements().contains(lektionsNameNeu) == true){
				JOptionPane.showMessageDialog(sPane, "Lektion bereits vorhanden! Bitte wählen Sie einen anderen Namen.", "Fehler",1);
				return;
			}	
			try {
				if(lektionsNameAlt != null)
				dao.karteSpeichern(1, null, lektionsNameNeu + "." + lektionsNameAlt, false, true);
			} catch (Exception e) {
				LOG.error("Fehler beim Speichern: ", e);
				e.printStackTrace();
			}
			try {
				listeAktualisieren();
			} catch (Exception e) {
				LOG.error("Fehler beim Aktualisieren: ", e);
				e.printStackTrace();
			}
		}
	}
	
	public class btnLöschenListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
        
			int index = list.getSelectedIndex();
            aktuellerKasten = (String)listModel.get(index);
            
			int chosenOption = dlgLoeschenWarnung(aktuellerKasten);
			if(chosenOption == JOptionPane.YES_OPTION){

	            DAOFactory factory= new DAOFactory();
	            KarteikastenDAO dao= factory.getKarteikastenDAO();
				LOG.info("Die Lektion "+aktuellerKasten+" wurde gelöscht!");
	            try {
					dao.kartensatzLoeschen(aktuellerKasten);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(sPane, "Ein Fehler beim Löschen einer Lektion ist aufgetreten!", "Warnung", 0);
					LOG.error("Ein Fehler beim Löschen einer Lektion ist aufgetreten: ", e);
				}
	            try {
					listeAktualisieren();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(sPane, "Ein Fehler beim aktualisieren der Lektionsliste aufgetreten!", "Warnung", 0);
					LOG.error("Ein Fehler beim aktualisieren der Lektionsliste aufgetreten: ", e);
				}
	            parent.setCurrentKartensatzName(null);
	            parent.resetKartensatz();
	            parent.tabbedPaneAktivieren(1, false);
			}
		}
	}
}
