
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MapViewMouseInputListener extends MouseAdapter{
    InfoFooter footer;
    MapView mapView;
    Layer layer;
    Tool tool;

    /**
     * Konstruktor des MouseListeners.
     * 
     * @param iFooter
     * @param mView
     * @param layer
     */
    public MapViewMouseInputListener(InfoFooter iFooter, MapView mView, Layer layer, Tool tool) {
        this.footer = iFooter;
        this.mapView = mView;
        this.layer = layer;
        this.tool = tool;
    }

    /**
     * Ruft die drawTile-Methode bei Klick auf.
     */
    public void mouseClicked(MouseEvent e) {
        if (mapView.window.tool.getMouseStatus() == 0) {
            tool.drawTile(e.getX(), e.getY(), layer.getLayer());
        } else if (mapView.window.tool.getMouseStatus() == 1) {
            tool.eraseTile(e.getX(), e.getY(), layer.getLayer());
        } else if(mapView.window.tool.getMouseStatus() == 2){
            if(!tool.window.actualMap.map[e.getX()/32][e.getY()/32].isEmpty()){
                new TileEvent(mapView, e.getX(), e.getY());
            }
        }
    }

    /**
     * Ruft die drawTile-Methode pro betretenem Feld auf. Dragged Mouse!
     */
    public void mouseDragged(MouseEvent e) {
        if (mapView.window.tool.getMouseStatus() == 0) {
            tool.drawTile(e.getX(), e.getY(), layer.getLayer());
            footer.updateCoordsView("x: " + (int)Math.ceil(e.getX() / 32) + " y: " + (int)Math.ceil(e.getY() / 32));
        } else if (mapView.window.tool.getMouseStatus() == 1) {
            tool.eraseTile(e.getX(), e.getY(), layer.getLayer());
            footer.updateCoordsView("x: " + (int)Math.ceil(e.getX() / 32) + " y: " + (int)Math.ceil(e.getY() / 32));
        } else if(mapView.window.tool.getMouseStatus() == 2){
            //NOTHING , can't drag .... not yet
        }
    }

    /**
     * Aktualisiert die Tile-Koordinaten im Footer anhand der Maus-Koordinaten
     * wenn diese bewegt wird.
     */
    public void mouseMoved(MouseEvent e) {
        //berechnen der einzelnen Koordinaten des angeklickten Tiles
        //durch 32 wegen der größe und dann aufrunden um auf das tile zu kommen
        //auf (int) casten da "/32" eine floatzahl zurückgibt
        footer.updateCoordsView("x: " + (int)Math.ceil(e.getX() / 32) + " y: " + (int)Math.ceil(e.getY() / 32));
    }
}
