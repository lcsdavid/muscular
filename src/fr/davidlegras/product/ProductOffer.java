package fr.davidlegras.product;

import fr.davidlegras.customer.CustomerState;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

import java.util.ArrayList;

/**
 * TODO abstract
 *
 * @author Lucas David
 * @author Théo Legras
 * @see CommercialOffer
 * @see Discountable
 * @see Product
 */
public class ProductOffer<T extends AbstractProduct & Discountable > extends CommercialOffer<T> {
    private ArrayList<T> targetProduct;


    ProductOffer(float discount, ArrayList<CustomerState> targets, ArrayList<T> targetProduct) throws NotInBoundsDiscountException {
        super(discount, targets);
        this.targetProduct = targetProduct;
    }


    public boolean applicable(Product product){
        return targetProduct.contains(product);
    }

    @Override
    public boolean applicable(Cart<? extends T> cart) {
        boolean res = false;
        for (int i = 0; i<targetProduct.size(); i++) {
            if(cart.contains((AbstractProduct)targetProduct.get(i)))
                return true;
        }
        return res;
    }

    @Override
    public double applyOffer(double price) {
        return price * (1 + discount());
    }
}
