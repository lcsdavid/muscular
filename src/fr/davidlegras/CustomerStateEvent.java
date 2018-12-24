package fr.davidlegras;

public class CustomerStateEvent extends CustomerEvent {
    private CustomerState oldState, newState;

    /**
     * Constructs a prototypical CustomerEvent.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CustomerStateEvent(Object source, CustomerState oldState, CustomerState newState) {
        super(source);
        this.oldState = oldState;
        this.newState = newState;
    }

    public CustomerState getOldState() {
        return oldState;
    }

    public CustomerState getNewState() {
        return newState;
    }
}
