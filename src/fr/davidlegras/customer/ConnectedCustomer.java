package fr.davidlegras.customer;

import javafx.application.Platform;

public abstract class ConnectedCustomer implements CustomerState {
    private String name;

    public ConnectedCustomer(String name) {
        super();
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public final void connect(Platform platform, Customer context, String login, String passwordHash) throws AlreadyConnectedException {
        throw new AlreadyConnectedException();
    }

    @Override
    public final void disconnect(Platform unused, Customer context) {
        context.customerState(Visitor.getVisitor());
    }
}
