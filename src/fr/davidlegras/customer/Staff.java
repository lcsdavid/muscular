package fr.davidlegras.customer;

public class Staff extends SignedInCustomer {

    Staff(final String name) {
        super(name);
    }

    @Override
    public int price(Customer customer) {
        return 0;
    }

    @Override
    public void signOut() throws NotSignedInException {

    }
}
