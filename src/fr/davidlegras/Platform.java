package fr.davidlegras;

import fr.davidlegras.customer.Customer;

public interface Platform {






    Server server();

    interface Server {

        void onConnect(String login, String passwordHash);

        void onDisconnect();





    }

    void addToCustomerAListener(Customer customer);

    void removeToCustomerAListener(Customer customer);
}