package features;

import actions.DeleteProductAction;
import actions.LoginAction;
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
import ui.DeleteProductUI;
import untils.ExcelUntils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class DeleteProductTest {
    WebDriver driver;
    DeleteProductUI deleteProductUI;
    DeleteProductAction deleteProductAction;
    LoginAction login;
    String excelFilePath="data/productData.xlsx";
    @BeforeMethod
    public void setUp() throws Exception {
        // Khởi tạo ChromeOptions và bật chế độ ẩn danh
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        deleteProductUI = new DeleteProductUI(driver);
        deleteProductAction = new DeleteProductAction(driver);
        login = new LoginAction(driver);

    }
    @Test(testName = "DP_1")
    public void testDeleteProductSuccess() throws InterruptedException {
        List<Map<String, String>> excelData = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");

        Map<String, String> rowData = excelData.get(1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        //B1: Login
        login.Login();
        Thread.sleep(2000);
        //B2: Navigate to List Product Page
        deleteProductAction.navigateToListProduct();
        deleteProductAction.searchProduct(rowData.get("Mã hàng"));
        deleteProductAction.viewProductDetails(rowData.get("Mã hàng"));
        deleteProductAction.deleteProduct(rowData.get("Mã hàng"));
        deleteProductAction.clickDefineDeleteButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteProductUI.SuccessMessageDelete())));
        String message = toast.getText();
        Assert.assertEquals(message, "Xóa thành công!");

    }

    @Test(testName = "DP_2")
    public void testDeleteProductFailedWhenProductUsed() throws InterruptedException {

        List<Map<String, String>> excelData = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");

        Map<String, String> rowData = excelData.get(16);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        //B1: Login
        login.Login();
        Thread.sleep(2000);
        //B2: Navigate to List Product Page
        deleteProductAction.navigateToListProduct();
        deleteProductAction.searchProduct(rowData.get("Mã hàng"));
        deleteProductAction.viewProductDetails(rowData.get("Mã hàng"));
        Thread.sleep(2000);
        deleteProductAction.deleteProduct(rowData.get("Mã hàng"));
        deleteProductAction.clickDefineDeleteButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteProductUI.ErrorMessageDelete())));
        String message = toast.getText();
        Assert.assertEquals(message, "Sản phẩm đang được sử dụng ở nhập hàng, không thể xóa");
    }

    @Test(testName = "DP_3")
    public void testDeleteProductFailedWhenClickHuyButton() throws InterruptedException {
        List<Map<String, String>> excelData = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");
        Map<String, String> rowData = excelData.get(16);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
        //B1: Login
        login.Login();
        Thread.sleep(4000);
        //B2: Navigate to List Product Page
        deleteProductAction.navigateToListProduct();
        deleteProductAction.searchProduct(rowData.get("Mã hàng"));
        deleteProductAction.viewProductDetails(rowData.get("Mã hàng"));
        deleteProductAction.deleteProduct(rowData.get("Mã hàng"));
        deleteProductAction.clickCancelButton();
        Assert.assertTrue(deleteProductAction.isPopupClosed(), "Popup phải được đóng sau khi hủy");
        Assert.assertTrue(deleteProductAction.isProductExist(rowData.get("Mã hàng")), "Sản phẩm vẫn còn sau khi hủy xóa");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
