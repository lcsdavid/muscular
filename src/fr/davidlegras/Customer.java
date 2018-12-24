package fr.davidlegras;

import javax.swing.event.EventListenerList;
import java.util.Map;

/**
 * Un Customer est un objet qui représente le client.
 *
 * @author Lucas David
 * @author Théo Legras
 */
public class Customer {
    private EventListenerList listeners = new EventListenerList();

    private CustomerState customerState;
    private Cart cart;

    public Customer() {
        super();
        customerState = Visitor.getVisitor();
        cart = new Cart();
    }

    public <T extends CustomerListener> void addCustomerListener(Class<T> type, T listener) {
        listeners.add(type, listener);
    }

    /* Acesseurs & Mutateurs */
    public CustomerState customerState() {
        return customerState;
    }

    public void customerState(CustomerState state) {
        customerState = state;
    }

    public Cart cart() {
        return cart;
    }

    public void addToCart(Product product, int count) {
        Cart old = cart;
        if (product == null)
            throw new NullPointerException();
        cart.add(product, count);
        for (CustomerListener listener : listeners.getListeners(CustomerListener.class))
            listener.productAdded(new CartEvent(this, old, cart, product, count));
    }

    public void addToCart(Product product) {
        addToCart(product, 1);
    }

    /* Prix */

    public double price(final Platform platform) {
        return customerState.price(platform, this);
    }

    public float rawPrice() {
        float cartPrice = 0;
        for (Map.Entry<Product, Integer> entry : cart)
            cartPrice += entry.getKey().price() * entry.getValue();
        return cartPrice;
    }

    /* Connexion & Déconnexion */

    public void connect(final Platform platform, String login, String passwordHash) throws AlreadyConnectedException, WrongCredentials {
        CustomerState old = customerState;
        customerState.connect(platform, this, login, passwordHash);
        for (CustomerListener listener : listeners.getListeners(CustomerListener.class))
            listener.stateChanged(new CustomerStateEvent(this, old, customerState));
    }

    public void disconnect(final Platform platform) throws NotConnectedException {
        CustomerState old = customerState;
        customerState.disconnect(platform, this);
        for (CustomerListener listener : listeners.getListeners(CustomerListener.class))
            listener.stateChanged(new CustomerStateEvent(this, old, customerState));
    }

    public boolean isConnected() {
        return ConnectedCustomer.class.isAssignableFrom(customerState.getClass());
    }

    /* Affichage */
    @Override
    public String toString() {
        return customerState.toString();
    }
}
