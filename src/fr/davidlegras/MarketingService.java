package fr.davidlegras;

import fr.davidlegras.customer.CustomerListener;

import javax.swing.event.EventListenerList;


public class MarketingService {

    private final EventListenerList listeners = new EventListenerList();

    public synchronized void addCustomerListener(CustomerListener listener) {
        listeners.add(CustomerListener.class, listener);
    }

    public synchronized void removeCustomerListener(CustomerListener listener) {
        listeners.remove(CustomerListener.class, listener);
    }



}
