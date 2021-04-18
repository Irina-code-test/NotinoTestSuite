package com.irinadyachenko.pageobject.notino;

import com.irinadyachenko.PropertyManager;
import com.irinadyachenko.pageobject.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(CartPage.class.getSimpleName());
    private final String CART_PAGE_URL = propertyManager.getProperty("cartpage.url");

    @FindBy(xpath = "//*[contains(@id, 'removeFromBasket')]")
    public WebElement RemoveButton;

    public CartPage(WebDriver driver, PropertyManager propertyManager) {
        super(driver, propertyManager);
    }
    public CartPage open(){
        logger.info("Trying to open application cart page");
        driver.navigate().to(CART_PAGE_URL);
        return this;
    }
    public CartPage clickToRemoveElementFromCart(){
        logger.info("Trying to remove the element from the cart");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(RemoveButton)).click();
        return this;
    }
}
