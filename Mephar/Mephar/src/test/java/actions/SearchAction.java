package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.SearchProductPageUI;
import ui.NavigatePageUI;

import java.time.Duration;

public class SearchAction {
    WebDriver driver;
    WebDriverWait wait;
    NavigatePageUI navigatePageUI;
    SearchProductPageUI searchProductPageUI;

    public SearchAction(WebDriver driver) {
        this.driver = driver;
        this.navigatePageUI = new NavigatePageUI(driver);
        this.searchProductPageUI = new SearchProductPageUI(driver);
    }

    public void navigateMarket()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement marketButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(navigatePageUI.getMarketButton())));
        marketButton.click();
        navigatePageUI.getMarketLink().click();
    }
    public void inputDataSearch(String keyword) {
        searchProductPageUI.getSearchTextbox().sendKeys(keyword + Keys.ENTER);
    }
}
