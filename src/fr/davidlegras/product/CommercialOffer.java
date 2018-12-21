package fr.davidlegras.product;

import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

/**
 * TODO
 *
 * @author Lucas David
 * @author Théo Legras
 * @see CommercialOffer
 * @see Discountable
 * @see ProductOffer
 * @see FlashOffer
 */
public abstract class CommercialOffer<T extends Discountable> implements Offer<T> {
    /* Pourcentage de réduction (e.g. -0.1 ou -0.5 respectivements -10% et -50%). */
    private double discount;

    CommercialOffer(double discount) throws NotInBoundsDiscountException {
        super();
        if (discount < -1 || discount > 0)
            throw new NotInBoundsDiscountException("La réduction " + discount * 100 + "% n'est pas comprise entre -100% et 0%.");
        this.discount = discount;
    }

    protected double discount() {
        return discount;
    }

    @Override
    public String toString() {
        return Double.toString(discount * 100) + '%';
    }
}
