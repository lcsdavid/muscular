package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Product;

import java.util.Map;

public abstract class CommercialOffer {
    protected float reduction; /* Pourcentage de réduction */

    public float getReduction() {
        return reduction;
    }

    public abstract float getReduction(Map<Product, Integer> cart);
}
