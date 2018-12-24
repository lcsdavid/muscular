package fr.davidlegras;

/**
 * TODO abstract
 *
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractOffer
 * @see Discountable
 * @see Product
 */
public class ProductOffer extends AbstractOffer {
    private Product target;

    public ProductOffer(double discount, Product target) throws NotInBoundsDiscountException, NotDiscountableException {
        super(discount);
        if (!Products.isProductDiscountable(target.getClass()))
            throw new NotDiscountableException();
        this.target = target;
    }

    @Override
    public boolean applicable(Customer customer, Product product) {
        return super.applicable(customer, product) && product.equals(target);
    }

    @Override
    public String toString() {
        return super.toString() + " sur le produit " + target.toString();
    }
}
