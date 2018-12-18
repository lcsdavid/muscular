package fr.davidlegras.product;

public abstract class Category implements Comparable<Category> {
    private String name;
    private boolean discountable;

    /* Constructeurs */
    public Category(String name) {
        this(name, true);
    }

    public Category(String name, boolean discountable) {
        super();
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
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Category o) {
        return name.compareTo(o.name);
    }
}
