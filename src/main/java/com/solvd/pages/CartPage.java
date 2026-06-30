package com.solvd.pages;

import com.solvd.components.CartItemComponent;
import com.solvd.components.HeaderComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(css = ".header_secondary_container")
    private ExtendedWebElement headerRoot;

    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private ExtendedWebElement continueShoppingButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItemComponent> getCartItems() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart_item")));
        List<WebElement> elements = driver.findElements(By.cssSelector(".cart_item"));
        List<CartItemComponent> items = new ArrayList<>();
        for (WebElement element : elements) {
            CartItemComponent item = new CartItemComponent(driver, element);
            item.setElement(element);
            item.setBy(By.cssSelector(".cart_item"));
            items.add(item);
        }
        return items;
    }

    public HeaderComponent getHeader() {
        return new HeaderComponent(driver, headerRoot);
    }

    public boolean isEmpty() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cart_item")));
            return true;
        } catch (Exception e) {
            return driver.findElements(By.cssSelector(".cart_item")).isEmpty();
        }
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