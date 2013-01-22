package lexicanum.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import lexicanum.library.race.Races;
import lexicanum.library.units.imp.IGInfantry;
import lexicanum.library.units.imp.IGVehicles;

public class Mainpanel extends JPanel {

    private DefaultListModel listModelTroops;
    private DefaultListModel listModelVehicles;
    private JComboBox comboBoxRaces;
    private JList listTroops;
    private JList listVehicles;
    
    public Mainpanel(){
        setLayout(new BorderLayout(0, 0));
        
        JPanel topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        String[] races = Races.getRaces();
        
        comboBoxRaces = new JComboBox(races);
        comboBoxRaces.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxRaces.getSelectedItem() != null){
                    fillList(comboBoxRaces.getSelectedItem().toString());    
                }
            }
        });
        topPanel.add(comboBoxRaces);
        
        JPanel centerPanel = new JPanel();
        JPanel troopPanel = new JPanel();
        JPanel vehiclePanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(2, 1));
        troopPanel.setLayout(new BorderLayout());
        vehiclePanel.setLayout(new BorderLayout());
        centerPanel.add(troopPanel, BorderLayout.NORTH);
        centerPanel.add(vehiclePanel, BorderLayout.SOUTH);
        
        listModelTroops = new DefaultListModel();
        listTroops = new JList(listModelTroops);
        listTroops.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        listModelVehicles = new DefaultListModel();
        listVehicles = new JList(listModelVehicles);
        listTroops.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        JScrollPane scrollPaneTroops = new JScrollPane(listTroops);
        JScrollPane scrollPaneVehicle = new JScrollPane(listVehicles);
        troopPanel.add(new JLabel("Truppen"), BorderLayout.NORTH);
        vehiclePanel.add(new JLabel("Fahrzeuge"), BorderLayout.NORTH);
        troopPanel.add(scrollPaneTroops, BorderLayout.CENTER);
        vehiclePanel.add(scrollPaneVehicle, BorderLayout.CENTER);
        
        //todo
        listTroops.addListSelectionListener(null);
        
    }
    
    private void fillList(String race){
        listModelTroops.removeAllElements();
        if(race.equals(Races.IMP.getName())){
            String[] units = IGInfantry.getUnits();
            String[] vehicles = IGVehicles.getUnits();
            for (int i = 0; i < units.length; i++) {
                listModelTroops.addElement(units[i]);
            }
            for (int i = 0; i < vehicles.length; i++) {
                listModelVehicles.addElement(vehicles[i]);
            }
        }else{
            System.out.println(race);    
        }
    }
}
