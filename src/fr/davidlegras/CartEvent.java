package fr.davidlegras;

import java.util.EventObject;

public class CartEvent extends EventObject {
    private Cart oldCart, newCart;
    private Product product;
    private int count;

    /**
     * Constructs a {@code CartEvent}.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CartEvent(Object source, Cart oldCart, Cart newCart, Product product, int count) {
        super(source);
        this.oldCart = oldCart;
        this.newCart = newCart;
        this.product = product;
        this.count = count;
    }

    public Cart getOldCart() {
        return oldCart;
    }

    public Cart getNewCart() {
        return newCart;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }
}
