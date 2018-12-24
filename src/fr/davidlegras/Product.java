package fr.davidlegras.product;

/**
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractProduct
 */
public interface Product {

    String productTitle();

    double price();

    default int gainInLoyaltyPoints() {
        return 0;
    }
}
