package src.model;

public interface EventListener {
    void onEvent(EventTypes eventType, Object model);
}
