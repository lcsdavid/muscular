package fr.davidlegras.product;

import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

/**
 * TODO
 *
 *
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractOffer
 * @see Discountable
 * @see Product
 */
public class FlashOffer<T, C> extends AbstractOffer<T, C> {
    /**
     * Tableau dont les clées représentent les produits concernés et leurs valeurs respectives représente la quantité
     * demandé pour appliquer l'offre.
     */
    private Cart cart = new Cart<>();

    /**
     *
     * @param discount pourcentage de réduction sur la panier.
     * @param targetCart panier sensible à la réduction.
     * @throws NotInBoundsDiscountException
     */
    public FlashOffer(double discount, Cart targetCart) throws NotInBoundsDiscountException {
        super(discount);
        cart.addAll(targetCart);
    }

    @Override
    public boolean applicable(Cart cart) {
        return false;
    }

    @Override
    public double applyOffer(double price) {
        return 0;
    }
}
