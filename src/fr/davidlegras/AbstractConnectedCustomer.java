package fr.davidlegras;

public abstract class AbstractConnectedCustomer implements ConnectedCustomer {
    private String name, lastName;

    protected AbstractConnectedCustomer(String name, String lastName) {
        super();
        this.name = name;
        this.lastName = lastName;
    }

    public String name() {
        return name;
    }

    public String lastName() {
        return lastName;
    }
}
