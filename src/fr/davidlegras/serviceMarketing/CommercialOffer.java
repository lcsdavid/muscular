package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Discountable;

/**
 * TODO
 *
 * @author Lucas David
 * @author Théo Legras
 * @see fr.davidlegras.serviceMarketing.CommercialOffer
 * @see Discountable
 * @see fr.davidlegras.serviceMarketing.CategoryOffer
 * @see fr.davidlegras.serviceMarketing.ProductOffer
 * @see fr.davidlegras.serviceMarketing.FlashOffer
 */
public abstract class CommercialOffer<T extends Discountable> {
    private float discount; /* Pourcentage de réduction (e.g. -0.1 ou -0.5 respectivements -10% et -50%). */

    public CommercialOffer(float discount) throws NotInBoundsDiscountException {
        super();
        if (discount < -1 || discount > 0)
            throw new NotInBoundsDiscountException("La réduction " + discount * 100 + "% n'est pas comprise entre -100% et 0%.");
        this.discount = discount;
    }

    public float discount() {
        return discount;
    }
}
