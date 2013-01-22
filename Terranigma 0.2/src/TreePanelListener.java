
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreePanelListener implements TreeSelectionListener {

    MapWindow window;

    public TreePanelListener(MapWindow window) {
        this.window = window;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)window.treePanel.tree.getLastSelectedPathComponent();

        if (node == null)
            return;

        if (node.isLeaf()) {
            try {
                //Speichert die letzte geöffnete Karte auf der Festplatte, wenn keine Karte bisher geöffnet wurde wird dieser Schritt übersprungen
                if(window.actualMap.mapName != null){
                    File f = new File("mapdata/" + node.getParent() + "/" + window.actualMap.mapName);
                    if (!f.exists()) {
                        JOptionPane.showMessageDialog(window.mapView, "You cannot save the actual map!", "Saving failed", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    FileOutputStream fOut = new FileOutputStream("mapdata/" + node.getParent() + "/" + window.actualMap.mapName);
                    BufferedOutputStream bufOut = new BufferedOutputStream(fOut);
                    ObjectOutputStream write = new ObjectOutputStream(bufOut);
                    write.writeObject(window.actualMap.map);
                    write.writeObject(window.actualMap.mapName);
                    write.writeObject(window.actualMap.tilesetName);
                    write.close();  
                }
                //Liest die angeklickte Karte von der Festplatte in den Editor ein
                File ff = new File("mapdata/" + node.getParent() + "/" + node.toString());
                if(!ff.exists()){
                    JOptionPane.showMessageDialog(window.mapView, "The map you want to load does not exist!", "Loading failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                FileInputStream fIn = new FileInputStream(ff);
                BufferedInputStream bufIn = new BufferedInputStream(fIn);
                ObjectInputStream read = new ObjectInputStream(bufIn);
                window.layerPanel.updateLayerCount();

                //Setzt die internen Daten mit den Daten aus der Datei
                Tile[][] map = (Tile[][])read.readObject();
                String mapName = (String)read.readObject();
                String tilesetName = (String)read.readObject();

                //Der Rest ...
                window.actualMap = new Map(map, tilesetName, mapName);
                read.close();
                window.setTitle("Terranigma 0.2 - " + mapName);
                window.layerPanel.updateLayerCount();
                window.mapView.setMapSize();
                window.palette.repaint();
                window.mapView.repaint();
                window.layerPanel.repaint();
                window.mapView.scrollPane.repaint();
                window.repaint();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
    }
}