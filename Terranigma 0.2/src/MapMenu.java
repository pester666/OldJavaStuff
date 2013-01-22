import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MapMenu extends JMenuBar {

    MapWindow window;

    JMenu file = new JMenu("Datei");
    JMenuItem saveMap = new JMenuItem("Karte Speichern");

    /**
     * Konstruktor des MapMenüs. Hier wird die Menüleiste erstellt und
     * Menüpunkte hinzugefügt.
     * 
     * @param mapWindow
     */
    public MapMenu(MapWindow mapWindow) {
        window = mapWindow;

        saveMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMap();
            }
        });

        file.add(saveMap);
        add(file);
    }

    /**
     * Lädt eine bereits gespeicherte Karte und setzte diese als aktuelle Karte
     * im MapWindow.
     */
    public void loadMap() {
        try {
            JFileChooser loadDialog = new JFileChooser();
            loadDialog.showOpenDialog(window);
            FileInputStream file = new FileInputStream(loadDialog.getSelectedFile());
            BufferedInputStream buf = new BufferedInputStream(file);
            ObjectInputStream read = new ObjectInputStream(buf);

            Tile[][] map = (Tile[][])read.readObject();
            String mapName = (String)read.readObject();
            String tilesetName = (String)read.readObject();

            window.actualMap = new Map(map, tilesetName, mapName);
            read.close();
            window.mapView.setMapSize();
            window.palette.repaint();
            window.mapView.repaint();
            window.mapView.scrollPane.repaint();
            window.repaint();

        } catch (Exception e) {

        }
    }

    /**
     * Speichert die angezeigte Karte als Datei.
     */
    public void saveMap() {
        try {
            JFileChooser saveDialog = new JFileChooser();
            saveDialog.showSaveDialog(window);
            FileOutputStream file = new FileOutputStream(saveDialog.getSelectedFile());
            BufferedOutputStream buf = new BufferedOutputStream(file);
            ObjectOutputStream write = new ObjectOutputStream(buf);
            write.writeObject(window.actualMap.map);
            write.writeObject(window.actualMap.mapName);
            write.writeObject(window.actualMap.tilesetName);
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
