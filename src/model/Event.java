package src.model;

public class Event {
    EventTypes type;
    EventListener listener;
    
    public Event(EventTypes type, EventListener listener) {
        this.type = type;
        this.listener = listener;
    }
}
