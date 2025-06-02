package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductDetailUI {
    WebDriver driver;
    WebDriverWait wait;

    public ProductDetailUI(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement firstProduct() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.grid > div:nth-child(1) h3.cursor-pointer")
        ));
    }

    public WebElement title() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div/div[1]/div[2]/h1")
        ));
    }

    public WebElement img (){return driver.findElement(By.cssSelector("img.w-full.h-full.object-cover"));}
}
