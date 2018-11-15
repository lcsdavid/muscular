package fr.davidlegras.customer;

public interface CustomerState {

    int price(Customer customer);
    void signIn(String login, String password) throws AlreadySignedInException;
    void signOut() throws NotSignedInException;
}
