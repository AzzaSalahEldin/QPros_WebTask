package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage {

    public CheckOutPage() {
        super();
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
        return getText(By.cssSelector("td.product-name"));
    }

    public String getSubtotalText() {
        return getText(By.cssSelector(".cart-subtotal td"));
    }

    public String getTaxText() {
        return getText(By.cssSelector(".tax-rate.tax-rate-in-tax-1 td"));
    }

    public String getTotalText() {
        return getText(By.cssSelector(".order-total td"));
    }
}
