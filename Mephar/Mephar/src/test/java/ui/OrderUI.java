package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderUI {
    WebDriver driver;
    WebDriverWait wait;

    public OrderUI(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement getOrderBtn() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"__next\"]/div/div/div[1]/div/div[3]/div[1]/div[2]")
        ));
    }

    public WebElement getOrderBuyBtn() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div")
        ));
    }
    public WebElement messageNoData() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'ant-empty-description')]")
        ));
    }


}
