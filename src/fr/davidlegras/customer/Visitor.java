package fr.davidlegras.customer;


public class Visitor implements CustomerState {
    private static Visitor uniqueInstance;

    private Visitor() {
        super();
    }

    public void handle(String login, String password) {

    }
    public void handle() {

    }

}
