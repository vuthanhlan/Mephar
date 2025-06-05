package features;

import actions.LoginAction;
import actions.RegisterAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest {
    WebDriver driver;
    LoginAction loginAction;

    @BeforeMethod
    public void setUp() throws Exception {
        // Khởi tạo ChromeOptions và bật chế độ ẩn danh
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginAction = new LoginAction(driver);
    }

    @Test(testName = "LG_1")
    public void LoginSuccess() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(10000);
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://mephar-sit.acdtech.asia/");
    }

    @Test(testName = "LG_2")
    public void LoginWithEmptyField() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.inputData("", "");
        loginAction.clickLoginButton();
        Thread.sleep(5000);
        String url = driver.getCurrentUrl();
        Assert.assertNotEquals(url, "https://mephar-sit.acdtech.asia/");
    }

    @Test(testName = "LG_3")
    public void LoginWithUsernameFail() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.inputData("qweqe", "12345678");
        loginAction.clickLoginButton();
        Thread.sleep(5000);
        String url = driver.getCurrentUrl();
        Assert.assertNotEquals(url, "https://mephar-sit.acdtech.asia/");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
