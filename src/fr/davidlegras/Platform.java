package fr.davidlegras;

import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.CustomerState;
import fr.davidlegras.product.Offer;
import fr.davidlegras.product.Product;

import java.util.Collection;

public interface Platform {


    Collection<Product> products ();
    Collection<Offer<Product, CustomerState>> offers();

    Offer<Product, CustomerState> createOffer();

    Server server();

    interface Server {

        void onConnect(String login, String passwordHash);

        void onDisconnect();





    }

    void addToCustomerAListener(Customer customer);

    void removeToCustomerAListener(Customer customer);
}