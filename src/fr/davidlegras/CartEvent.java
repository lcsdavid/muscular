package fr.davidlegras;

import java.util.EventObject;

public class CartEvent extends EventObject {

    /**
     * Constructs a {@code CartEvent} prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CartEvent(Object source) {
        super(source);
    }


}
