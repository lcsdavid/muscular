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
public class ProductOffer<P extends Product, C extends CustomerState> extends AbstractOffer<P, C> {
    public ProductOffer(float discount) throws NotInBoundsDiscountException {
        super(discount);
    }

    
}
