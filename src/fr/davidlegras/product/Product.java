package fr.davidlegras.product;

public class Product implements Comparable<Product> {
    private String name;
    private Category category;
    private float price;

    /* Constructeurs */
    public Product(float price, Category category, String name) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /* Acesseurs */
    public final String getName() {
        return name;
    }

    public final String getCategory() {
        return category.getName();
    }

    public final float getPrice() {
        return price;
    }

    public final boolean isDiscountable() {
        return category.isDiscountable();
    }

    /* Object Override */
    @Override
    public String toString() {
        return "[" + category.toString() + "]\t" + name + "\nPrix : " + price + "€.";
    }

    /* Comparable Override */
    @Override
    public int compareTo(Product o) {
        int compare = name.compareTo(o.name);
        if (compare != 0)
            return compare;
        compare = category.compareTo(o.category);
        if (compare != 0)
            return compare;
        return Float.compare(price, o.price);
    }
}
