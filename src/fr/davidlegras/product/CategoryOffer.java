package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CategoryOffer<P extends Product, C extends CustomerState> extends AbstractOffer<P, C> {

    public CategoryOffer(double discount) throws NotInBoundsDiscountException {
        super(discount);
    }

    @Override
    public boolean applicable(Customer customer, Product product) {
        Type parameterType = getClass().getGenericInterfaces()[0];
        parameterType = ((ParameterizedType) parameterType).getActualTypeArguments()[1]; /* Get P type parameter. */
        return super.applicable(customer, product) && product.getClass().isAssignableFrom((Class<?>) parameterType);
    }
}
