package fr.davidlegras.customer;

import fr.davidlegras.customer.AlreadySignedInException;
import fr.davidlegras.customer.Customer;
import fr.davidlegras.customer.NotSignedInException;

public interface CustomerState {

    String name();
    int price(final Customer context);
    void signIn(final Customer context, final String login, final String passwordHash) throws AlreadySignedInException;
    void signOut(final Customer context) throws NotSignedInException;
}
