package fr.davidlegras.customer;

public interface NotSignedInCustomer extends CustomerState {

    @Override
    default void signOut() throws NotSignedInException {
        throw new NotSignedInException();
    }
}
