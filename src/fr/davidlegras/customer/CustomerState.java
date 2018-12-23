package fr.davidlegras.customer;

import fr.davidlegras.Platform;
import fr.davidlegras.product.Offer;
import fr.davidlegras.product.Product;

import java.util.Map;

public interface CustomerState {

    default int price(final Platform platform, final Customer context) {
        int res = 0;
        for (Map.Entry<Product, Integer> entry : context.cart()) {
            float productDiscount = 1;
            for (Offer offer : platform.offers()) {
                if (offer.applicable(context, entry.getKey()))
                    productDiscount *= (1 - offer.discount());
            }
            res += entry.getKey().price() * productDiscount;
        }
        return res;
    }

    void connect(final Platform platform, final Customer context, String login, String passwordHash) throws AlreadyConnectedException, WrongCredentials;

    void disconnect(final Platform platform, final Customer context) throws NotConnectedException;
}
