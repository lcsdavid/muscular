package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Product;

import java.util.ArrayList;

public class ProductOffer extends CommercialOffer {
    public ProductOffer (float reduction, String target){
        this.reduction = reduction;
        this.target = target;
    }

    private float reduction;
    private String target;//le nom ou la catégorie du/des produits ciblés

    public float getReduction(){return reduction;}

    public String getTarget() {
        return target;
    }

    public float getPrice(Product product){
        if(product.getCategorie().equals(target) || product.getName().equals(target))
            return (product.getPrice() - (product.getPrice()*reduction/100));
        return product.getPrice();//le prix du produit ne change pas avec cette réduction
    }

    public float getPrice(ArrayList<Product> products){
        float res = 0;
        for(Product p : products){
            res += getPrice(p);
        }
        return res;
    }
}
