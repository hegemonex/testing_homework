package com.solvd.pages;

import com.solvd.components.HeaderComponent;
import com.solvd.components.ProductCardComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends AbstractPage {

    @FindBy(css = ".inventory_item")
    private List<ProductCardComponent> productCards;

    @FindBy(css = "#header_container")
    private HeaderComponent header;

    @FindBy(css = ".product_sort_container")
    private ExtendedWebElement sortDropdown;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCardComponent> getProductCards() {
        return productCards;
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public void sortBy(String optionText) {
        sortDropdown.select(optionText);
    }

    public ProductCardComponent getProductByName(String name) {
        return getProductCards().stream()
                .filter(card -> card.getProductName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + name));
    }
}