package src.model;

import java.util.ArrayList;

public class EventManeger {
    private ArrayList<Event> listeners = new ArrayList<>();

    public void subscribe(String eventType, EventListener listener) {
        listeners.add(new Event(eventType, listener));
    }

    public void notify(String eventType, Object data) {
        for (Event event : listeners) {
            if(event.type == eventType) {
                event.listener.eventUpdate(data);
            }
        }
    }
}
