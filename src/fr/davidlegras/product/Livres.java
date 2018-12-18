package fr.davidlegras.product;

public class Livres extends Categorie {
    private String auteur;
    private String dateDeParution;

    public Livres(String auteur, String dateDeParution) {
        super("Livres", false);
        this.auteur = auteur;
        this.dateDeParution = dateDeParution;
    }

    @Override
    public String toString() {
        String res = "";
        res += super.toString();
        res += ". Auteur : " + auteur + ", paru en : " + dateDeParution;
        return res;
    }

}
