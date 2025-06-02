package features;

import actions.LoginAction;
import actions.SearchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.SearchProductPageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchTest {
    WebDriver driver;
    WebDriverWait wait;
    LoginAction login;
    SearchAction search;
    SearchProductPageUI searchProductPageUI;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        login = new LoginAction(driver);
        search = new SearchAction(driver);
        searchProductPageUI = new SearchProductPageUI(driver);

    }

    @Test(testName = "SP_1")
    public void searchHasProduct() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        //B1: Login
        login.Login();
        Thread.sleep(1000);

        String originalTab = driver.getWindowHandle();
        //B2: navigate to market
        search.navigateMarket();
        Thread.sleep(2000);

        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        String keyword = "Ad";
        search.inputDataSearch(keyword);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchProductPageUI.getResultText())));

        List<WebElement> results = searchProductPageUI.getProductName();

        for (WebElement result : results) {
            String resultText = result.getText().toLowerCase();
            assertTrue(resultText.contains(keyword.toLowerCase()),
                    "Kết quả không chứa từ khóa: " + resultText);
        }
    }

    @Test(testName = "SP_2")
    public void searchDoNotHasProduct() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        //B1: Login
        login.Login();
        Thread.sleep(1000);
        String originalTab = driver.getWindowHandle();
        //B2: navigate to market
        search.navigateMarket();
        Thread.sleep(2000);
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        String keyword = "dddd";
        search.inputDataSearch(keyword);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchProductPageUI.getResultText())));


        WebElement toast1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchProductPageUI.getMessNotResultText())));
        String message = toast1.getText();
        Assert.assertEquals(message, "Không tìm thấy sản phẩm");

    }

    @Test(testName = "SP_3")
    public void searchDoNotInputData() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        //B1: Login
        login.Login();
        Thread.sleep(1000);
        String originalTab = driver.getWindowHandle();
        //B2: navigate to market
        search.navigateMarket();
        Thread.sleep(2000);
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        String keyword = "";

        search.inputDataSearch(keyword);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchProductPageUI.getResultText())));


        List<WebElement> results = searchProductPageUI.getProductName();

        // Kiểm tra có kết quả được hiển thị
        assertFalse(results.isEmpty(), "Không có kết quả nào hiển thị khi tìm kiếm rỗng");

        // Optional: in ra từng kết quả
        for (WebElement result : results) {
            System.out.println(result.getText());
        }

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
