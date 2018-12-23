package fr.davidlegras.product;

/**
 *
 * @author Lucas David
 * @author Théo Legras
 * @see Product
 * @see Book
 */
public abstract class AbstractProduct implements Product {
    private String productTitle;
    private double price;
    private int fidelityPoints;

    private boolean discountable;

    protected AbstractProduct(String productTitle, double price) {
        this.productTitle = productTitle;
        this.price = price;
    }

    @Override
    public String productTitle() {
        return productTitle;
    }

    @Override
    public final double price() {
        return price;
    }

    @Override
    public boolean isDiscountable() {
        return discountable;
    }
}
