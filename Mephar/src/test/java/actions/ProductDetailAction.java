package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.LogoutPageUI;
import ui.ProductDetailUI;

public class ProductDetailAction {
    WebDriver driver;
    ProductDetailUI page;
    public ProductDetailAction(WebDriver driver) {
        this.driver = driver;
        page = new ProductDetailUI(driver);
    }
    public String clickDetail() {
       driver.get("https://mephar-sit.acdtech.asia/markets");
       WebElement product = page.firstProduct();
        System.out.println(product.getText());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
        product.click();
        return product.getText();
    }

    public String getTile() {
        return page.title().getText();
    }
}
