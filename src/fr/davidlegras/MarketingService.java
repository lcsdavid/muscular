package fr.davidlegras;

import fr.davidlegras.customer.CustomerListener;
import fr.davidlegras.product.Product;

import javax.swing.event.EventListenerList;
import java.util.HashMap;
import java.util.Map;


public class MarketingService {

    private final EventListenerList listeners = new EventListenerList();
    private final Map<Product, Integer> fidelityPointPerProduct;

    public MarketingService() {
        this("chemin par défault");
    }

    public MarketingService(String databasePath) {
        super();
        // TODO charger les exemples de membre du Staff, client inscrit sur le site, matrice des gains de points fidelités...
        fidelityPointPerProduct = new HashMap<>();
    }

    public synchronized void addCustomerListener(CustomerListener listener) {
        listeners.add(CustomerListener.class, listener);
    }

    public synchronized void removeCustomerListener(CustomerListener listener) {
        listeners.remove(CustomerListener.class, listener);
    }

}
