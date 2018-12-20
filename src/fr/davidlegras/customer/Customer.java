package fr.davidlegras.customer;

import fr.davidlegras.product.Product;
import javafx.application.Platform;

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

    public Customer(String login, String passwordHash) throws WrongCredentials {
        this();
        try {
            signIn(login, passwordHash);
        } catch (AlreadySignedInException ignored) { /* Never catch in this case. */ }
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

    public void signIn(final Platform platform, String login, String passwordHash) throws AlreadySignedInException, WrongCredentials {
        customerState.signIn(platform, this, login, passwordHash);
    }

    public void signOut(final Platform platform) throws NotSignedInException {
        customerState.signOut(platform, this);
    }

    public boolean isSignedIn() {
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
