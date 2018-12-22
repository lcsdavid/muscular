package fr.davidlegras.product;

import fr.davidlegras.customer.CustomerState;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

import java.util.ArrayList;

/**
 * TODO
 *
 *
 * @author Lucas David
 * @author Théo Legras
 * @see CommercialOffer
 * @see Discountable
 * @see Product
 */
public class FlashOffer<T extends Discountable> extends CommercialOffer<T> {
    /**
     * Tableau dont les clées représentent les produits concernés et leurs valeurs respectives représente la quantité
     * demandé pour appliquer l'offre.
     */
    private Cart<T> cart = new Cart<>();

    /**
     *
     * @param discount
     * @param targets
     * @throws NotInBoundsDiscountException
     */
    FlashOffer(double discount, Cart<? extends T> targetCart, ArrayList<? extends CustomerState> targets) throws NotInBoundsDiscountException {
        super(discount, targets);
        cart.addAll(targetCart);
    }

    @Override
    public boolean applicable(Cart<? extends T> cart) {
        return false;
    }

    @Override
    public double applyOffer(double price) {
        return 0;
    }
}
