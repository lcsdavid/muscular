package fr.davidlegras.serviceMarketing;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;

import java.util.ArrayList;

public abstract class CommercialOffer {
    protected float reduction; /* Pourcentage de réduction */

    protected ArrayList<? extends CustomerState> customerTarget;//les stats de customer qui ont le droit de profiter de ces offres, est égale à null si toutes les classes ont accès a cette offre

    public ArrayList<? extends CustomerState> getCustomerTarget(){
        return this.customerTarget;
    }

    public CommercialOffer(float reduction, ArrayList<? extends CustomerState> customerTarget) throws NotInBoundsReductionException {
        if (reduction > 100 || reduction < 0) {
            throw new NotInBoundsReductionException("Reduction non comprise entre 0 et 100");
        }
        this.reduction = reduction;
        this.customerTarget = customerTarget;
    }

    public float getReduction() {
        return reduction;
    }

    public abstract float getReduction(Customer customer);

    public abstract String toString();

    public boolean customerAccepted(Customer customer){
        if(customerTarget == null)
            return true;
        for(int i = 0; i < customerTarget.size(); i++){
            if(((CustomerState)customerTarget.get(i)).getState().equals(customer.getCustomerState().getState()))
                return true;
        }
        return false;
    }
}
