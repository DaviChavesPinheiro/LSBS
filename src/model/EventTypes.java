package src.model;

public enum EventTypes {
    // Stegnography events
    LSB_ENCODE,
    LSB_DECODE,
    LSB_SET_SOURCE,
    LSB_ENCODED,
    LSB_DECODED,
    // TargetFile events
    TF_MAX_SIZE,
    TF_CURRENT_SIZE,
}
