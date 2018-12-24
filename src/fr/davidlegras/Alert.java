package fr.davidlegras;

import java.io.PrintStream;

public class Alert extends Exception {

    public Alert() {
        super();
    }

    public Alert(String message) {
        super(message);
    }

    public void printAlert() {
        printAlert(System.out);
    }

    public void printAlert(PrintStream s) {
        s.println(getMessage());
    }
}
