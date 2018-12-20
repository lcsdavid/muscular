package fr.davidlegras.customer;

import javafx.application.Platform;

public abstract class SignedInCustomer implements CustomerState {
    private String name;

    public SignedInCustomer(String name) {
        super();
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public final void signIn(Platform platform, Customer context, String login, String passwordHash) throws AlreadySignedInException {
        throw new AlreadySignedInException();
    }

    @Override
    public final void signOut(Platform unused, Customer context) {
        context.customerState(Visitor.getVisitor());
    }
}
