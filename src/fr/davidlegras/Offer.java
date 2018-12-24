package fr.davidlegras;

public interface Offer {

    boolean applicable(Customer customer, Product product);

    double discount();
}
