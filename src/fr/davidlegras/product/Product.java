package fr.davidlegras.product;

public class Product {
    private float price;
    private String categorie;
    private String name;

    public String toString(){
        return "Catégorie : " + categorie + ", Produit : " + name + "   Prix : " + price + " euros" ;
    }

    public final float getPrice() {
        return price;
    }
    public final String getCategorie(){ return categorie;}
    public final String getName() {return name;}
}
