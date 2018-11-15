package fr.davidlegras.customer;

import fr.davidlegras.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String firstName;
    private CustomerState customerState;
    private Map<Product, Integer> cart;

    public Customer() {
        super();
        firstName = null;
        customerState = new VisitorFactory().makeCustomerState();
        cart = new HashMap<>();
    }

    public Customer(String login, String password) {

    }

    public final int rawPrice() {
        int cartPrice = 0;
        for (Map.Entry<Product, Integer> entry: cart.entrySet()) {
            cartPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return cartPrice;
    }

    public int price() {
        return customerState.price(this);
    }

    public void signIn(String login, String password) throws AlreadySignedInException {
        customerState.signIn(login, password);
    }

    public void signOut() throws NotSignedInException {
        customerState.signOut();
    }

    public void addToCart(Product product, int count) {
        if (product == null)
            throw new NullPointerException();
        // TODO
    }

    public void addToCart(Product product) {
        addToCart(product, 1);
    }
}
