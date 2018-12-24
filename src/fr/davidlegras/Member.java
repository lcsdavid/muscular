package fr.davidlegras.customer;

import fr.davidlegras.Platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Member extends AbstractConnectedCustomer {
    private Collection<LoyaltyCard> loyaltyCards = new ArrayList<>();

    public Member(String name, String lastName, LoyaltyCard... loyaltyCards) {
        super(name, lastName);
        this.loyaltyCards.addAll(Arrays.asList(loyaltyCards));
    }

    @Override
    public int price(final Platform platform, final Customer context) {
        return super.price(platform, context);
        // TODO
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (object.getClass().equals(this.getClass()))
            return true;
        return false;
    }
}
