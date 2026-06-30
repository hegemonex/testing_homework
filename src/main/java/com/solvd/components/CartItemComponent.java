package com.solvd.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartItemComponent extends AbstractUIObject {

    public CartItemComponent(WebDriver driver, WebElement element) {
        super(driver, element);
    }
    public String getItemName() {
        return getRootElement().findElement(By.cssSelector(".inventory_item_name")).getText();
    }

    public String getPrice() {
        return getRootElement().findElement(By.cssSelector(".inventory_item_price")).getText();
    }

    public int getQuantity() {
        return Integer.parseInt(getRootElement().findElement(By.cssSelector(".cart_quantity")).getText().trim());
    }

    public void removeItem() {
        getRootElement().findElement(By.cssSelector(".cart_button")).click();
    }
}