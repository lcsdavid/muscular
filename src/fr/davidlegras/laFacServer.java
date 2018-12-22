package fr.davidlegras;

import java.util.ArrayList;

public class laFacServer implements Platform.Server {

    /* login | passwordHash | type | fidelityPoints */
    private ArrayList<String[]> usersTable = new ArrayList<>();
    /* name | categoryName | price | points */
    private ArrayList<String[]> productsTable = new ArrayList<>();
    /* name | type | discount */
    private ArrayList<String[]> offerTable = new ArrayList<>();

    @Override
    public void onConnect(String login, String passwordHash) {

    }

    @Override
    public void onDisconnect() {
        /* Unusued procedure. */
    }
}
