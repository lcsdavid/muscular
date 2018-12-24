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
        return super.applicable(customer, product) && product.getClass().isAssignableFrom(productClass);
    }

    public double getReduction(Product product) {
        if (product.getClass().equals(productClass))
            return discount();
        return 0;
    }
}
