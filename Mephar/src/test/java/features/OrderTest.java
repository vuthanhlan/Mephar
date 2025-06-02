package features;

import actions.CartAction;
import actions.LoginAction;
import actions.OrderAction;
import actions.ProductDetailAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.OrderUI;

import java.util.List;

public class OrderTest {
    WebDriver driver;
    LoginAction loginAction;
    CartAction cartAction;
    OrderAction orderAction;
    OrderUI orderUI;
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
        orderAction = new OrderAction(driver);
        orderUI = new OrderUI(driver);
    }

    @Test(testName = "OV_1")
    public void OrderHasProduct() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(1000);
        cartAction.clickCart();
        Thread.sleep(2000);
        List<WebElement> elements =  orderAction.buyOrder();
        Assert.assertTrue(elements.size() > 0, "Danh sách đơn hàng trống!");
//        Assert.assertFalse(!elements.isEmpty());
    }

    @Test(testName = "OV_2")
    public void OrderDoNotHasProduct() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.LoginAccount();
        Thread.sleep(1000);
        cartAction.clickCart();
        List<WebElement> elements =  orderAction.buyOrder();
        String message = orderUI.messageNoData().getText();
        Assert.assertEquals(message, "No data");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
