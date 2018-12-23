package fr.davidlegras.product;

public class HighTech extends AbstractProduct {

    public HighTech(String productTitle, double price) {
        super(productTitle, price);
    }

    public static boolean IsClassDiscountable() {
        return true;
    }

    @Override
    public boolean isClassDiscountable() {
        return super.isClassDiscountable();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
