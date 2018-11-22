package fr.davidlegras.customer;

public interface SignedInCustomer extends CustomerState {

    @Override
    public default void signIn(String login, String password) throws AlreadySignedInException {
        throw new AlreadySignedInException();
    }
}
