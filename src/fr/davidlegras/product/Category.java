package fr.davidlegras.product;

public abstract class Category {
    private String name;
    private boolean discountable;


    /* initializer */
    public Category(String name) {
        this.name = name;
        discountable = true;
    }

    public Category(String name, boolean discountable) {
        this.name = name;
        this.discountable = discountable;
    }

    /* Accesseurs */
    public String getName() {
        return name;
    }

    public boolean isDiscountable() {
        return discountable;
    }


    /* Affichage */
    public String toString() {
        return name;
    }
}
