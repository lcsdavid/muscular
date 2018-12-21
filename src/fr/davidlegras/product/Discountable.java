package fr.davidlegras.product;

import java.util.Collection;

/**
 * Un Discountable est un objet qui escomtable.
 *
 * @author Lucas David
 * @author Théo Legras
 * @see ProductOffer
 * @see FlashOffer
 */
public interface Discountable<T> {
    void attachOffer(Offer<T> offer);

    void attachOffers(Collection<? extends Offer<T>> offers);

    double applyDiscount();
}
