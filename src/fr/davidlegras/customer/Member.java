package fr.davidlegras.customer;

public class Member implements SignedInCustomer {

    Member() {
        super();
    }

    @Override
    public int price(Customer customer) {
        return 0;
    }

    @Override
    public void signOut() throws NotSignedInException {

    }
}
