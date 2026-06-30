package com.solvd.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCardComponent extends AbstractUIObject {

    @FindBy(css = ".inventory_item_name")
    private ExtendedWebElement name;

    @FindBy(css = ".inventory_item_price")
    private ExtendedWebElement price;

    @FindBy(css = ".btn_inventory")
    private ExtendedWebElement addToCartButton;

    public ProductCardComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductName() {
        return name.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public void addToCart() {
        addToCartButton.clickByJs();
    }

    public boolean isAddedToCart() {
        return addToCartButton.getText().equalsIgnoreCase("Remove");
    }
}