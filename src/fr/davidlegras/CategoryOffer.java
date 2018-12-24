package fr.davidlegras;

public class CategoryOffer extends AbstractOffer {
    private Class<? extends Product> productClass;

    public CategoryOffer(double discount, Class<? extends Product> productClass) throws NotInBoundsDiscountException, NotDiscountableException {
        super(discount);
        if (!Products.isProductDiscountable(productClass))
            throw new NotDiscountableException();
        this.productClass = productClass;
    }

    public CategoryOffer(double discount, Class<? extends CustomerState> customerStateClass, Class<? extends Product> productClass) throws NotInBoundsDiscountException, NotDiscountableException {
        super(discount, customerStateClass);
        if (!Products.isProductDiscountable(productClass))
            throw new NotDiscountableException();
        this.productClass = productClass;
    }

    @Override
    public boolean applicable(Customer customer, Product product) {
        return super.applicable(customer, product) && productClass.isAssignableFrom(product.getClass());
    }

    @Override
    public String toString() {
        return super.toString() + " sur les " + productClass.getSimpleName();
    }
}
