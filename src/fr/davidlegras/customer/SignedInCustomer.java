package fr.davidlegras.customer;

public abstract class SignedInCustomer implements CustomerState {

    private final String name;

    public SignedInCustomer(String name) {
        super();
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public final void signIn(final Customer context, final String login, final String passwordHash) throws AlreadySignedInException {
        throw new AlreadySignedInException();
    }

    @Override
    public final void signOut(final Customer context) {
        context.customerState(new VisitorFactory().makeCustomerState());
    }
}
