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

    protected AbstractProduct(String productTitle, double price, int fidelityPoints) {
        this.productTitle = productTitle;
        this.price = price;
        this.fidelityPoints = fidelityPoints;
        this.discountable = isClassDiscountable();
    }

    protected AbstractProduct(String productTitle, double price, int fidelityPoints, boolean discountable) {
        this.productTitle = productTitle;
        this.price = price;
        this.fidelityPoints = fidelityPoints;
        this.discountable = discountable;
    }

    @Override
    public final String productTitle() {
        return productTitle;
    }

    @Override
    public final double price() {
        return price;
    }

    @Override
    public final int fidelityPoints() {
        return fidelityPoints;
    }

    @Override
    public final boolean isClassDiscountable() {
        return getClass().isAssignableFrom(Discountable.class);
    }

    @Override
    public final boolean isDiscountable() {
        return discountable;
    }
}
