package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.DeleteProductUI;
import ui.NavigatePageUI;

import java.time.Duration;
import java.util.List;

public class DeleteProductAction {
    WebDriver driver;
    DeleteProductUI deleteProductUI;
    NavigatePageUI navigatePageUI;

    public DeleteProductAction(WebDriver driver) {
        this.driver = driver;
        deleteProductUI = new DeleteProductUI(driver);
        navigatePageUI = new NavigatePageUI(driver);
    }
    public void navigateToListProduct() {
        navigatePageUI.getLinkProduct().click();
        navigatePageUI.getLinkListProduct().click();
    }


    public void searchProduct(String productName) {
        deleteProductUI.getTextSearch().clear();
        deleteProductUI.getTextSearch().sendKeys(productName);
    }



    public void viewProductDetails(String product) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement Product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DeleteProductUI.getProduct(product))));
        Product.click();
    }

    public void deleteProduct(String product) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement buttonDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteProductUI.getButtonDelete())));
        buttonDelete.click();

    }
    public void clickDefineDeleteButton() {
        deleteProductUI.getButtonDefineDelete().click();
    }

    public void clickCancelButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement buttonCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteProductUI.getCancelButton())));
        buttonCancel.click();
    }

    public boolean isPopupClosed() {
        String xpath = deleteProductUI.getPopupDelete();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Đợi cho popup không còn trong DOM
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isProductExist(String product) {
        String xpath = deleteProductUI.getProduct(product); // nhận chuỗi XPath
        List<WebElement> products = driver.findElements(By.xpath(xpath)); // tạo locator từ String
        return products.size() > 0;
    }
}
