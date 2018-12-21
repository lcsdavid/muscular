package fr.davidlegras.product;

import java.util.HashMap;
import java.util.Map;

public class Cart<T> {
    private Map<T, Integer> cart = new HashMap<>();

    Cart() {
        super();
    }

    /* Query methods */

    int count() {
        int count = 0;
        for (int c: cart.values())
            count += c;
        return count;
    }

    boolean contains(T product) {
        return cart.containsKey(product);
    }

    boolean contains(Cart<? extends T> other) {
        for (Map.Entry<? extends T, Integer> entry: other.cart.entrySet())
            if (!contains(entry.getKey()) || !(cart.get(entry.getKey()) < entry.getValue()))
                return false;
        return true;
    }

    /* Modification methods */

    int add(T product) {
        return add(product, 1);
    }

    int add(T product, int count) {
        if (count < 1)
            throw new IllegalArgumentException();
        if (cart.containsKey(product)) {
            count += cart.get(product);
            cart.replace(product, count);
        } else
            cart.put(product, count);
        return count;
    }

    int addAll(Cart<? extends T> other) {
        int productsAdded = 0;
        for (Map.Entry<? extends T, Integer> entry: other.cart.entrySet())
            productsAdded += add(entry.getKey(), entry.getValue());
        return productsAdded;
    }

    int remove(T product) {
        return remove(product, 1);
    }

    int remove(T product, int count) {
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

    int removeAll(Cart<? extends T> other) {
        int productsRemoved = 0;
        for(Map.Entry<? extends T, Integer> entry: other.cart.entrySet())
            productsRemoved += remove(entry.getKey(), entry.getValue());
        return productsRemoved;
    }
}
