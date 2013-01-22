import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {

    Tile[][] map;
    int TILE_WIDTH = 32;
    int TILE_HEIGHT = 32;

    String mapName;
    String tilesetName;

    ArrayList<BufferedImage> tileset = new ArrayList<BufferedImage>();

    /**
     * Map konstruktor zum einlesen eines Tilesets und speichern
     * 
     * @param map
     * @param dataName der name des TileSets
     * @param name der Name der Map
     */
    public Map(Tile[][] map, String dataName, String name) {
        if (map == null && dataName == null && name == null) {
            this.map = new Tile[1][1];
            return;
        }
        this.mapName = name;
        this.map = map;
        changeTileset(dataName);
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                for (int l = 1; l < map[x][y].getLayers().size(); l++) {
                    map[x][y].setTileOnLayer(l, new ActualTile(-1, false));
                }
            }
        }
    }

    /**
     * Liefert ein Bild anhand der angegebenen Koordinaten.
     * 
     * @param x
     * @param y
     * @return tile
     */
    public BufferedImage getTileImage(int x, int y, int layer) {
        int tileID = map[x][y].getTileIDOnLayer(layer);
        if (tileID < 0 || tileset.size() <= 0) {
            return null;
        }
        return tileset.get(tileID);
    }

    /**
     * Setzt ein neue Tile-ID an einer Stelle der Map.
     * 
     * @param x
     * @param y
     * @param tile
     */
    public void setTile(int x, int y, int layer, ActualTile tile) {
        map[x][y].setTileOnLayer(layer, tile);
    }

    /**
     * Befüllt das Tileset mit einzelnen Bildern. Sollte bereits ein Tileset
     * vorhanden sein wird dieses gelöscht und neu befüllt.
     * 
     * @param tileSetName
     */
    public void changeTileset(String tileSetName) {
        try {
            this.tilesetName = tileSetName;
            if (this.tileset.size() > 0) {
                this.tileset.removeAll(tileset);
            }
            BufferedImage pictureTileset = ImageIO.read(new File(tileSetName));
            int width = pictureTileset.getWidth() / TILE_WIDTH;
            int height = pictureTileset.getHeight() / TILE_HEIGHT;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    BufferedImage tile = pictureTileset.getSubimage(x * TILE_WIDTH, y * TILE_HEIGHT, 32, 32);
                    this.tileset.add(tile);
                }
            }
        } catch (IOException e) {
            System.err.println("Tileset " + tileSetName + " nicht gefunden.");
            e.printStackTrace();
        }
    }
}
