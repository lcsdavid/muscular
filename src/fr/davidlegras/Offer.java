package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;

public interface Offer {

    boolean applicable(Customer customer, Product product);

    double discount();
}
