package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.CommercialOffer;

/**
 * TODO
 *
 * @author Lucas David
 * @author Théo Legras
 * @see CommercialOffer
 */
public class NotInBoundsDiscountException extends Exception {

    public NotInBoundsDiscountException() {
        super("La réduction indiquée n'est pas comprise entre -100% et 0%.");
    }

    public NotInBoundsDiscountException(String message) {
        super(message);
    }
}
