package com.solvd.pages;

import com.solvd.components.CartItemComponent;
import com.solvd.components.HeaderComponent;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(css = ".cart_item")
    private List<CartItemComponent> cartItems;

    @FindBy(css = "#header_container")
    private HeaderComponent header;

    @FindBy(id = "checkout")
    private com.zebrunner.carina.webdriver.decorator.ExtendedWebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private com.zebrunner.carina.webdriver.decorator.ExtendedWebElement continueShoppingButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItemComponent> getCartItems() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("cart.html"));
        return cartItems;
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public boolean isEmpty() {
        return driver.findElements(By.cssSelector(".cart_item")).isEmpty();
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }

    public void checkout() {
        checkoutButton.click();
    }

    public CartItemComponent getItemByName(String name) {
        return getCartItems().stream()
                .filter(item -> item.getItemName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found: " + name));
    }
}