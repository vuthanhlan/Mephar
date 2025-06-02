package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartUI {
    WebDriver driver;
    WebDriverWait wait;

    public CartUI(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement getCartBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"__next\"]/div/div/div[1]/div/div[3]/div[2]/div[1]")
        ));
    }

    public WebElement getProductList() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("ant-radio-group")
        ));
    }

    public WebElement getAddBtn() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//span[contains(text(),'Thêm vào giỏ hàng')])[1]")
        ));
    }

    public WebElement getSuccessMessage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".ant-message-success")
        ));
    }

    public List<WebElement> getFirstCheckbox() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("(//input[@class='ant-checkbox-input'])")
        ));
    }

    public WebElement getMuaHangBtn() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div/div[3]/div/div[2]/div/button")
        ));
    }

    public WebElement getDatHangBtn() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[normalize-space()='Đặt hàng']")
        ));
    }
    public WebElement getQuantityText() { return driver.findElement(By.xpath("(//input[contains(@class,'ant-input')])[2]"));}
    public WebElement getMess() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(text(),'Cập nhật thành công!')]")
        ));
    }
    public WebElement getDeleteIcon() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'text-center cursor-pointer')]")
        ));
    }

    public WebElement getDeleteBtn() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div/div/div/div[4]/div[2]/button")
        ));
    }
    public WebElement getMessDelete() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(text(),'Xóa thành công!')]")
        ));
    }
}
