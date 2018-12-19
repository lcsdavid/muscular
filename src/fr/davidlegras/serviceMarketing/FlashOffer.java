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

    private boolean containsTargets(Map<? super Product, Integer> cart) {
        for (Map.Entry<T, Integer> entry: targets.entrySet()) {
            if (!cart.containsKey(entry.getKey()))
                return false;
            if (Integer.compare(cart.get(entry.getKey()), entry.getValue()) < 0)
                return false;
        }
        return true;

        /* Ancienne version
        for (int i = 0; i < targets.size(); i++) {
            if (!cart.containsKey(target.get(i))) //l'un des produit n'est pas dans le panier
                return false;
            if (cart.containsKey(target.get(i))) {
                if (cart.get(target.get(i)) == 0) //un produit est enregistré mais la quantité est à 0
                    return false;
            }
        }
        return true; */
    }

    @Override
    public <S extends Product & Discountable> boolean isTarget(S product) {
        return targets.containsKey(product);
    }

    @Override
    public float apply(Map<? super Product, Integer> cart) {
        if (!containsTargets(cart))
            return 0;
        float price = 0;
        for (Map.Entry<T, Integer> entry: targets.entrySet()) {
            price += discountedPrice(entry.getKey());
            int remaining = cart.get(entry.getKey()) - entry.getValue();
            if (remaining > 0) /* N'est jamais en dessous de 0 (cf. containsTargets). */
                cart.replace(entry.getKey(), remaining);
            else
                cart.remove(entry.getKey());
        }
        return price + (containsTargets(cart) ? apply(cart) : 0);

        /* Ancienne version
        float res = 0;
        if (!containsAll(cart))//dans ce cas la réduction ne s'applique pas
            return 0;
        //on va appliquer la réduction sur tous les produits
        for (Product entry : target) {
            res += cart.get(entry) * (reduction / 100);
        }
        return res;
        */
    }

    /**
     * Renvoie le montant du Product après l'application du rabais par cette réduction de Category de Product.
     * Plus particulièrement, si le Product est concerné on retourne la valeur conséquente, sinon on retourne la
     * du Product sans modification.
     *
     * @param product le Product dont on veut obtenir le prix réduit.
     * @return le prix du Product rabais compris.
     */
    @Override
    public <S extends Product & Discountable> float discountedPrice(S product) {
        return product.price() + effectiveDiscount(product);
    }

    /**
     * Renvoie le montant du rabais sur Product par cette réduction de Category de Product.
     * Plus particulièrement, si le Product est concerné on retourne la valeur conséquente, sinon on retourne 0.
     *
     * @param product le Product dont le lequel on voit obtenir le montant du rabais.
     * @return le montant du rabais.
     */
    @Override
    public <S extends Product & Discountable> float effectiveDiscount(S product) {
        if (isTarget(product))
            return product.price() * discount();
        return 0;
    }

    @Override
    public String toString() {
        String s = "Offre flash ! Réduction sur les produits suivant:\n";
        for (Map.Entry<T, Integer> entry: targets.entrySet())
            s += '\t' + entry.getKey().toString() + " x" + entry.getValue().toString();
        s += "à hauteur de " + discount() * 100 + "%.";
        return s;
    }
}
