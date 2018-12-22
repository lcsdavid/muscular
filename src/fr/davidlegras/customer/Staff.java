package fr.davidlegras.customer;

public class Staff extends SignedInCustomer {

    public Staff(final String name) {
        super(name);
    }

    @Override
    public String getState() {
        return "Staff";
    }

    @Override
    public int price(Customer customer) {
        return 0;
    }
}
