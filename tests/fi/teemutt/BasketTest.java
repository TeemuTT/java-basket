package fi.teemutt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by H8705 on 29.11.2016.
 *
 */

public class BasketTest {

    private Basket basket;

    @Before
    public void setUp() throws Exception {
        basket = new Basket(
                "Teemu",
                new Item("Leipä", 2.5),
                new Item("Kahvi", 4.8),
                new Item("Kananmunia", 1.2));
    }

    @After
    public void tearDown() throws Exception {
        basket = null;
    }

    @Test
    public void addProduct() throws Exception {
        double priceBefore = basket.getPrice();
        basket.addProduct(new Item("Sandels", 1.25));
        Assert.assertTrue("contents should contain 'Sandels'", basket.getContents().contains("Sandels"));
        Assert.assertTrue("price should be higher than before adding", basket.getPrice() > priceBefore);
    }

    @Test
    public void removeProduct() throws Exception {
        double priceBefore = basket.getPrice();
        basket.removeProduct("Kahvi");
        Assert.assertTrue("contents should not contain 'Kahvi'", !basket.getContents().contains("Kahvi"));
        Assert.assertTrue("Price should be lower than before adding", basket.getPrice() < priceBefore);
    }

    @Test
    public void priceIsLowerAfterRemove() throws Exception {
        double priceBefore = basket.getPrice();
        basket.removeProduct("Kahvi");
        Assert.assertTrue("Price should have decreased", basket.getPrice() < priceBefore);
    }

    @Test
    public void priceIsHigherAfterAdd() throws Exception {
        double priceBefore = basket.getPrice();
        basket.addProduct(new Item("Esine", 5.0));
        Assert.assertTrue("price should have increased", basket.getPrice() > priceBefore);
    }

    @Test
    public void getDiscount() throws Exception {
        double price = basket.getPrice();
        double halfPrice = basket.getDiscountPrice(.5);
        double quarterPrice = basket.getDiscountPrice(.25);
        Assert.assertTrue("price should be 1/2", halfPrice == price / 2);
        Assert.assertTrue("price should be 3/4", quarterPrice == price / 4 * 3);
    }

    @Test
    public void clearBasket() throws Exception {
        basket.clearBasket();
        Assert.assertTrue("contents is empty", basket.getContents().size() == 0);
        Assert.assertTrue("price is zero", basket.getPrice() == 0);
    }

    @Test
    public void customerIsNotNullAfterClear() throws Exception {
        basket.clearBasket();
        Assert.assertNotNull("customer should not be null", basket.getCustomer());
    }

    @Test
    public void customerIsNotEmptyAfterClear() throws Exception {
        basket.clearBasket();
        Assert.assertTrue("customer should not be empty", !basket.getCustomer().isEmpty());
    }

    @Test
    public void returnFalseIfAddedProductIsNull() throws Exception {
        Assert.assertFalse("should return false", basket.addProduct(null));
    }

    @Test
    public void returnFalseIfRemovedProductIsNull() throws Exception {
        Assert.assertFalse("should return false", basket.removeProduct(null));
    }

    @Test
    public void returnFalseIfPriceIsNegative() throws Exception {
        Assert.assertFalse("should return false", basket.addProduct(new Item("Ilmaista rahaa", -1)));
    }

    @Test
    public void returnFalseIfNameIsNull() throws Exception {
        Assert.assertFalse("should return false", basket.addProduct(new Item(null, 1)));
    }

    @Test
    public void returnFalseIfNameIsEmpty() throws Exception {
        Assert.assertFalse("should return false", basket.addProduct(new Item("", -1)));
    }

    @Test
    public void createBasketWithNullContents() throws Exception {
        Basket newBasket = new Basket("Teemu", null);
        Assert.assertNotNull("Basket should have been created", newBasket);
        Assert.assertEquals("customer should be 'Teemu'", "Teemu", newBasket.getCustomer());
    }

    @Test
    public void createBasketWithNullCustomer() throws Exception {
        Basket newBasket = new Basket(null, new Item("Esine", 1));
        Assert.assertEquals("customer should be 'Default'", "Default", newBasket.getCustomer());
    }
}