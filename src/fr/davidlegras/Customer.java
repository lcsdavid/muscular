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

    public CustomerState getCustomerState() {
        return this.customerState;
    }

    public Cart cart() {
        return cart;
    }

    public void addToCart(Product product, int count) {
        if (product == null)
            throw new NullPointerException();
        cart.add(product, count);
    }

    public void addToCart(Product product) {
        addToCart(product, 1);
    }

    /* Prix */

    public float price(final Platform platform) {
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
        customerState.connect(platform, this, login, passwordHash);
    }

    public void disconnect(final Platform platform) throws NotConnectedException {
        customerState.disconnect(platform, this);
    }

    public boolean isConnected() {
        return customerState.getClass().isAssignableFrom(ConnectedCustomer.class);
    }

    /* Affichage */
    public String cartToString() {
        String s = "";
        for (Map.Entry<Product, Integer> entry : cart)
            s += entry.getKey().toString() + " : " + entry.getValue().toString() + '\n';
        s += "\n\t";
        s += "Prix total = " + rawPrice() + "€.\n\t";
        return s;
    }

    @Override
    public String toString() {
        return customerState.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (object.getClass().equals(this.getClass()))
            return true;
        return false;
    }
}
