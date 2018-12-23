package fr.davidlegras.product;

/**
 * TODO
 *
 *
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractOffer
 * @see Product
 */
public class FlashOffer extends AbstractOffer {
    /**
     * Tableau dont les clées représentent les produits concernés et leurs valeurs respectives représente la quantité
     * demandé pour appliquer l'offre.
     */
    private Cart target;

    /**
     *
     * @param discount pourcentage de réduction sur la panier.
     * @param targetCart panier sensible à la réduction.
     * @throws NotInBoundsDiscountException
     */
    public FlashOffer(double discount, Cart target) throws DiscountException {
        super(discount);
        this.target = target.clone();
    }

}
