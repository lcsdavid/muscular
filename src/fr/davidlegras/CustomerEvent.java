package fr.davidlegras;

import java.util.EventObject;

public class CustomerEvent extends EventObject {

    /**
     * Constructs a prototypical CustomerEvent.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CustomerEvent(Object source) {
        super(source);
    }

}
