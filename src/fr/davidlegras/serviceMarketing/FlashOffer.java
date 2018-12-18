package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Product;

import java.util.ArrayList;
import java.util.Map;

public class FlashOffer extends CommercialOffer {
    private ArrayList<Product> target; /* La liste des artciles qui sont concernés */


    public FlashOffer(float reduction, ArrayList<Product> target) throws NotInBoundsReductionException, NotAPromouvableProductException {
        if (reduction > 100 || reduction < 0) {
            throw new NotInBoundsReductionException("Reduction non comprise entre 0 et 100");
        }
        for (Product product : target) {
            if (!product.isDiscountable())
                throw new NotAPromouvableProductException("Il est impossible de promouvoir ce produit");
        }
        this.reduction = reduction;
        this.target = target;
    }

    public float getPrice(ArrayList<Product> products) {
        float res = 0;
        if (products.containsAll(target)) {//on fait la réduction
            for (Product p : products) {//on parcours tous les produits que l'on nous a donné
                if (target.contains(p))
                    res += p.getPrice() - p.getPrice() * reduction / 100;
            }
        } else {
            for (Product p : products) {//on parcours tous les produits que l'on nous a donné
                res += p.getPrice();
            }
        }
        return res;
    }

    public float getReduc(ArrayList<Product> products) {
        float res = 0;
        if (products.containsAll(target)) {//on fait la réduction
            for (Product p : products) {//on parcours tous les produits que l'on nous a donné
                if (target.contains(p))
                    res += p.getPrice() * reduction / 100;
            }
        }
        return res;
    }


    //est-ce que la Map passée en entré contient tous les produits nécessaires pour appliquer la réduction
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

    @Override
    public String toString() {
        String res = "Flash Offer : " + reduction + "%";

        for (Product product : target) {
            res += "\n" + product.toString();
        }

        return res;
    }

}
