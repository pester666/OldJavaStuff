import java.io.Serializable;
import java.util.ArrayList;


public class Tile implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Integer> layers = new ArrayList<Integer>();
    private boolean walkable = true;
    private ArrayList<Event> events = new ArrayList<Event>();

    public Tile(ArrayList<Integer> layers, boolean walkable, ArrayList<Event> events) {
        super();
        this.layers = layers;
        this.walkable = walkable;
        this.events = events;
    }

    public ArrayList<Integer> getLayers() {
        return layers;
    }

    public int getTileIDOnLayer(int i) {
        return layers.get(i);
    }

    public void setLayerList(ArrayList<Integer> layers) {
        this.layers = layers;
    }

    public void fillAdditionlLayerWithTileID(int i) {
        layers.add(i);
    }

    public void setTileOnLayer(int i, ActualTile tileID) {
        layers.set(i, tileID.getId());
        walkable = tileID.isWalkable();
    }

    public void removeLayer(int i) {
        layers.remove(i);
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEventList(ArrayList<Event> events) {
        this.events = events;
    }

    public void addAdditionalEvent(Event e) {
        events.add(e);
    }

    public void setEventById(int i, int e) {
        this.events.set(i, new Event(e));
    }
    
    public void setEventByString(int i, String e){
        this.events.set(i, new Event(new Event().getEventByString(e)));
    }

    public void removeEvent(int i) {
        events.remove(i);
    }
    
    public boolean isEmpty(){
        for (int i = 0; i < layers.size(); i++) {
            if(layers.get(i) != null && layers.get(i) >= 0){
                return false;
            }
        }
        return true;
    }
}
