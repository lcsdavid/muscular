package fr.davidlegras.customer;

import fr.davidlegras.Platform;

public interface CustomerState {

    default int price(final Platform platform, final Customer context){
        int res = 0;



        return res;
    }

    void connect(final Platform platform, final Customer context, String login, String passwordHash) throws AlreadyConnectedException, WrongCredentials;

    void disconnect(final Platform platform, final Customer context) throws NotConnectedException;
}
