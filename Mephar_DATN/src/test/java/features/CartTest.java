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
import ui.CartUI;

import java.util.List;

public class CartTest {
    WebDriver driver;
    LoginAction loginAction;
    CartAction cartAction;
    ProductDetailAction productDetailAction;
    CartUI cartUI;
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
        cartUI = new CartUI(driver);
    }

    @Test(testName = "CM_1")
    public void CartHasProduct() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(3000);
        productDetailAction.clickDetail();
        Thread.sleep(2000);
        cartAction.addCart();
        Thread.sleep(1000);
        cartAction.clickCart();
        List<WebElement> products = cartAction.getListProduct();
        Thread.sleep(2000);
        Assert.assertTrue(!products.isEmpty());
    }

    @Test(testName = "CM_2")
    public void ProductDetail() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(2000);
        productDetailAction.clickDetail();
        Thread.sleep(2000);
        cartAction.addCart();
        Thread.sleep(2000);
        Assert.assertEquals(cartAction.getMessageSuccess(), "Thêm mới thành công!");
    }

    @Test(testName = "CM_3")
    public void UpdateQuantity() throws InterruptedException {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(2000);
        productDetailAction.clickDetail();
        Thread.sleep(2000);
        cartAction.addCart();
        Thread.sleep(1000);
        cartAction.clickCart();
        Thread.sleep(1000);
        WebElement quantityInput = cartUI.getQuantityText();
        quantityInput.clear();
        cartAction.inputQuantity("3");
        String message = cartUI.getMess().getText();
        Thread.sleep(2000);
        Assert.assertEquals(message,"Cập nhật thành công!");
    }

    @Test(testName = "CM_4")
    public void DeleteProduct() throws InterruptedException {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(2000);
        productDetailAction.clickDetail();
        Thread.sleep(2000);
        cartAction.addCart();
        Thread.sleep(1000);
        cartAction.clickCart();
        Thread.sleep(2000);
        cartAction.clickIconDelete();
        Thread.sleep(2000);
        cartAction.clickDeleteBtn();
        String message = cartUI.getMessDelete().getText();
        Assert.assertEquals(message,"Xóa thành công!");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
