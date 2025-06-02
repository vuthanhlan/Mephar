package features;

import actions.CartAction;
import actions.LoginAction;
import actions.ProductDetailAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BuyProductTest {
    WebDriver driver;
    LoginAction loginAction;
    CartAction cartAction;
    ProductDetailAction productDetailAction;
    String excelFilePath="data/register.xlsx";

    @BeforeMethod
    public void setUp() throws Exception {
        // Khởi tạo ChromeOptions và bật chế độ ẩn danh
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        cartAction = new CartAction(driver);
        loginAction = new LoginAction(driver);
        productDetailAction = new ProductDetailAction(driver);
    }

    @Test(testName = "BP_1")
    public void BuyProduct() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(2000);
        productDetailAction.clickDetail();
        Thread.sleep(2000);
        cartAction.addCart();
        Thread.sleep(1000);
        cartAction.clickCart();
        cartAction.clickCheckbox();
        Thread.sleep(3000);
        String msg = cartAction.getMessageSuccess();
        Assert.assertEquals(msg, "Thêm mới thành công!");
    }

    

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
