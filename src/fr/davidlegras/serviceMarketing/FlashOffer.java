package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Product;

import java.util.ArrayList;

public class FlashOffer extends CommercialOffer {
    private boolean used; /* Indique si la réduction à été utilisé ou pas */
    private ArrayList<Product> target; /* La liste des artciles qui sont concernés */


    public FlashOffer(float reduction, ArrayList<Product> target) {
        this.reduction = reduction;
        this.target = target;
        used = false;
    }

    public Boolean getUsed() {
        return used;
    }

    public float getPrice(ArrayList<Product> products){
        float res = 0;
            if(products.containsAll(target)) {//on fait la réduction
                for(Product p : products){//on parcours tous les produits que l'on nous a donné
                    if(target.contains(p))
                        res+= p.getPrice() - p.getPrice()*reduction/100;
                }
            }else{
                for(Product p : products){//on parcours tous les produits que l'on nous a donné
                    res+= p.getPrice();
                }
            }
        return res;
    }

    public float getREduc(ArrayList<Product> products){
        float res = 0;
        if(products.containsAll(target)) {//on fait la réduction
            for(Product p : products){//on parcours tous les produits que l'on nous a donné
                if(target.contains(p))
                    res+= p.getPrice()*reduction/100;
            }
        }
        return res;
    }

}
