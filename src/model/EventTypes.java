package src.model;

public enum EventTypes {
    // Stegnography events
    LSB_ENCODE,
    LSB_DECODE,
    LSB_SET_SOURCE,
    LSB_ENCODED_SET,
    LSB_ENCODED_REMOVED,
    LSB_DECODED_CHANGED,
    // TargetFile events
    TF_MAX_SIZE,
    TF_ADD_FILE,
    TF_REMOVE_FILE,
}
