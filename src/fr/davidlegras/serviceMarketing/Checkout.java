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

    private float getReduction(Map<Product,Integer> cart){
        float res = 0;
        for (CommercialOffer offer: offers) {
            res += offer.getReduction(cart);
        }
        return res;
    }

    public float getPrice(Map<Product, Integer> cart){
        float res = 0;
        for (Map.Entry<Product, Integer> entry: cart.entrySet() ) {
            res += entry.getKey().getPrice()*entry.getValue();
        }
        //TODO ajouter exception pour le cas ou la prix est 0

        res -= getReduction(cart);

        //si les réductions sont plus grandes que le prix on remène le prix à 0
        //ce cas n'est pas concidéré comme une erreure car on accepte le cumul des réductions.
        if(res <= 0)
            res = 0;

        return res;
    }
}
