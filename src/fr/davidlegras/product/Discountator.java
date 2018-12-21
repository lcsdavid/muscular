package fr.davidlegras.product;

import java.util.Objects;

/**
 * Cette interface permet faciliment la définie d'un rabais.
 * La définition ce fait sous forme de lambda-expression.
 *
 * Pour que l'intégralité des fonctions soient cohérentes le comportement de cette interface fonctionnel doit être
 * identique à celle d'une suite géométrique (e.g. (double prix) -> return prix * q).
 *
 * @param <T>
 */
@FunctionalInterface
public interface Discountator {

    double apply(double price);

    default Discountator unapply() {
        return (double price) -> {
            double appliedOnce = apply(price), appliedTwice = apply(appliedOnce);
            if (appliedOnce / price == appliedTwice / appliedOnce) /* Suite géométrique réduction en pourcentage. */
                return Math.pow(price, 2) * apply(price);
            else
                return 0;
        };
    }

    default Discountator thenApplying(Discountator then) {
        Objects.requireNonNull(then);
        return (double price) -> then.apply(apply(price));
    }
}
