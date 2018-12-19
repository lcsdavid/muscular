package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Discountable;
import fr.davidlegras.product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @param <T>
 *
 * @author Lucas David
 * @author Théo Legras
 * @see fr.davidlegras.serviceMarketing.CommercialOffer
 * @see Discountable
 * @see Product
 */
public class FlashOffer<T extends Product & Discountable> extends CommercialOffer<T> {
    /**
     * Tableau dont les clées représentent les produits concernés et leurs valeurs respectives représente la quantité
     * demandé pour appliquer l'offre.
     */
    private Map<T, Integer> targets = new HashMap<>();

    /**
     *
     * @param discount
     * @param targets
     * @throws NotInBoundsDiscountException
     */
    public FlashOffer(float discount, Map<? extends T, Integer> targets) throws NotInBoundsDiscountException {
        super(discount);
        this.targets.putAll(targets);
    }

    public Map<T, Integer> targets() {
        return targets;
    }

    /**
     * Renvoie le montant du Product après l'application du rabais par cette réduction de Category de Product.
     * Plus particulièrement, si le Product est concerné on retourne la valeur conséquente, sinon on retourne la
     * du Product sans modification.
     *
     * @param product le Product dont on veut obtenir le prix réduit.
     * @return le prix du Product rabais compris.
     */
    public float discountedPrice(T product) {
        return product.price() + effectiveDiscount(product);
    }

    /**
     * Renvoie le montant du rabais sur Product par cette réduction de Category de Product.
     * Plus particulièrement, si le Product est concerné on retourne la valeur conséquente, sinon on retourne 0.
     *
     * @param product le Product dont le lequel on voit obtenir le montant du rabais.
     * @return le montant du rabais.
     */
    public float effectiveDiscount(T product) {
        if (targets.containsKey(product))
            return product.price() * discount();
        return 0;
    }

    // TODO convertir
    /*
    // est-ce que la Map passée en entré contient tous les produits nécessaires pour appliquer la réduction
    private boolean containsAll(Map<Product, Integer> cart) {

        for (int i = 0; i < target.size(); i++) {
            if (!cart.containsKey(target.get(i)))//l'un des produit n'est pas dans le panier
                return false;
            if (cart.containsKey(target.get(i))) {
                if (cart.get(target.get(i)) == 0)//un produit est enregistré mais la quantité est à 0
                    return false;
            }
        }

        return true;
    }

    @Override
    public float getReduction(Map<Product, Integer> cart) {

        float res = 0;


        if (!containsAll(cart))//dans ce cas la réduction ne s'applique pas
            return 0;


        //on va appliquer la réduction sur tous les produits
        for (Product entry : target) {
            res += cart.get(entry) * (reduction / 100);
        }

        return res;
    }

    */

    @Override
    public String toString() {
        String s = "Offre flash ! Réduction sur les produits suivant:\n";
        for (Map.Entry<T, Integer> entry: targets.entrySet())
            s += '\t' + entry.getKey().toString() + " x" + entry.getValue().toString();
        s += "à hauteur de " + discount() * 100 + "%.";
        return s;
    }
}
