package fr.davidlegras;

public class StateAlert extends Alert {

    public StateAlert(String message) {
        super(message);
    }

    public StateAlert(CustomerStateEvent e) {
        super("Le client " + e.getSource() + " est passé de " + e.getOldState().getClass().getSimpleName() + " à "
                + e.getOldState().getClass().getSimpleName());
    }
}
