package fr.davidlegras.product;

import fr.davidlegras.customer.CustomerState;
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
public class ProductOffer extends AbstractOffer {
    private Product target;

    public ProductOffer(Product target, float discount) throws NotInBoundsDiscountException, NotDiscountableException {
        super(discount);
        if (!target.isDiscountable())
            throw new NotDiscountableException();
        this.target = target;
    }
}
