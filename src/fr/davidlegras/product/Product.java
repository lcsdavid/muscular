package fr.davidlegras.product;

import java.util.Objects;

/**
 *
 * @author Lucas David
 * @author Théo Legras
 * @see Category
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


    @Override
    public int hashCode() {
        return Objects.hash(name, category, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Product) {
            Product pro = (Product)obj;
            if (!name.equals(pro.name))
                return false;
            if (!category.equals(pro.category))
                return false;
            if (Float.compare(price, pro.price) != 0)
                return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " EUR " + price;
    }

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
