package com.solvd.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends AbstractUIObject {

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void openMenu() {
        driver.findElement(By.cssSelector(".bm-burger-button")).click();
    }

    public void openCart() {
        var cartLink = driver.findElement(By.cssSelector(".shopping_cart_link"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartLink);
    }
    public int getCardCount() {
        var badges = driver.findElements(By.cssSelector(".shopping_cart_badge"));
        if (badges.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badges.get(0).getText().trim());
    }
}