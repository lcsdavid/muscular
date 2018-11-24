package fr.davidlegras.serviceMarketing;

import fr.davidlegras.product.Product;

import java.util.ArrayList;
import java.util.Map;

public final class Checkout {
    private Checkout (){
        offers = new ArrayList<>();
    }

    private static final Checkout instance = new Checkout();

    private ArrayList<CommercialOffer> offers;


    public float checkout (Map<Product, Integer> cart){
        float res = 0;

        return res;
    }
    public void addOffer(CommercialOffer offer){
        offers.add(offer);
    }
    public static Checkout getCheckout(){
        return instance;
    }
}
