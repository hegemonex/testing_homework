package com.solvd.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractUIObject {

    @FindBy(css = ".bm-burger-button")
    private ExtendedWebElement burgerMenu;

    @FindBy(css = ".shopping_cart_link")
    private ExtendedWebElement cartLink;

    @FindBy(css = ".shopping_cart_badge")
    private ExtendedWebElement cartBadge;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void openMenu() {
        burgerMenu.click();
    }

    public void openCart() {
        cartLink.clickByJs();
    }

    public int getCardCount() {
        if (!cartBadge.isElementPresent(3)) {
            return 0;
        }
        return Integer.parseInt(cartBadge.getText().trim());
    }
}