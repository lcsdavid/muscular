package fr.davidlegras.product;


/**
 *
 *
 * @author Lucas David
 * @author Théo Legras
 */
public class Category implements Comparable<Category> {
    private String name;

    public Category(String name) {
        super();
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Category) {
            Category cat = (Category)obj;
            return name.equals(cat.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Category o) {
        return name.compareTo(o.name);
    }
}
