package features;

import actions.AddNewMerchandiseAction;
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
import ui.AddNewProductPageUI;
import utils.ExcelUntils;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


public class AddNewMerchandiseTest {
    WebDriver driver;
    WebDriverWait wait;
    LoginAction login;
    AddNewMerchandiseAction addNewMerchandise;
    AddNewProductPageUI addNewProductPageUI;
    String excelFilePath="data/productData.xlsx";

    @BeforeMethod
    public void setUp() {

        // Khởi tạo ChromeOptions và bật chế độ ẩn danh
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        login = new LoginAction(driver);
        addNewMerchandise = new AddNewMerchandiseAction(driver);
        addNewProductPageUI = new AddNewProductPageUI(driver);

    }
    @Test(testName = "IP_1")
    public void testAddNewProductSuccessfulWithRequiredFields() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            //B1: Login
            login.Login();
            //B2: Navigate to add new Merchandise page
            addNewMerchandise.navigateToAddNewMerchandisePage();
            //B3: Input required fields
            Map<String, String> rowData = excelData.get(0);
            addNewMerchandise.inputRequireData(rowData.get("Tên sản phẩm"), Integer.parseInt(rowData.get("Giá bán")),Integer.parseInt(rowData.get("Tồn kho")),rowData.get("Đơn vị cơ bản"));
//            addNewProduct.loadImage();
            Thread.sleep(5000);
            //B4: Click button Luu
            addNewMerchandise.clickButtonLuu();
            //B5: Observe
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addNewProductPageUI.getMessageSuccessful())));
            String message = toast.getText();
            Assert.assertTrue(message.contains("Thêm mới thành công!"));

            WebElement ActualName = addNewProductPageUI.getActualProductName();
            WebElement ActualInventor = addNewProductPageUI.getActualInventor();
            WebElement ActualSellingPrice = addNewProductPageUI.getActualSellingPrice();
            Thread.sleep(1000);
            Assert.assertEquals(ActualName.getText(),rowData.get("Tên sản phẩm"));
            Assert.assertEquals(ActualInventor.getText().replace(",","").trim(), rowData.get("Tồn kho"));
            Assert.assertEquals(ActualSellingPrice.getText().replace("đ","").replace(",","").trim(), rowData.get("Giá bán"));


    }

    @Test(testName = "IP_2")
    public void testAddNewProductSuccessfulWithAllFields() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData1 = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");
        Thread.sleep(2000);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMerchandise.navigateToAddNewMerchandisePage();
            Map<String, String> rowData = excelData1.get(1);
            addNewMerchandise.inputData(rowData);
            addNewMerchandise.clickButtonLuu();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addNewProductPageUI.getMessageSuccessful())));
            String message = toast.getText();
            Assert.assertEquals(message,"Thêm mới thành công!");

    }

    @Test(testName = "IP_3")
    public void testAddNewProductFail() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData2 = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");

        Thread.sleep(2000);
        for(int i=2; i<6; i++){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMerchandise.navigateToAddNewMerchandisePage();
            Map<String, String> rowData = excelData2.get(i);
            addNewMerchandise.inputData(rowData);
            addNewMerchandise.clickButtonLuu();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addNewProductPageUI.getMessageRequire())));
            String message = toast.getText();
            Assert.assertTrue(message.contains("Đây là trường bắt buộc!"));

        }
    }
    @Test(testName = "IP_4")
    public void testAddNewProductFailWhenCodeExisted() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData2 = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");

        Thread.sleep(2000);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMerchandise.navigateToAddNewMerchandisePage();
            Map<String, String> rowData = excelData2.get(6);
            addNewMerchandise.inputData(rowData);
            addNewMerchandise.clickButtonLuu();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = addNewProductPageUI.getDuplicateCodeToastXpath(rowData.get("Mã hàng"));
            String message = toast.getText();
            Assert.assertEquals(message,"Mã hàng "+rowData.get("Mã hàng")+" đã tồn tại.");
    }
    @Test(testName = "IP_5")
    public void testAddNewProductFailWhenBarCodeExisted() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData2 = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");

        Thread.sleep(2000);
        for(int i=7; i<8; i++){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMerchandise.navigateToAddNewMerchandisePage();
            Map<String, String> rowData = excelData2.get(i);
            addNewMerchandise.inputData(rowData);
            addNewMerchandise.clickButtonLuu();
            WebElement toast = addNewProductPageUI.getDuplicateBarcodeToastXpath(rowData.get("Mã vạch"));
            String message = toast.getText();
            Assert.assertEquals(message,"Mã vạch "+rowData.get("Mã vạch")+" đã tồn tại.");

        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
