
public class Layer {
    MapWindow window;
    int actualLayer = 0;

    /**
     * Konstruktor des Layer-Objekts.
     */
    public Layer(MapWindow window) {
        this.window = window;
    }

    /**
     * Liefert das aktuell ausgewählte Layer.
     */
    public int getLayer() {
        return this.actualLayer;
    }

    /**
     * Setzt den aktuell ausgewählten Layer.
     * 
     * @param newLayer
     */
    public void setLayer(int newLayer) {
        this.actualLayer = newLayer;
    }

    public int getLayerCount() {
        if (window.actualMap.map.length > 0 && window.actualMap.map[0].length > 0 && window.actualMap.map[0][0] != null) {
            return window.actualMap.map[0][0].getLayers().size();
        }
        return 0;
    }
}
