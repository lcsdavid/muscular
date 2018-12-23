package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;

public class CategoryOffer extends AbstractOffer {
    private Class<? extends Product> productClass;

    public CategoryOffer(double discount, Class<? extends Product> productClass) throws DiscountException {
        super(discount);
        if (!Products.isProductDiscountable(productClass))
            throw new NotDiscountableException();
        this.productClass = productClass;
    }

    public CategoryOffer(double discount, Class<? extends CustomerState> customerStateClass, Class<? extends Product> productClass) throws DiscountException {
        super(discount, customerStateClass);
        if (!Products.isProductDiscountable(productClass))
            throw new NotDiscountableException();
        this.productClass = productClass;
    }

    @Override
    public boolean applicable(Customer customer, Product product) {
        return super.applicable(customer, product) && product.getClass().isAssignableFrom(productClass);
    }
}
