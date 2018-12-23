package fr.davidlegras.product;

public class HighTech extends AbstractProduct implements Discountable {

    public HighTech(String productTitle, double price, int fidelityPoints) {
        super(productTitle, price, fidelityPoints);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
