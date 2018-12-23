package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * TODO
 *
 * @author Lucas David
 * @author Théo Legras
 * @see AbstractOffer
 * @see Discountable
 * @see ProductOffer
 * @see FlashOffer
 */
public abstract class AbstractOffer<P extends Product, C extends CustomerState> implements Offer<P, C> {
    /* Pourcentage de réduction (e.g. -0.1 ou -0.5 respectivements -10% et -50%). */
    private double discount;

    protected AbstractOffer(double discount) throws NotInBoundsDiscountException {
        super();
        if (discount < -1 || discount > 0)
            throw new NotInBoundsDiscountException("La réduction " + discount * 100 + "% n'est pas comprise entre -100% et 0%.");
        this.discount = discount;
    }

    public double discount() {
        return discount;
    }

    @Override
    public boolean applicable(Customer customer, Product product) {
        Type parameterType = getClass().getGenericInterfaces()[0];
        parameterType = ((ParameterizedType) parameterType).getActualTypeArguments()[0];
        return customer.getCustomerState().getClass().isAssignableFrom((Class) parameterType);
    }

    @Override
    public String toString() {
        return Double.toString(discount * 100) + '%';
    }
}
