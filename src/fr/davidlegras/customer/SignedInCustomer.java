package fr.davidlegras.customer;

public abstract class SignedInCustomer implements CustomerState {

    private String identifier;
    private String firstName;
    private String name;

    @Override
    public void signIn(String login, String password) throws AlreadySignedInException {
        throw new AlreadySignedInException();
    }
}
