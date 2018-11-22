package fr.davidlegras.customer;


public class Visitor implements NotSignedInCustomer {
    private Visitor() {
        super();
    }

    @Override
    public int price(Customer customer) {
        return 0;
    }

    @Override
    public void signIn(String login, String password) throws AlreadySignedInException {

    }
}
