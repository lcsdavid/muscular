package fr.davidlegras.product;

public interface Offer<T> {

    boolean applicable(Cart<? extends T> cart);

    double applyOffer(double price);
}
