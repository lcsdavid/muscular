package fr.davidlegras.product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart implements Iterable<Map.Entry<Product, Integer>> {
    private Map<Product, Integer> cart = new HashMap<>();

    public Cart() {
        super();
    }

    /* Query methods */

    public int count() {
        int count = 0;
        for (int c: cart.values())
            count += c;
        return count;
    }

    public boolean contains(Product product) {
        return cart.containsKey(product);
    }

    public boolean contains(Cart other) {
        for (Map.Entry<Product, Integer> entry: other.cart.entrySet())
            if (!contains(entry.getKey()) || !(cart.get(entry.getKey()) < entry.getValue()))
                return false;
        return true;
    }

    /* Modification methods */

    public int add(Product product) {
        return add(product, 1);
    }

    public int add(Product product, int count) {
        if (count < 1)
            throw new IllegalArgumentException();
        if (cart.containsKey(product)) {
            count += cart.get(product);
            cart.replace(product, count);
        } else
            cart.put(product, count);
        return count;
    }

    public int addAll(Cart other) {
        int productsAdded = 0;
        for (Map.Entry<Product, Integer> entry: other.cart.entrySet())
            productsAdded += add(entry.getKey(), entry.getValue());
        return productsAdded;
    }

    public int remove(Product product) {
        return remove(product, 1);
    }

    public int remove(Product product, int count) {
        if (count < 1)
            throw new IllegalArgumentException();
        if (cart.containsKey(product)) {
            count = cart.get(product) - count;
            if (count < 1) {
                cart.remove(product);
                count = 0;
            } else
                cart.replace(product, count);
            return count;
        }
        return 0;
    }

    public int removeAll(Cart other) {
        int productsRemoved = 0;
        for(Map.Entry<Product, Integer> entry: other.cart.entrySet())
            productsRemoved += remove(entry.getKey(), entry.getValue());
        return productsRemoved;
    }

    @Override
    public Iterator<Map.Entry<Product, Integer>> iterator() {
        return cart.entrySet().iterator();
    }
}
