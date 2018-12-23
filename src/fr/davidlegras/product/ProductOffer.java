package fr.davidlegras.product;

import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

/**
 * TODO abstract
 *
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractOffer
 * @see Discountable
 * @see Product
 */
public class ProductOffer<T extends Discountable> extends AbstractOffer<T> {
    ProductOffer(float discount) throws NotInBoundsDiscountException {
        super(discount);
    }

    @Override
    public boolean applicable(Cart<? extends T> cart) {
        return false;
    }

    @Override
    public double applyOffer(double price) {
        return price * (1 + discount());
    }
}
