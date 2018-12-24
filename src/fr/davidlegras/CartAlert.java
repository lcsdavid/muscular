package fr.davidlegras;

public class CartAlert extends Alert {

    public CartAlert(String message) {
        super(message);
    }

    public CartAlert(CartEvent e) {
        super("Le panier de " + e.getSource().toString() + " à été modifié.");
    }
}
