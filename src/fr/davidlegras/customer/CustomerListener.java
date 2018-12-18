package fr.davidlegras.customer;

import fr.davidlegras.product.Product;

import java.util.EventListener;
import java.util.Map;

public interface CustomerListener extends EventListener {

    void onStateChanged(CustomerState previousState, CustomerState newState);

    void onCartChanged(Map<Product, Integer> cart);

}
