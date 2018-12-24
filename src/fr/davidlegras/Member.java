package fr.davidlegras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class Member extends AbstractConnectedCustomer {
    private Collection<LoyaltyCard> loyaltyCards = new ArrayList<>();

    public Member(String name, String lastName, LoyaltyCard... loyaltyCards) {
        super(name, lastName);
        this.loyaltyCards.addAll(Arrays.asList(loyaltyCards));
    }

    @Override
    public double price(final Platform platform, final Customer context) {
        double price = 0;
        for (Map.Entry<Product, Integer> entry : context.cart()) {
            double productDiscount = 1;
            for (Offer offer : platform.offers()) {
                if (offer.applicable(context, entry.getKey())) {
                    productDiscount *= (1 + offer.discount());
                    if (loyaltyCards.size() > 0)
                        loyaltyCards.iterator().next().add(entry.getKey().gainInLoyaltyPoints() * entry.getValue());
                }
            }
            price += entry.getKey().price() * productDiscount;
        }
        for (LoyaltyCard loyaltyCard : loyaltyCards)
            if (loyaltyCard.isUsable() && price > LoyaltyCard.FLAT_DISCOUNT_VALUE) {
                try {
                    price -= loyaltyCard.discount();
                } catch (NotEnoughFidelityPointsException ignored) {
                }
            }
        return price;
    }

    @Override
    public String toString() {
        String s = super.toString() + " [Cartes de fidelité possédées:";
        for (LoyaltyCard loyaltyCard : loyaltyCards)
            s += " " + loyaltyCard.toString();
        s += " points]";
        return s;
    }
}
