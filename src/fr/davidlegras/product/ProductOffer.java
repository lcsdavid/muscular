package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * TODO abstract
 *
 * @author Lucas David
 * @author Théo Legras
 * @see CommercialOffer
 * @see Discountable
 * @see Product
 */
public class ProductOffer<T extends Product & Discountable > extends CommercialOffer<T> {
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
        Cart<Product> nouv = (Cart<Product>)cart;// a cette endroit du code, nos contraintes sur les types nous garantisses que ce cast est legal
        for (int i = 0; i<targetProduct.size(); i++) {
            if(nouv.contains(targetProduct.get(i)))
                return true;
        }
        return false;
    }

    @Override
    public double applyOffer(double price) {
        return price * (1 + discount());
    }


    /* fonction qui nous permet d'avoir la réduction pour un client */
    public double getReduction(Customer customer){
        double res =0.0;

        for (Map.Entry<Product, Integer> entry : (Set<Map.Entry<Product, Integer>>) customer.cart().entrySet()) {
            if(applicable(entry.getKey()))
                res += applyOffer(entry.getKey().price()) * entry.getValue();
        }

        return res;
    }
}
