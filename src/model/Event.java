package src.model;

public class Event {
    String type;
    EventListener listener;
    
    public Event(String type, EventListener listener) {
        this.type = type;
        this.listener = listener;
    }
}
