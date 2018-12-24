package fr.davidlegras;

public class Staff extends AbstractConnectedCustomer {

    public Staff(String name, String lastName) {
        super(name, lastName);
    }

    @Override
    public int price(final Platform platform, final Customer context) {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object == this)
            return true;
        if (object.getClass().equals(this.getClass()))
            return true;
        return false;
    }
}
