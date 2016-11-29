package fi.teemutt;

import java.util.ArrayList;

/**
 * Created by H8705 on 29.11.2016.
 *
 */

public class Basket {

    private String customer;
    private ArrayList<Item> contents;

    public Basket(String customer, Item... contents) {
        if (customer == null || customer.isEmpty()) {
            this.customer = "Default";
        } else {
            this.customer = customer;
        }
        this.contents = new ArrayList<>();

        if (contents != null) {
            for (Item i : contents) {
                addProduct(i);
            }
        }
    }

    public String getCustomer() {
        return customer;
    }

    public double getPrice() {
        double price = 0;
        for (Item p : contents) {
            price += p.getPrice();
        }
        return price;
    }

    public double getDiscountPrice(double discount) {
        double price = 0;
        for (Item p : contents) {
            price += p.getPrice();
        }
        price -= price * discount;
        return price;
    }

    public ArrayList<String> getContents() {
        ArrayList<String> items = new ArrayList<>();
        for (Item i : contents) {
            items.add(i.getName());
        }
        return items;
    }

    public boolean addProduct(Item product) {
        if (product == null) {
            return false;
        } else if (product.getPrice() < 0) {
            return false;
        } else if (product.getName() == null || product.getName().isEmpty()) {
            return false;
        }
        return contents.add(product);
    }

    public boolean removeProduct(String product) {
        if (product == null) {
            return false;
        }
        for (Item p : contents) {
            if (p.getName().equals(product)) {
                return contents.remove(p);
            }
        }
        return false;
    }

    public void clearBasket() {
        contents = new ArrayList<>();
    }
}
