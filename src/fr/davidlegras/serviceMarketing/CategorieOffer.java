package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Category;
import fr.davidlegras.product.Product;

import java.util.ArrayList;
import java.util.Map;

public class CategorieOffer extends CommercialOffer {

    private Category target; //la catégorie du/des produits ciblés


    public CategorieOffer(float reduction, Category target) throws NotInBoundsReductionException, NotAPromouvableProductException {
        if (reduction > 100 || reduction < 0) {
            throw new NotInBoundsReductionException("Reduction non comprise entre 0 et 100");
        }
        if (!target.isDiscountable())
            throw new NotAPromouvableProductException("Produit non promouvalbe");
        this.reduction = reduction;
        this.target = target;
    }

    public float getReduction() {
        return reduction;
    }

    public Category getTarget() {
        return target;
    }

    public float getPrice(Product product) {//on renvoie la valeur du produit après réduction
        if (product.getCategory().equals(target.getName()))
            return (product.getPrice() - (product.getPrice() * reduction / 100));
        return product.getPrice();//le prix du produit ne change pas avec cette réduction
    }

    public float getPrice(ArrayList<Product> products) {
        float res = 0;
        for (Product p : products) {
            res += getPrice(p);
        }
        return res;
    }

    public float getReduction(Product product) {//on renvoie la valeur du produit après réduction
        if (product.getCategory().equals(target.getName()))
            return (product.getPrice() * reduction / 100);
        return 0;//le prix du produit ne change pas avec cette réduction
    }

    public float getReduction(ArrayList<Product> products) {
        float res = 0;
        for (Product p : products) {
            res += getReduction(p);
        }
        return res;
    }

    @Override
    public float getReduction(Map<Product, Integer> cart) {

        float res = 0;

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            res += getReduction(entry.getKey()) * entry.getValue();
        }

        return res;
    }

    @Override
    public String toString() {
        return "Cible : " + target.toString() + ", " + reduction + "%";
    }
}
