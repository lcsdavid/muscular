package fr.davidlegras.product;

public interface Offer<T> {

    boolean applicable(Cart cart);

    double applyOffer(double price);
}
