package fr.davidlegras.serviceMarketing;

import java.util.ArrayList;

import fr.davidlegras.product.Product;

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


}
