package fr.davidlegras.customer;

import fr.davidlegras.Platform;

public class Staff extends AbstractConnectedCustomer {

    public Staff(String name, String lastName) {
        super(name, lastName);
    }

    @Override
    public int price(final Platform platform, final Customer context) {
        return 0;
    }
}
