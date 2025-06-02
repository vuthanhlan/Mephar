package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.CartUI;
import ui.ProductDetailUI;

import java.util.List;

public class CartAction {
    WebDriver driver;
    CartUI page;
    ProductDetailUI productDetailUI;
    public CartAction(WebDriver driver) {
        this.driver = driver;
        page = new CartUI(driver);
    }

    public void clickCart() {
        driver.get("https://mephar-sit.acdtech.asia/markets");
        page.getCartBtn().click();
    }

    public List<WebElement> getListProduct() {
        clickCart();
        WebElement product = page.getProductList();
        return product.findElements(By.cssSelector(".grid-cols-12"));
    }

    public void addCart() {
        page.getAddBtn().click();
    }

    public String getMessageSuccess() {
        return page.getSuccessMessage().getText();
    }

    public void clickCheckbox() throws Exception {
        List<WebElement> checkboxes = page.getFirstCheckbox();
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        page.getMuaHangBtn().click();
        Thread.sleep(3000);
        page.getDatHangBtn().click();
    }

    public void inputQuantity(String num) {
        page.getQuantityText().clear();
        page.getQuantityText().sendKeys(num);
    }

    public  void clickIconDelete() {
        page.getDeleteIcon().click();
    }
    public void  clickDeleteBtn() {
        page.getDeleteBtn().click();
    }
}
