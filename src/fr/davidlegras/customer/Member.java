package fr.davidlegras.customer;

public class Member extends ConnectedCustomer {

    public Member(String name) {
        super(name);
    }

    @Override
    public int price(Customer customer) {
        return 0;
    }
}
