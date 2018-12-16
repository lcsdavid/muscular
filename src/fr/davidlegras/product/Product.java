package fr.davidlegras.product;

public class Product {
    private float price;
    private String categorie;
    private String name;

    public Product(float price, String categorie, String name){
        this.price = price;
        this.categorie = categorie;
        this.name = name;
    }

    public String toString(){
        return "Catégorie : " + categorie + ", Produit : " + name + "   Prix : " + price + " euros" ;
    }

    public final float getPrice() {
        return price;
    }
    public final String getCategorie(){ return categorie;}
    public final String getName() {return name;}

    @Override
    public boolean equals(Object object){
        if(object == null)
            return false;
        if(object == this)
            return false;
        if(object.getClass() != this.getClass())
            return false;
        Product p = (Product)object;
        if(p.getName() == this.getName())//les produits sont identifiés par leur noms
            return true;
        return false;
    }
}
