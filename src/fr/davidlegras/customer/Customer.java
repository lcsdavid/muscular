package fr.davidlegras.customer;

import fr.davidlegras.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private CustomerState customerState;
    private Map<Product, Integer> cart;

    public Customer() {
        super();
        customerState = new VisitorFactory().makeCustomerState();
        cart = new HashMap<>();
    }

    public Customer(String login, String passwordHash) {
        this();
        try {
            signIn(login, passwordHash);
        } catch (AlreadySignedInException ignored) {} /* Never catch in this case. */

    }

    /* Acesseurs & Mutateurs */

    void customerState(CustomerState state) {
        customerState = state;
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

    public Map<Product, Integer> getCart() {
        return cart;
    }

    /* Prix */

    public float price() {
        return customerState.price(this);
    }

    public float rawPrice() {
        float cartPrice = 0;
        for (Map.Entry<Product, Integer> entry: cart.entrySet())
            cartPrice += entry.getKey().getPrice() * entry.getValue();
        return cartPrice;
    }

    /* Connexion & Déconnexion */

    public void signIn(final String login, final String passwordHash) throws AlreadySignedInException {
        customerState.signIn(this, login, passwordHash);
    }

    public void signOut() throws NotSignedInException {
        customerState.signOut(this);
    }

    /* Affichage */
    public String cartToString(){
        String s = "";
        for (Map.Entry<Product,Integer> entry:cart.entrySet())
            s += entry.getKey().toString() + " : " + entry.getValue().toString() + '\n';
        s += "\n\t";
        s += "Prix total = " + rawPrice() + "€.\n\t";
        s += "Prix: " + price() + "€.\n";
        return s;
    }
}
