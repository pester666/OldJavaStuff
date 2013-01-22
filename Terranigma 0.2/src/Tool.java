import java.awt.Rectangle;


public class Tool {
    // "0" for Brush-Tool
    // "1" for Eraser-Tool
    // "2" for Event-Tool
    // "3" for Selector
    int mouseStatus;

    MapWindow window;

    /**
	 * 
	 */
    public Tool(MapWindow window) {
        setMouseStatus(-1);
        this.window = window;
    }

    /**
     * @param toolId
     */
    public void setMouseStatus(int toolId) {
        mouseStatus = toolId;
    }

    /**
     * @return
     */
    public int getMouseStatus() {
        return mouseStatus;
    }

    /**
     * Zeichnet ein Tile abhängig von Koordinaten und Ebene und added eine
     * "DirtyRegion" zum repainten.
     * 
     * @param x
     * @param y
     * @param layer
     */
    public void drawTile(int x, int y, int layer) {
        if (window.layerPanel.getStatusForOneLayer(layer) == true) {
            x = x / window.actualMap.TILE_WIDTH;
            y = y / window.actualMap.TILE_HEIGHT;
            //Prüft ob man außerhalb der Karte zeichnen möchte
            if (x < window.actualMap.map.length && y < window.actualMap.map[0].length && x >= 0 && y >= 0) {
                if (x < window.actualMap.map.length && y < window.actualMap.map[x].length && layer <= window.actualMap.map[x][y].getLayers().size()) {
                    if(window.palette.actualTile != null){
                        window.actualMap.map[x][y].setTileOnLayer(layer, window.palette.actualTile);
                        Rectangle r = window.mapView.scrollPane.getViewport().getViewRect();
                        int dx = this.window.mapView.scrollPane.getLocation().x + window.getInsets().left - r.x;
                        int dy = this.window.mapView.scrollPane.getLocation().y + window.getInsets().top - r.y;
                        window.mapView.rm.addDirtyRegion(window, dx + x * window.actualMap.TILE_WIDTH, dy + y * window.actualMap.TILE_HEIGHT, window.actualMap.TILE_WIDTH, window.actualMap.TILE_HEIGHT);
                    }
                }
            }
        }
    }

    /**
     * "Radiert" ein Tile und added eine "DirtyRegion" zum repainten.
     * 
     * @param x
     * @param y
     * @param layer
     */
    public void eraseTile(int x, int y, int layer) {
        if (window.layerPanel.getStatusForOneLayer(layer) == true) {
            x = x / window.actualMap.TILE_WIDTH;
            y = y / window.actualMap.TILE_HEIGHT;
            //Prüft ob man außerhalb der Karte zeichnen möchte
            if (x < window.actualMap.map.length && y < window.actualMap.map[0].length && x >= 0 && y >= 0) {
                if (x < window.actualMap.map.length && y < window.actualMap.map[x].length && layer <= window.actualMap.map[x][y].getLayers().size()) {
                    window.actualMap.map[x][y].setTileOnLayer(layer, new ActualTile(-1, false));
                    Rectangle r = window.mapView.scrollPane.getViewport().getViewRect();
                    int dx = this.window.mapView.scrollPane.getLocation().x + window.getInsets().left - r.x;
                    int dy = this.window.mapView.scrollPane.getLocation().y + window.getInsets().top - r.y;
                    window.mapView.rm.addDirtyRegion(window, dx + x * window.actualMap.TILE_WIDTH, dy + y * window.actualMap.TILE_HEIGHT, window.actualMap.TILE_WIDTH, window.actualMap.TILE_HEIGHT);
                }
            }
        }
    }
}
