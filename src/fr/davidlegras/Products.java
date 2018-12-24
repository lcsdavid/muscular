package fr.davidlegras;

public class Products {

    static boolean isProductDiscountable(Class<? extends Product> productClass) {
        return productClass.isAssignableFrom(Discountable.class);
    }
}
