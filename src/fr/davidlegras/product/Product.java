package fr.davidlegras.product;

public abstract class Product {
    private int price;
    private String categorie;
    private String name;

    public final int getPrice() {
        return price;
    }
    public final String getCategorie(){ return categorie;}
    public final String getName() {return name;}
}
