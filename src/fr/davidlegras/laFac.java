package fr.davidlegras;

import fr.davidlegras.customer.CustomerListener;
import fr.davidlegras.product.Offer;
import fr.davidlegras.product.Product;

import javax.swing.event.EventListenerList;
import java.util.ArrayList;
import java.util.Collection;

public class laFac implements Platform {
    private static laFacServer SERVER = new laFacServer();

    private EventListenerList listeners = new EventListenerList();

    private Collection<Product> products = new ArrayList<>();
    private Collection<Offer> offers = new ArrayList<>();
    


    @Override
    public void addCustomerListener(CustomerListener listener) {
        listeners.add(CustomerListener.class, listener);
    }

    @Override
    public void removeCustomerListener(CustomerListener listener) {
        listeners.remove(CustomerListener.class, listener);
    }

    @Override
    public Server server() {
        return null;
    }

    static class laFacServer implements Platform.Server {

        public laFacServer() {


        }

        @Override
        public void onConnect(String login, String passwordHash) {

        }

        @Override
        public void onDisconnect() {

        }
    }
}
