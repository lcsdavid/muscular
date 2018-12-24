package fr.davidlegras;

public class CustomerStateEvent extends CustomerEvent {
    /**
     * Constructs a prototypical CustomerEvent.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CustomerStateEvent(Object source) {
        super(source);
    }
}
