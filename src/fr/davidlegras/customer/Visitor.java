package fr.davidlegras.customer;


public class Visitor extends NotSignedInCustomer {

    public Visitor() {
        super();
    }

    @Override
    public int price(Customer customer) {
        return 0;
    }

    @Override
    public void signIn(final Customer context, final String login, final String passwordHash) throws AlreadySignedInException {

    }
}
