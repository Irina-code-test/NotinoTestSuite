package com.irinadyachenko;

import com.irinadyachenko.drivers.DriverManager;
import com.irinadyachenko.pageobject.notino.CartPage;
import com.irinadyachenko.pageobject.notino.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.Set;

public abstract class BaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static final Logger assertLogger = LogManager.getLogger("Assert");

    protected DriverManager driverManager;
    protected PropertyManager propertyManager;

    protected HomePage homePage;
    protected CartPage cartPage;
    protected int waitCart = 0;

    @Parameters({"browserName","testDataFileName", "waitCart"})
    @BeforeClass
    public void setup(@Optional("Chrome") String browserName
                , @Optional("notino.properties") String testDataFileName
                , @Optional("0") String waitCart){
        driverManager = new DriverManager();
        driver.set(driverManager.getDriver(browserName, "Grid"));
        ITestContext context = Reporter.getCurrentTestResult().getTestContext();
        context.setAttribute("WebDriver", driver.get());
        this.waitCart = Integer.parseInt(waitCart);

        propertyManager = new PropertyManager(testDataFileName);
        homePage = new HomePage(driver.get(), propertyManager);
        cartPage = new CartPage(driver.get(), propertyManager);
    }

    @AfterClass
    public void cleanUp(){
        driver.get().quit();
        driver.remove();
    }
}
