package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;

import java.util.Map;

/**
 * TODO
 *
 *
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractOffer
 * @see CustomerState
 */
public class FlashOffer extends AbstractOffer {
    /**
     * Tableau dont les clées représentent les produits concernés et leurs valeurs respectives représente la quantité
     * demandé pour appliquer l'offre.
     */
    private Cart target;

    public FlashOffer(double discount, Cart target) throws DiscountException {
        super(discount);
        for (Map.Entry<Product, Integer> entry: target)
            if (!entry.getKey().isDiscountable())
                throw new NotDiscountableException();
        this.target = (Cart) target.clone();
    }

    /**
     *
     * @param discount pourcentage de réduction sur la panier.
     * @param target panier sensible à la réduction.
     * @throws DiscountException
     */
    public FlashOffer(double discount, Class<? extends CustomerState> customerStateClass, Cart target) throws DiscountException {
        super(discount, customerStateClass);
        for (Map.Entry<Product, Integer> entry: target)
            if (!entry.getKey().isDiscountable())
                throw new NotDiscountableException();
        this.target = (Cart) target.clone();
    }

    @Override
    public boolean applicable(Customer customer, Product product) {
        return super.applicable(customer, product) && customer.cart().contains(target);
    }

}
