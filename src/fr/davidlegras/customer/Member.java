package fr.davidlegras.customer;

import fr.davidlegras.Platform;

public class Member implements ConnectedCustomer {
    private String firstName;
    private FidelityCard fidelityCard = new FidelityCard();

    public Member(String firstName) {
        super();
        this.firstName = firstName;
    }

    @Override
    public int price(final Platform platform, final Customer context) {
        return 0;
    }
}
