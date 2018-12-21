package fr.davidlegras.product;

import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

/**
 * TODO abstract
 *
 * @author Lucas David
 * @author Théo Legras
 * @see CommercialOffer
 * @see Discountable
 * @see Product
 */
public class ProductOffer<T extends Product & Discountable> extends CommercialOffer<T> {
    ProductOffer(float discount) throws NotInBoundsDiscountException {
        super(discount);
    }

    @Override
    public double applyOffer(double price) {
        return price * (1 + discount());
    }
}
