import java.util.ArrayList;


public class Event {
    
    //TODO muss noch validiert werden
    private final int STACHELFALLE = 0;
    private final int OPEN_GATE = 1;
    private final int CLOSE_GATE = 2;
    public ArrayList<Integer> events = new ArrayList<Integer>();
    public int eventId;
    
    public Event(){
        events.add(STACHELFALLE);
        events.add(OPEN_GATE);
        events.add(CLOSE_GATE);
    }
    
    public String getEventById(int i){
        if(i == STACHELFALLE){
            return "Stachefalle";
        }else if(i == OPEN_GATE){
            return "Open Gate";
        }else if(i == CLOSE_GATE){
            return "Close Gate";
        }
        return null;
    }
    
    public int getEventByString(String s){
        if(s.equals("Stachelfalle")){
            return STACHELFALLE;
        }else if(s.equals("Open Gate")){
            return OPEN_GATE;
        }else if(s.equals("Close Gate")){
            return CLOSE_GATE;
        }
        return -1;
    }
    
    public Event(int eventId){
        this.eventId = eventId;
    }
    
    public Event(String s){
        this.eventId = getEventByString(s);
    }
}
