package com.irinadyachenko.pageobject.notino;

import com.irinadyachenko.PropertyManager;
import com.irinadyachenko.pageobject.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class.getSimpleName());
    private final String HOME_PAGE_URL = propertyManager.getProperty("homepage.url");

    @FindBy(xpath = "//span[@class=\"flag__name\" and contains(text(),\"Great Britain\")]")
    public WebElement UKButton;

    @FindBy(xpath = "//*[@class=\"category-title\" and contains(text(),\"Fragrance SALES\")]")
    public WebElement FragranceSALESButton;

    @FindBy(xpath = "//*[@data-cypress='mainMenu-SALES']")
    public WebElement SALESButton;

    @FindBy(xpath = "//*[@id=\"productsList\"]/li[1]")
    public WebElement FirstElementInTheList;

    @FindBy(xpath = "//*[@id=\"pd-buy-button\"]")
    public WebElement AddToCartButton;

    @FindBy(xpath = "//*[@id=\"upsellingContinueWithShopping\"]")
    public WebElement ContinueShoppingButton;

    public HomePage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }

    public HomePage open(){
        logger.info("Trying to open application home page");
        driver.navigate().to(HOME_PAGE_URL);
        return this;
    }

    public HomePage clickUKButton(){
        logger.info("Trying to click on UK button on homepage");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
            .until(ExpectedConditions.visibilityOf(UKButton)).click();
        return this;
    }

    public HomePage clickSALESButton(){
        logger.info("Trying to click on UK button on homepage");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(SALESButton)).click();
        return this;
    }

    public HomePage clickFragranceSALESButton(){
        logger.info("Trying to click on Fragrance SALES button on UK homepage");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(FragranceSALESButton)).click();
        return this;
    }
    public HomePage clickFirstElementInTheList(){
        logger.info("Trying to click on the first element in the list of fragrances");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(FirstElementInTheList)).click();
        return this;
    }
    public HomePage pressEscapeButton(){
        logger.info("Trying to press the escape button");
        pressKey(Keys.ESCAPE);
        return this;
    }

    public HomePage clickToAddElementToCart(){
        logger.info("Trying to add the first element to the cart");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(AddToCartButton)).click();
        return this;
    }

    public HomePage clickContinueShoppingButton(){
        logger.info("Trying to continue shopping");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(ContinueShoppingButton)).click();
        return this;
    }
}
