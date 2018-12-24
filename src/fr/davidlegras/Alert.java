package fr.davidlegras;

import java.io.PrintStream;

public class Alert {
    private String message;

    public Alert(String message) {
        super();
        this.message = message;
    }

    public void printAlert() {
        printAlert(System.out);
    }

    public void printAlert(PrintStream s) {
        s.println(message);
    }
}
