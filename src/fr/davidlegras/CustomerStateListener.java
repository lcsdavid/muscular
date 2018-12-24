package fr.davidlegras.customer;

public interface CustomerStateListener extends CustomerListener {

    void stateChanged(CustomerStateEvent e);
}
