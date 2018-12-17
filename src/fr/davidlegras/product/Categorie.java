package fr.davidlegras.product;

public abstract class Categorie {
    private String name;
    private boolean promouvable;


    /* initializer */
    public Categorie(String name){
        this.name = name;
        promouvable = true;
    }

    public Categorie(String name, boolean promouvable){
        this.name = name;
        this.promouvable = promouvable;
    }

    /* getters */
    public String getName(){
        return name;
    }

    public boolean isPromouvable(){
        return promouvable;
    }

    public String toString(){
        return "Categorie  : " + name;
    }
}
