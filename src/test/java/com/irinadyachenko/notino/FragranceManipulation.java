package com.irinadyachenko.notino;

import com.irinadyachenko.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FragranceManipulation extends BaseTest {

    @Test(priority=1)
    private void checkRedirectionToUKPage(){
        Assert.assertEquals(homePage.open().clickUKButton().getPageTitle(), "Shop Beauty & Fragrance Online | notino.co.uk");
    }

    @Test(priority=2)
    private void checkingMechanismOfAddingElementToCart(){
        int count = homePage.open().clickUKButton()
                .clickSALESButton()
                .clickFragranceSALESButton()
                .clickFirstElementInTheList()
                .clickToAddElementToCart()
                .clickContinueShoppingButton()
                .getNumberOfElementsInTheCart(0);
        Assert.assertEquals(count, 1);
    }

    @Test(priority=3)
    private void decreasingTheNumberOfElementsInCart(){
        int count = homePage.getNumberOfElementsInTheCart(0);
        Assert.assertEquals(cartPage.open().clickToRemoveElementFromCart().getNumberOfElementsInTheCart(waitCart), count - 1);
    }
}
