package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By viewBasket =  By.xpath("//a[contains(@href, 'basket') and @title='View your shopping cart']");
    private By cartLink =  By.xpath("//a[contains(@href, 'shop') and @title='Start shopping']");

    private By bookTitle(String name) {
        return By.xpath("//h3[text()='" + name + "']");
    }

    private By bookPrice(String name) {
        return By.xpath("//h3[text()='" + name + "']/ancestor::li//ins//span[@class='woocommerce-Price-amount amount']");
    }

    private By addToBasket(String name) {
        return By.xpath("//h3[text()='" + name + "']/ancestor::li//a[text()='Add to basket']");
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isBookDisplayed(String name) {
        return isDisplayed(bookTitle(name));
    }

    public String getBookPrice(String name) {
        return getText(bookPrice(name));
    }

    public void clickAddToBasket(String name) {
        click(addToBasket(name));
    }
    public void goToCart() {
       click(cartLink);
    }
    public void viewBasket() {
        click(viewBasket);
    }
}
