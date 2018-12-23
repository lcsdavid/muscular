package fr.davidlegras.product;

public interface Offer {

    boolean applicable(Cart cart);

    double applyOffer(double price);
}
