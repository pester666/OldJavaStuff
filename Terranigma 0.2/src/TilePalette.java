import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class TilePalette extends JPanel{
    MapWindow window;
    ActualTile actualTile;
    int tilepaletteWidth = 4;

    /**
     * Konstruktor der Tilepalette. In diesem Bereich liegen alle Tiles des
     * geladenen Tilesets.
     * 
     * @param mapWindow
     */
    public TilePalette(MapWindow mapWindow) {
        window = mapWindow;
        setPreferredSize(new Dimension(tilepaletteWidth * window.actualMap.TILE_WIDTH, (window.actualMap.tileset.size() / tilepaletteWidth) * window.actualMap.TILE_HEIGHT));
        setDoubleBuffered(true);

        //MouseListener für das Anwählen eines Tiles und speichern der ID an der Maus.
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setTileID(e.getX(), e.getY());
                window.tool.setMouseStatus(0);
            }
        });
    }

    /**
     * Hier wird das aktuelle Tileset gezeichnet.
     */
    public void paintComponent(Graphics g) {
        int tileCount = window.actualMap.tileset.size();
        int row = 0;
        int column = 0;

        for (int i = 0; i < tileCount; i++) {
            BufferedImage tile = window.actualMap.tileset.get(i);
            g.drawImage(tile, column * window.actualMap.TILE_WIDTH, row * window.actualMap.TILE_HEIGHT, null);

            if (i % tilepaletteWidth == 3) {
                row++;
                column = 0;
            } else {
                column++;
            }
        }
    }

    /**
     * Speichert das aktuell ausgewählte Tile.
     * 
     * @param x
     * @param y
     */
    public void setTileID(int x, int y) {

        int column = x / window.actualMap.TILE_WIDTH;
        int row = y / window.actualMap.TILE_HEIGHT;
        int tileID = row * tilepaletteWidth + column;
        String tsName = window.actualMap.tilesetName.substring(window.actualMap.tilesetName.lastIndexOf("/")+1, window.actualMap.tilesetName.lastIndexOf(".png"));
        ActualTile actTile = null;
        if(tsName.equals(Tileset1.getName())){
            actTile = Tileset1.getTile(tileID);
        }else if(tsName.equals(Tileset2.getName())){
            actTile = Tileset2.getTile(tileID);
        }
        if (actTile.getId() < window.actualMap.tileset.size()) {
            actualTile = actTile;
        }
    }
}
