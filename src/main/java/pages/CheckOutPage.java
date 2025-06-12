package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutPage extends BasePage {
    private By billingFirstNameInput = By.id("billing_first_name");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }
    public boolean isOnCheckoutPage() {
        return driver.getCurrentUrl().contains("checkout");
    }

    public boolean isFieldDisplayed(String field) {
        try {
            return isDisplayed(By.id(field));
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isOrderTableDisplayed() {
        try {
            return isDisplayed(By.cssSelector(".shop_table.woocommerce-checkout-review-order-table"));
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String getOrderItemText() {
        return getText(By.cssSelector(".product-name"));
    }

    public String getSubtotalText() {
        return getText(By.cssSelector(".cart-subtotal td"));
    }

    public String getShippingText() {
        return getText(By.cssSelector(".shipping td"));
    }

    public String getTotalText() {
        return getText(By.cssSelector(".order-total td"));
    }
    public boolean isCountryDropdownDisplayed() {
        try {
            WebElement countryDropdown = driver.findElement(By.cssSelector("billing_country"));
            return countryDropdown.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
