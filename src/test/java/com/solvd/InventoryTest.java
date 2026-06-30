package com.solvd;

import com.beust.ah.A;
import com.solvd.components.CartItemComponent;
import com.solvd.components.ProductCardComponent;
import com.solvd.pages.CartPage;
import com.solvd.pages.InventoryPage;
import com.solvd.pages.LoginPage;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class InventoryTest extends AbstractTest {


    private static final String BASE_URL = "https://www.saucedemo.com";
    private static final String username = "standard_user";
    private static final String password = "secret_sauce";

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void login(){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open(BASE_URL);
        inventoryPage = loginPage.login(username, password);
    }

    @Test
    public void addSingleItemCardTest(){
        ProductCardComponent firstProduct = inventoryPage.getProductCards().get(0);
        String productName = firstProduct.getName();

        firstProduct.addToCart();

        Assert.assertTrue(firstProduct.isAddedToCart(),
                "Button should say Remove after adding to cart");
        Assert.assertEquals(inventoryPage.getHeader().getCardCount(), 1,
                "Cart badge should show 1");
    }

    @Test
    public void addMultipleProductsAndVerifyCartCountTest() {
        inventoryPage.getProductCards().get(0).addToCart();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        inventoryPage.getProductCards().get(1).addToCart();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        inventoryPage.getProductCards().get(2).addToCart();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        Assert.assertEquals(inventoryPage.getHeader().getCardCount(), 3,
                "Cart badge should show 3 after adding 3 products");
    }

    @Test
    public void cartContainsCorrectProductsTest() {
        String firstName = inventoryPage.getProductCards().get(0).getProductName();
        String secondName = inventoryPage.getProductCards().get(1).getProductName();

        inventoryPage.getProductCards().get(0).addToCart();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        inventoryPage.getProductCards().get(1).addToCart();
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        inventoryPage.getHeader().openCart();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("cart"));

        CartPage cartPage = new CartPage(getDriver());
        List<CartItemComponent> items = cartPage.getCartItems();

        Assert.assertEquals(items.size(), 2, "Cart should contain exactly 2 items");
        Assert.assertEquals(items.get(0).getItemName(), firstName, "First cart item name should match");
        Assert.assertEquals(items.get(1).getItemName(), secondName, "Second cart item name should match");
    }

    @Test
    public void sortProductsByPriceLowToHighTest(){
        inventoryPage.sortBy("Price (low to high)");

        List<ProductCardComponent> cards = inventoryPage.getProductCards();

        for(int i = 0; i < cards.size() - 1; i++){
            double current = parsePrice(cards.get(i).getPrice());
            double next = parsePrice(cards.get(i + 1).getPrice());
            Assert.assertTrue(current <= next,
                    "Products should be sorted low to high but found "
                            + current + " before " + next);

        }
    }

    @Test
    public void removeItemFromCartTest() {
        inventoryPage.getProductCards().get(0).addToCart();
        inventoryPage.getHeader().openCart();
        CartPage cartPage = new CartPage(getDriver());

        cartPage.getCartItems().get(0).removeItem();

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        Assert.assertTrue(cartPage.isEmpty(),
                "Cart should be empty after removing the only item");
        Assert.assertEquals(cartPage.getHeader().getCardCount(), 0,
                "Cart badge should show 0 after removal");
    }

    private double parsePrice(String price) {
        return Double.parseDouble(price.replace("$", ""));
    }

}

