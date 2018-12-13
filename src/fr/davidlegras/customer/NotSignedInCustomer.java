package fr.davidlegras.customer;

public abstract class NotSignedInCustomer implements CustomerState {

    @Override
    public String name() {
        return "Guest";
    }

    @Override
    public final void signOut(final Customer context) throws NotSignedInException {
        throw new NotSignedInException();
    }
}
