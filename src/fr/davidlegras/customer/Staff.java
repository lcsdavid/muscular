package fr.davidlegras.customer;

public class Staff extends ConnectedCustomer {

    public Staff(final String name) {
        super(name);
    }

    @Override
    public int price(Customer customer) {
        return 0;
    }
}
