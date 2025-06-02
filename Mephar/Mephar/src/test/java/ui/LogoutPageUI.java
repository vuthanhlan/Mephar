package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPageUI {
    WebDriver driver;
    WebDriverWait wait;
    public LogoutPageUI(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public WebElement getLogoutBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div/ul/li")
        ));
    }

    public WebElement getLogoutDropdown() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"__next\"]/div/div/div/div[2]/div/div[1]/div[2]/div[2]")
        ));
    }
}
