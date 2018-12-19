package fr.davidlegras.product;

/**
 * @see fr.davidlegras.serviceMarketing.CategoryOffer
 * @see fr.davidlegras.serviceMarketing.ProductOffer
 * @see fr.davidlegras.serviceMarketing.FlashOffer
 */
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
    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    public final float price() {
        return price;
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
