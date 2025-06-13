package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasketPage extends BasePage {

    private By cartItemNames = By.cssSelector(".cart_item .product-name");
    private By cartItemRows = By.cssSelector(".cart_item");
    private By productPrice = By.cssSelector(".product-price");
    private By productQuantityInput = By.cssSelector(".product-quantity input");
    private By proceedToCheckoutButton = By.cssSelector(".checkout-button");
    public BasketPage() {
        super();
    }
    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains("basket");
    }
    public boolean isBookInCart(String bookName) {
        List<WebElement> items = driver.findElements(cartItemNames);
        return items.stream().anyMatch(item -> item.getText().contains(bookName));
    }
    public boolean verifyBookPrice(String bookName) {
        List<WebElement> rows = driver.findElements(cartItemRows);
        for (WebElement row : rows) {
            if (row.getText().contains(bookName)) {
                String price = row.findElement(productPrice).getText();
                return price != null && !price.isEmpty();
            }
        }
        return false;
    }
    public int getBookQuantity(String bookName) {
        List<WebElement> rows = driver.findElements(cartItemRows);
        for (WebElement row : rows) {
            if (row.getText().contains(bookName)) {
                String qty = row.findElement(productQuantityInput).getAttribute("value");
                return Integer.parseInt(qty);
            }
        }
        return 0;
    }
    public void clickProceedToCheckout() {
        click(proceedToCheckoutButton);
    }
}
