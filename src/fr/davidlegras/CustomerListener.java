package fr.davidlegras;

import java.util.EventListener;

public interface CustomerListener extends EventListener {

    void productAdded(CartEvent e);

    void productRemoved(CartEvent e);

    void stateChanged(CustomerStateEvent e);
}
