package com.solvd.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartItemComponent extends AbstractUIObject {

    @FindBy(css = ".inventory_item_name")
    private ExtendedWebElement name;

    @FindBy(css = ".inventory_item_price")
    private ExtendedWebElement price;

    @FindBy(css = ".cart_quantity")
    private ExtendedWebElement quantity;

    @FindBy(css = ".cart_button")
    private ExtendedWebElement removeButton;

    public CartItemComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getItemName() {
        return name.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public int getQuantity() {
        return Integer.parseInt(quantity.getText().trim());
    }

    public void removeItem() {
        removeButton.clickByJs();
    }
}