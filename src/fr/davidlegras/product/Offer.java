package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;

public interface Offer<P extends Product, C extends CustomerState> {

    boolean applicable(Customer customer, Product product);

    double discount();
}
