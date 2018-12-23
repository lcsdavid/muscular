package fr.davidlegras.product;

/**
 * @author Lucas David
 * @author Théo Legras
 * @see Product
 * @see Book
 */
public abstract class AbstractProduct implements Product {
    private String productTitle;
    private double price;
    private int gainInLoyaltyPoints;

    private boolean discountable;

    protected AbstractProduct(String productTitle, double price, int gainInLoyaltyPoints) {
        this(productTitle, price, gainInLoyaltyPoints, false);
    }

    protected AbstractProduct(String productTitle, double price, int gainInLoyaltyPoints, boolean discountable) {
        super();
        this.productTitle = productTitle;
        this.price = price;
        this.gainInLoyaltyPoints = gainInLoyaltyPoints;
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
    public final int gainInLoyaltyPoints() {
        return gainInLoyaltyPoints;
    }

    @Override
    public final boolean isDiscountable() {
        return discountable;
    }
}
