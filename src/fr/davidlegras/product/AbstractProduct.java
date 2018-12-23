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

    protected AbstractProduct(String productTitle, double price, int gainInLoyaltyPoints) throws NegativePriceException {
        super();
        if(price < 0)
            throw new NegativePriceException("Le prix est négatif et ne peut pas être attribué !");
        this.productTitle = productTitle;
        this.price = price;
        this.gainInLoyaltyPoints = gainInLoyaltyPoints;
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
}
