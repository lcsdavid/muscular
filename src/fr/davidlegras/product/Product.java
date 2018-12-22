package fr.davidlegras.product;

/**
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractProduct
 */
public interface Product {

    String productTitle();

    double price();

    default boolean isClassDiscountable() {
        return getClass().isAssignableFrom(Discountable.class);
    }

    boolean isDiscountable();
}
