package features;

import actions.LoginAction;
import actions.ProductDetailAction;
import actions.RegisterAction;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.ProductDetailUI;


public class ProductDetailTest {
    WebDriver driver;
    LoginAction loginAction;
    ProductDetailAction productDetailAction;
    ProductDetailUI productDetailUI;
    String excelFilePath="data/register.xlsx";

    @BeforeMethod
    public void setUp() throws Exception {
        // Khởi tạo ChromeOptions và bật chế độ ẩn danh
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        productDetailAction = new ProductDetailAction(driver);
        loginAction = new LoginAction(driver);
        productDetailUI = new ProductDetailUI(driver);
    }

    @Test(testName = "SU_1")
    public void ProductDetail() throws Exception {
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        loginAction.Login();
        Thread.sleep(1000);
        String title = productDetailAction.clickDetail();
        String detailTitle = productDetailAction.getTile();
        Thread.sleep(3000);
        Assert.assertTrue(detailTitle.contains(title));
        Long naturalWidth = (Long) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].naturalWidth;", productDetailUI.img());
        if (naturalWidth == 0) {
            System.out.println("Ảnh sản phẩm không hiển thị đúng (naturalWidth = 0)");
        } else {
            System.out.println("Ảnh sản phẩm hiển thị bình thường.");
        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
