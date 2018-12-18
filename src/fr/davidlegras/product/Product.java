package fr.davidlegras.product;

public class Product {
    private String name;
    private Category category;
    private float price;

    public Product(float price, Category category, String name) {
        this.price = price;
        this.category = category;
        this.name = name;
    }

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

    public String toString() {
        return "Catégorie : " + category + ", Produit : " + name + "   Prix : " + price + " euros";
    }
}
