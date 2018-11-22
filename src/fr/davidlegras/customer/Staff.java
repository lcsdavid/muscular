package fr.davidlegras.customer;

public class Staff implements SignedInCustomer {

    Staff() {
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
