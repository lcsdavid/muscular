package fr.davidlegras.product;

import java.util.Objects;

public class HighTech extends AbstractProduct implements Discountable {

    public HighTech(String productTitle, double price, int fidelityPoints) {
        super(productTitle, price, fidelityPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productTitle(), price());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof HighTech) {
            HighTech ht = (HighTech) obj;
            if (!productTitle().equals(ht.productTitle()))
                return false;
            if (Double.compare(price(), ht.price()) != 0)
                return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
