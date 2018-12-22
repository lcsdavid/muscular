package fr.davidlegras.customer;

import fr.davidlegras.Platform;
import fr.davidlegras.product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Un Customer est un objet qui représente le client.
 *
 * @author Lucas David
 * @author Théo Legras
 */
public class Customer {
    private CustomerState customerState;
    private Map<Product, Integer> cart;

    public Customer() {
        super();
        customerState = Visitor.getVisitor();
        cart = new HashMap<>();
    }

    public Customer(final Platform platform, String login, String passwordHash) throws WrongCredentials {
        this();
        try { connect(platform, login, passwordHash); } catch (AlreadyConnectedException ignored) { /* Never catch in this case. */ }
    }

    /* Acesseurs & Mutateurs */

    void customerState(CustomerState state) {
        customerState = state;
    }

    public Map<Product, Integer> cart() {
        return cart;
    }
    public void addToCart(Product product, int count) {
        if (product == null)
            throw new NullPointerException();
        if (cart.containsKey(product))
            cart.replace(product, cart.get(product) + count);
        else
            cart.put(product, count);
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
        for (Map.Entry<Product, Integer> entry : cart.entrySet())
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
        return customerState != Visitor.getVisitor();
    }

    /* Affichage */
    public String cartToString() {
        String s = "";
        for (Map.Entry<Product, Integer> entry : cart.entrySet())
            s += entry.getKey().toString() + " : " + entry.getValue().toString() + '\n';
        s += "\n\t";
        s += "Prix total = " + rawPrice() + "€.\n\t";
        return s;
    }
}
