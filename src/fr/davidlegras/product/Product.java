package fr.davidlegras.product;

/**
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractProduct
 */
public interface Product {

    String productTitle();

    double price();

    default int fidelityPoints() {
        return 0;
    }

    boolean isClassDiscountable();

    boolean isDiscountable();
}
