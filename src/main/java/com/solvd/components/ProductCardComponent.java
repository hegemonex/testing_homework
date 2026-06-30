package com.solvd.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.*;

public class ProductCardComponent extends AbstractUIObject {

    public ProductCardComponent(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public String getProductName() {
        return getRootElement().findElement(By.cssSelector(".inventory_item_name")).getText();
    }

    public String getPrice() {
        return getRootElement().findElement(By.cssSelector(".inventory_item_price")).getText();
    }

    public void addToCart() {
        var btn = getRootElement().findElement(By.cssSelector(".btn_inventory"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public boolean isAddedToCart() {
        return getRootElement().findElement(By.cssSelector(".btn_inventory")).getText().equalsIgnoreCase("Remove");
    }
}