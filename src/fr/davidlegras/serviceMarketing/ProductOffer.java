package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Product;

import java.util.ArrayList;

public class ProductOffer extends CommercialOffer {

    private String target; //le nom ou la catégorie du/des produits ciblés

    public ProductOffer (float reduction, String target){
        if(reduction > 100)
            reduction = 0;//créer une exception
        this.reduction = reduction;
        this.target = target;
    }



    public float getReduction(){return reduction;}

    public String getTarget() {
        return target;
    }

    public float getPrice(Product product){//on renvoie la valeur du produit après réduction
        if(product.getCategorie().equals(target) || product.getName().equals(target))
            return (product.getPrice() - (product.getPrice()*reduction/100));
        return product.getPrice();//le prix du produit ne change pas avec cette réduction
    }

    public float getReduction(ArrayList<Product> products){
        float res = 0;
        for(Product p : products){
            if(p.getCategorie().equals(target) || p.getName().equals(target))
                res += (p.getPrice() - (p.getPrice()*reduction/100));
            else { res += p.getPrice();}
        }
        return res;
    }
}
