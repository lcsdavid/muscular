package fr.davidlegras.product;

import fr.davidlegras.customer.CustomerState;

public interface Offer<P extends Product, C extends CustomerState> {

    boolean applicable(C customerState, Cart cart, P product);

    double applyOffer(double price);
}
