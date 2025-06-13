package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasketPage;
import pages.CheckOutPage;
import pages.HomePage;

@Feature("Shopping Feature")
public class shoppingTests extends BaseTest {

    private HomePage homePage;
    private BasketPage basketPage;
    private CheckOutPage checkoutPage;

    @BeforeMethod
    public void setupPages() {
        homePage = new HomePage();
        basketPage = new BasketPage();
        checkoutPage = new CheckOutPage();
    }
    @Test(priority = 1)
    @Description("Verify book exist with its price")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyBookExistsWithPrice() {
        Assert.assertTrue(homePage.isBookDisplayed("Thinking in HTML"), "Book should be visible");
        Assert.assertEquals(homePage.getBookPrice("Thinking in HTML"), "₹400.00");
    }
    @Test(priority = 2)
    @Description("Verify item added to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void validateItemsAddedToCart(){
        homePage.clickAddToBasket("Thinking in HTML");
        homePage.viewBasket();
        Assert.assertTrue(basketPage.isOnCartPage());
        Assert.assertTrue(basketPage.isBookInCart("Thinking in HTML"));
        Assert.assertTrue(basketPage.verifyBookPrice("Thinking in HTML"));
        Assert.assertEquals(basketPage.getBookQuantity("Thinking in HTML"), 1);
    }
    @Test(priority = 3)
    @Description("Verify proceed to checkout")
    @Severity(SeverityLevel.CRITICAL)
    public void validateProceedToCheckout(){
        homePage.clickAddToBasket("Thinking in HTML");
        homePage.viewBasket();
        basketPage.clickProceedToCheckout();
        Assert.assertTrue(checkoutPage.isOnCheckoutPage());
    }

    @Test(priority = 4)
    @Description("Verify billing form not visible without adding books")
    @Severity(SeverityLevel.CRITICAL)
    public void validateBillingFormNotVisibleWithoutAddingBook() {
        homePage.goToCart();
        try {
            Assert.assertFalse(checkoutPage.isFieldDisplayed("billing_first_name"), "Billing form should not be visible without items.");
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("no such element"), "Expected navigation to fail or billing form to be absent.");
        }
    }
    @Test(priority = 5)
    @Description("Verify billing details")
    @Severity(SeverityLevel.CRITICAL)
    public void validateBillingDetails(){
        homePage.clickAddToBasket("Thinking in HTML");
        homePage.viewBasket();
        basketPage.clickProceedToCheckout();
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_first_name"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_last_name"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_company"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_email"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_phone"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_country_field"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_address_1"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_address_2"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_city"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_state_field"));
        Assert.assertTrue(checkoutPage.isFieldDisplayed("billing_postcode"));
    }
    @Test(priority = 6)
    @Description("Verify order details for Thinking In HTML book")
    @Severity(SeverityLevel.CRITICAL)
    public void validateOrderDetailsForThinkingInHTML() {
        homePage.clickAddToBasket("Thinking in HTML");
        homePage.viewBasket();
        basketPage.clickProceedToCheckout();
        Assert.assertTrue(checkoutPage.isOrderTableDisplayed(), "Order table is not visible");
        Assert.assertTrue(checkoutPage.getOrderItemText().contains("Thinking in HTML"), "Incorrect product row in order table");
        Assert.assertEquals(checkoutPage.getSubtotalText(), "₹400.00", "Subtotal is incorrect");
        Assert.assertEquals(checkoutPage.getTaxText(), "₹8.00", "Shipping info is incorrect");
        Assert.assertEquals(checkoutPage.getTotalText(), "₹408.00", "Total is incorrect");
    }
}
