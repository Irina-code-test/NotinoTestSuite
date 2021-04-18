package com.irinadyachenko.pageobject;

import com.irinadyachenko.pageobject.notino.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.devtools.v89.page.Page;
import com.irinadyachenko.PropertyManager;

import java.time.Duration;

public abstract class BasePage extends Page{
    @FindBy(xpath = "(//a[@href='/order-multistep.asp']/child::div)[1]")
    public WebElement CartXpath;

    protected WebDriver driver;
    protected PropertyManager propertyManager;

    public BasePage(WebDriver driver, PropertyManager propertyManager){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.propertyManager = propertyManager;
    }

    protected void checkPageTitle(String title){
        Assert.assertEquals(driver.getTitle(), title);
    }
    public String getPageTitle(){
        return driver.getTitle();
    }
    protected void pressKey(Keys key){
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }

    protected void enterTextIntoField(By xpath, String text){
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(xpath),text).perform();
    }

    protected void openWebPage(String url){
        driver.navigate().to(url);
    }

    protected void clickOnElement(By xpath){
        clickOnElement(xpath, 0);
    }
    protected void clickOnElement(By xpath, int position){
        Actions actions = new Actions(driver);
        actions.click(driver.findElements(xpath).get(position)).perform();
    }
    protected String getTextFromField(WebElement element, String attributeName){
        return element.getAttribute(attributeName);
    }
    public int getNumberOfElementsInTheCart(int waitSeconds){
        final String attributeName = "data-count";
        String cartCount = getTextFromField(CartXpath,attributeName);
        if(waitSeconds != 0) {
            new WebDriverWait(driver, Duration.ofSeconds(waitSeconds), Duration.ofSeconds(1)).until(ExpectedConditions.not(ExpectedConditions.attributeToBe(CartXpath, attributeName, cartCount)));
            cartCount = getTextFromField(CartXpath, attributeName);
        }
        return Integer.parseInt(cartCount);
    }

}
