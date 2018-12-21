package fr.davidlegras.product;

import java.util.ArrayList;
import java.util.Collection;

public class DiscountableProduct extends Product implements Discountable<DiscountableProduct> {
    private Collection<Offer<DiscountableProduct>> offers = new ArrayList<>();

    DiscountableProduct(String name, float price) {
        super(name, null, price);
    }

    @Override
    public void attachOffer(Offer<DiscountableProduct> offer) {
        offers.add(offer);
    }

    @Override
    public void attachOffers(Collection<? extends Offer<DiscountableProduct>> offers) {
        this.offers.addAll(offers);
    }

    @Override
    public double applyDiscount() {
        for (Offer<DiscountableProduct> offer: offers)
            if (offer.
            price()
        return 0;
    }
}
