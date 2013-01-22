import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RepaintManager;


@SuppressWarnings("serial")
public class MapView extends JPanel {
    MapWindow window;
    JScrollPane scrollPane = new JScrollPane();
    RepaintManager rm;
    InfoFooter footer;
    Layer layer;

    MapViewMouseInputListener mapViewMouseInputListener;

    /**
     * Konstruktor des MapViews. Dies ist das Map-Panel auf dem gezeichnet wird.
     * 
     * @param mapWindow
     * @param footer
     * @param layer
     */
    public MapView(MapWindow mapWindow, final InfoFooter footer, Layer layer) {
        window = mapWindow;
        this.layer = layer;
        this.footer = footer;
        scrollPane.setViewportView(this);

        // verhindert flackern
        setDoubleBuffered(true);
        setMapSize();

        rm = RepaintManager.currentManager(window);

        addMouseListener(new MapViewMouseInputListener(footer, this, layer, window.tool));
        addMouseMotionListener(new MapViewMouseInputListener(footer, this, layer, window.tool));
    }

    /**
     * Zeichnet bei Änderungen die gesamte Karte mit sämtlichen Layern.
     */
    public void paintComponent(Graphics g) {
        Rectangle r = g.getClipBounds();
        int startX = r.x;
        int startY = r.y;
        int endX = startX + r.width;
        int endY = startY + r.height;

        //Berechnet die Tile-Koordinaten
        startX = startX / window.actualMap.TILE_WIDTH;
        startY = startY / window.actualMap.TILE_HEIGHT;
        endX = endX / window.actualMap.TILE_WIDTH;
        endY = endY / window.actualMap.TILE_HEIGHT;

        //Prüft ob der Mausklick innerhalb der Map liegt
        if (endX < window.actualMap.map.length) {
            endX++;
        }
        if (endY < window.actualMap.map[0].length) {
            endY++;
        }
        if (endX > window.actualMap.map.length) {
            endX = window.actualMap.map.length;
        }
        if (endY > window.actualMap.map[0].length) {
            endY = window.actualMap.map[0].length;
        }

        //Zeichnet für jede Ebene jedes Tile neu
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                if (!(window.actualMap.map[x][y] != null)) {
                    continue;
                }
                for (int l = 0; l < window.actualMap.map[x][y].getLayers().size(); ++l) {
                    if (window.layerPanel.getStatusForOneLayer(l) == true) {
                        BufferedImage tile = window.actualMap.getTileImage(x, y, l);
                        if (tile != null) {
                            g.drawImage(tile, x * window.actualMap.TILE_WIDTH, y * window.actualMap.TILE_HEIGHT, this);
                        }
                    }
                }
            }
        }
        g.drawLine(0, window.actualMap.map[0].length * window.actualMap.TILE_WIDTH, window.actualMap.map.length * window.actualMap.TILE_HEIGHT, window.actualMap.map[0].length * window.actualMap.TILE_WIDTH);
        g.drawLine(window.actualMap.map.length * window.actualMap.TILE_HEIGHT, 0, window.actualMap.map.length * window.actualMap.TILE_HEIGHT, window.actualMap.map[0].length * window.actualMap.TILE_WIDTH);
    }

    /**
     * Setzt die Größe der MapView und stellt den ViewPort auf einen
     * Defaultwert.
     */
    public void setMapSize() {
        // Hier wird nun eine Feste groe§e des JPanel gesetzt.
        int dx = window.actualMap.map.length;
        int dy = window.actualMap.map[0].length;
        setPreferredSize(new Dimension(dx * window.actualMap.TILE_WIDTH, dy * window.actualMap.TILE_HEIGHT));
        scrollPane.setViewportView(this);
    }
}
