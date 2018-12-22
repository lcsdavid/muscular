package fr.davidlegras.serviceMarketing;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.product.Product;

import java.util.ArrayList;
import java.util.Map;

public final class Checkout {

    /* declaration du singleton*/

    private static final Checkout instance = new Checkout();
    private ArrayList<CommercialOffer> offers;

    private Checkout() {
        offers = new ArrayList<>();
    }
    /* fin de la déclaration du singleton */

    public static Checkout getCheckout() {
        return instance;
    }

    public float checkout(Map<Product, Integer> cart) {
        float res = 0;

        return res;
    }

    public void addOffer(CommercialOffer offer) {
        offers.add(offer);
    }

    private float getReduction(Customer customer) {
        float res = 0;
        for (CommercialOffer offer : offers) {
            res += offer.getReduction(customer);
        }
        return res;
    }

    public float getPrice(Customer customer) {
        float res = 0;
        for (Map.Entry<Product, Integer> entry : customer.getCart().entrySet()) {
            res += entry.getKey().getPrice() * entry.getValue();
        }

        res -= getReduction(customer);

        //si les réductions sont plus grandes que le prix on remène le prix à 0
        //ce cas n'est pas concidéré comme une erreure car on accepte le cumul des réductions.
        if (res <= 0)
            res = 0;

        return res;
    }
}
