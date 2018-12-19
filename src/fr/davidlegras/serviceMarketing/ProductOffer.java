package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Discountable;
import fr.davidlegras.product.Product;

import java.util.ArrayList;
import java.util.Map;

/**
 * TODO abstract
 * @param <T> le type du Product ciblé.
 *
 * @author Lucas David
 * @author Théo Legras
 * @see fr.davidlegras.serviceMarketing.CommercialOffer
 * @see Discountable
 * @see Product
 */
public class ProductOffer<T extends Product & Discountable> extends CommercialOffer<T> {
    private T target; /* Target est la cible de l'offre. Par contrainte T hérite au moins de Product & Discountable. */

    /**
     * TODO
     * @param discount
     * @param target
     * @throws NotInBoundsDiscountException
     */
    public ProductOffer(float discount, T target) throws NotInBoundsDiscountException {
        super(discount);
        this.target = target;
    }

    public T target() {
        return target;
    }

    /**
     * Renvoie le montant du Product après l'application du rabais par cette réduction du Product.
     *
     * @return le prix du Product rabais compris.
     */
    public float discountedPrice() {
        return target.price() + effectiveDiscount();
    }

    /**
     * Renvoie le montant du rabais sur Product par cette réduction du Product.
     *
     * @return le montant du rabais.
     */
    public float effectiveDiscount() {
        return target.price() * discount();
    }

    @Override
    public String toString() {
        return "Réduction sur le produit " + target.toString() + " à " + discount() * 100 + "%.";
    }
}
