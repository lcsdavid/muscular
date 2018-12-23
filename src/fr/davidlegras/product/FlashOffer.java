package fr.davidlegras.product;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;
import fr.davidlegras.serviceMarketing.NotInBoundsDiscountException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 *
 * @author Lucas David
 * @author Théo Legras
 * @see CommercialOffer
 * @see Discountable
 * @see Product
 */
public class FlashOffer<T extends Product &  Discountable> extends CommercialOffer<T> {
    /**
     * Tableau dont les clées représentent les produits concernés et leurs valeurs respectives représente la quantité
     * demandé pour appliquer l'offre.
     */
    private Cart<T> cart = new Cart<>();

    /**
     *
     * @param discount
     * @param targets
     * @throws NotInBoundsDiscountException
     */
    FlashOffer(double discount, Cart<? extends T> targetCart, ArrayList<? extends CustomerState> targets) throws NotInBoundsDiscountException {
        super(discount, targets);
        cart.addAll(targetCart);
    }

    @Override
    public boolean applicable(Cart<? extends T> cart) {
        Cart<Product> nouv = (Cart<Product>)cart;// a cette endroit du code, nos contraintes sur les types nous garantisses que ce cast est legal
        return nouv.contains(this.cart);
    }

    @Override
    public double applyOffer(double price) {

        return price * (1 + discount());
    }


    public double getReduction(Customer customer){
        double res =0.0;
        if(!isTargeted(customer))
            return res;

        if(applicable(customer.cart())){
            //Dans ce cas la client a tout ce qu'il faut pour que la réduction s'applique
            for (Map.Entry<Product, Integer> entry : (Set<Map.Entry<Product, Integer>>) customer.cart().entrySet()) {
                if(((Cart<Product>)this.cart).contains(entry.getKey()))//si l'entrée sur laquelle on est correspond a un produit sur lequel l'offre s'applique
                    res += applyOffer(entry.getKey().price()) * entry.getValue();
            }
        }
        return res;
    }
}
