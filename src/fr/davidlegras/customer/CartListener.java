package fr.davidlegras.customer;

import java.util.EventListener;

public interface CartListener extends EventListener {

    void productAdded(CartEvent e);

    void productRemoved(CartEvent e);
}
