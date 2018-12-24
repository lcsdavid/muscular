package fr.davidlegras;

public interface CustomerStateListener extends CustomerListener {

    void stateChanged(CustomerStateEvent e);
}
