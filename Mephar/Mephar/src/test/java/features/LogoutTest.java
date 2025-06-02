package features;

import actions.LoginAction;
import actions.LogoutAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest {
    WebDriver driver;
    LoginAction loginAction;
    LogoutAction logoutAction;

    @BeforeMethod
    public void setUp() throws Exception {
        // Khởi tạo ChromeOptions và bật chế độ ẩn danh
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginAction = new LoginAction(driver);
        logoutAction = new LogoutAction(driver);
    }

    @Test(testName = "LO_1")
    public void LogoutTest() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(5000);
        logoutAction.logout();
        Thread.sleep(5000);
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://mephar-sit.acdtech.asia/auth/sign-in/");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
