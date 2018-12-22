package fr.davidlegras;

import fr.davidlegras.customer.AlreadyConnectedException;
import fr.davidlegras.product.Book;
import fr.davidlegras.product.HighTech;
import fr.davidlegras.product.Product;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, AlreadyConnectedException {
        //MarketingService session = MarketingService.getMarketingService();
       //session.shopping();

        Product a = new Book("La Machine Infernal", 5, "", "", new Date());
        Product b = new HighTech("TV", 10);
        System.out.println(a.isClassDiscountable());
        System.out.println(b.isClassDiscountable());

    }
}
