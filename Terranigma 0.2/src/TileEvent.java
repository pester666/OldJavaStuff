import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class TileEvent extends JDialog {

    MapView mapView;
    JList list;
    DefaultListModel listModel;
    int x;
    int y;

    public TileEvent(MapView mapView, int x, int y) {
        this.x = x / mapView.window.actualMap.TILE_WIDTH;
        this.y = y / mapView.window.actualMap.TILE_HEIGHT;
        JButton btnSave = new JButton("Save Events");
        JButton btnAdd = new JButton("Add Event");
        JButton btnRemove = new JButton("Remove Event");
        JButton btnCancel = new JButton("Cancel Events");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                closeDialog();
            }
        });

        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveEvents();
            }
        });

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addEventToList();
            }
        });

        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                removeEventFromList();
            }
        });
        listModel = new DefaultListModel();
        for (int i = 0; i < mapView.window.actualMap.map[this.x][this.y].getEvents().size(); i++) {
            listModel.addElement(mapView.window.actualMap.map[this.x][this.y].getEvents().get(i).getEventById(mapView.window.actualMap.map[this.x][this.y].getEvents().get(i).eventId));
        }
        list = new JList(listModel);
        JScrollPane scrP = new JScrollPane(list);

        this.mapView = mapView;
        this.setSize(350, 200);
        this.setLocationRelativeTo(mapView);
        this.setLayout(new BorderLayout());
        this.add(scrP, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void saveEvents() {
        for (int i = 0; i < mapView.window.actualMap.map[x][y].getEvents().size(); i++) {
            mapView.window.actualMap.map[x][y].removeEvent(i);
        }
        for (int i = 0; i < listModel.getSize(); i++) {
            mapView.window.actualMap.map[x][y].addAdditionalEvent(new Event(listModel.getElementAt(i).toString()));
        }
        this.dispose();
    }

    private void closeDialog() {
        this.dispose();
    }

    private void addEventToList() {
        Event event = new Event();
        String[] ts = new String[event.events.size()];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = event.getEventById(i);
        }
        String eventName = (String)JOptionPane.showInputDialog(this, "Choose the Event u want to add:", "Add Event", JOptionPane.PLAIN_MESSAGE, null, ts, 1);
        listModel.addElement(eventName);
    }

    private void removeEventFromList() {
        listModel.remove(list.getSelectedIndex());
    }
}
