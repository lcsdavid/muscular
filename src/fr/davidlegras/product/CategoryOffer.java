package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

public class CategoryOffer extends AbstractOffer {
    private Class<? extends Product> productClass;

    public CategoryOffer(Class<? extends Product> productClass, double discount) throws NotInBoundsDiscountException, NotDiscountableException {
        super(discount);
        if (!Product.isProductDiscountable(productClass))
            throw new NotDiscountableException();
        this.productClass = productClass;
    }

    @Override
    public boolean applicable(Customer customer, Product product) {
        return super.applicable(customer, product) && product.getClass().isAssignableFrom(productClass);
    }
}
