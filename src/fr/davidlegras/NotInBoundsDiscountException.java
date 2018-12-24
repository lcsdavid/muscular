package fr.davidlegras;

/**
 * TODO
 *
 * @author Lucas David
 * @author Théo Legras
 * @see DiscountException
 * @see Offer
 */
public class NotInBoundsDiscountException extends DiscountException {

    public NotInBoundsDiscountException() {
        super("La réduction indiquée n'est pas comprise entre -100% et 0%.");
    }

    public NotInBoundsDiscountException(String message) {
        super(message);
    }
}
