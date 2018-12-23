package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author Lucas David
 * @author Théo Legras
 * @see CommercialOffer
 * @see Discountable
 * @see ProductOffer
 * @see FlashOffer
 */
public abstract class CommercialOffer<T extends Discountable> implements Offer<T> {
    /* Pourcentage de réduction (e.g. -0.1 ou -0.5 respectivements -10% et -50%). */
    private double discount;

    /* la cible de la réduction */
    private ArrayList<? extends CustomerState> targetCustomer;


    CommercialOffer(double discount, ArrayList<? extends CustomerState> target) throws NotInBoundsDiscountException {
        super();
        if (discount < -1 || discount > 0)
            throw new NotInBoundsDiscountException("La réduction " + discount * 100 + "% n'est pas comprise entre -100% et 0%.");
        this.discount = discount;
        this.targetCustomer = target;
    }

    protected double discount() {
        return discount;
    }

    public ArrayList<? extends CustomerState> getTargetCustomer(){
        ArrayList<? extends CustomerState> res = (ArrayList<? extends CustomerState>) targetCustomer.clone();
        return res;
    }

    public boolean isTargeted(Customer customer){
        /* cas où tout le monde est accepté */
        if(targetCustomer == null)
            return true;
        for (CustomerState entry: targetCustomer) {
            if(entry.equals(customer.getCustomerState()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Double.toString(discount * 100) + '%';
    }
}
