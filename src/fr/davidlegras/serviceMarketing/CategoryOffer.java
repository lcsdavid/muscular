package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Category;
import fr.davidlegras.product.Discountable;
import fr.davidlegras.product.Product;

import java.util.ArrayList;
import java.util.Map;

/**
 * TODO abstract
 * @param <T> le type de la Category ciblé.
 *
 * @author Lucas David
 * @author Théo Legras
 * @see fr.davidlegras.serviceMarketing.CommercialOffer
 * @see Discountable
 * @see Category
 */
public class CategoryOffer<T extends Category & Discountable> extends CommercialOffer<T> {
    private T target; /* Target est la cible de l'offre. Par contrainte T hérite au moins de Category & Discountable. */

    /**
     * Constructeur.
     * @param discount la valeur du pourcentage de rabais accordé.
     * @param target la cible du rabais.
     * @throws NotInBoundsDiscountException si le rabais spécifié n'est pas compris entre -1 et 0.
     */
    public CategoryOffer(float discount, T target) throws NotInBoundsDiscountException {
        super(discount);
        this.target = target;
    }

    public T target() {
        return target;
    }

    /**
     * Renvoie le montant du Product après l'application du rabais par cette réduction de Category de Product.
     * Plus particulièrement, si le Product est concerné on retourne la valeur conséquente, sinon on retourne la
     * du Product sans modification.
     *
     * @param product le Product dont on veut obtenir le prix réduit.
     * @param <S> TODO
     * @return le prix du Product rabais compris.
     */
    public <S extends Product> float discountedPrice(S product) {
        return product.price() + effectiveDiscount(product);
    }

    /**
     * Renvoie le montant du rabais sur Product par cette réduction de Category de Product.
     * Plus particulièrement, si le Product est concerné on retourne la valeur conséquente, sinon on retourne 0.
     *
     * @param product le Product dont le lequel on voit obtenir le montant du rabais.
     * @param <S> TODO
     * @return le montant du rabais.
     */
    public <S extends Product> float effectiveDiscount(S product) {
        if (product.category().equals(target))
            return product.price() * discount();
        return 0;
    }

    @Override
    public String toString() {
        return "Réduction sur tous les produits de la catégorie " + target.toString() + " à hauteur de " + discount() + "%.";
    }
}
