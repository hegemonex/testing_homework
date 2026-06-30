package com.solvd.pages;

import com.solvd.components.HeaderComponent;
import com.solvd.components.ProductCardComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends AbstractPage {

    private List<ExtendedWebElement> productRoots;

    @FindBy(css = ".header_secondary_container")
    private ExtendedWebElement headerRoot;

    @FindBy(css = ".product_sort_container")
    private ExtendedWebElement sortDropdown;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCardComponent> getProductCards() {
        List<WebElement> elements = driver.findElements(By.cssSelector(".inventory_item"));
        List<ProductCardComponent> cards = new ArrayList<>();
        for (WebElement element : elements) {
            ProductCardComponent card = new ProductCardComponent(driver, element);
            card.setElement(element);
            card.setBy(By.cssSelector(".inventory_item"));
            cards.add(card);
        }
        return cards;
    }

    public HeaderComponent getHeader() {
        return new HeaderComponent(driver, headerRoot);
    }

    public void sortBy(String optionValue) {
        sortDropdown.select(optionValue);
    }

    public ProductCardComponent getProductByName(String name) {
        return getProductCards().stream()
                .filter(card -> card.getProductName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + name));
    }
}
