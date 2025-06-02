package features;

import actions.AddNewMedicineAction;
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

public class AddNewMedicineTest {
    WebDriver driver;
    WebDriverWait wait;
    LoginAction login;
    AddNewMedicineAction addNewMedicine;
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
        addNewMedicine = new AddNewMedicineAction(driver);
        addNewProductPageUI = new AddNewProductPageUI(driver);
    }
    @Test()
    public void AddNewMedicineSuccessfulWithRequireFields() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");
        Thread.sleep(2000);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMedicine.navigateToAddNewMedicinePage();
            Map<String, String> rowData = excelData.get(8);
            addNewMedicine.inputRequireData(rowData);
            addNewMedicine.clickButtonLuu();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addNewProductPageUI.getMessageSuccessful())));
            String message = toast.getText();
            Assert.assertEquals(message,"Thêm mới thành công!");

    }

    @Test
    public void testAddNewMedicineSuccessfulWithAllFields() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData1 = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");
        Thread.sleep(2000);
        for(int i=9; i<10; i++){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMedicine.navigateToAddNewMedicinePage();
            Map<String, String> rowData = excelData1.get(i);
            addNewMedicine.inputData(rowData.get("Mã hàng"),rowData.get("Mã vạch"),rowData.get("Tên sản phẩm"),rowData.get("Tên viết tắt"),rowData.get("Nhóm sản phẩm"),rowData.get("Đường dùng"), rowData.get("Vị trí"), rowData.get("Giá vốn"), rowData.get("Giá bán"),rowData.get("Trọng lượng"),rowData.get("Đơn vị cơ bản"));
            addNewMedicine.clickButtonLuu();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addNewProductPageUI.getMessageSuccessful())));
            String message = toast.getText();
            Assert.assertTrue(message.contains("Thêm mới thành công!"));
        }
    }

    @Test
    public void testAddNewMedicineFailed() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData1 = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");
        Thread.sleep(2000);
        for(int i=10; i<14; i++){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMedicine.navigateToAddNewMedicinePage();
            Map<String, String> rowData = excelData1.get(i);
            addNewMedicine.inputData(rowData.get("Mã hàng"),rowData.get("Mã vạch"),rowData.get("Tên sản phẩm"),rowData.get("Tên viết tắt"),rowData.get("Nhóm sản phẩm"),rowData.get("Đường dùng"), rowData.get("Vị trí"), rowData.get("Giá vốn"), rowData.get("Giá bán"),rowData.get("Trọng lượng"),rowData.get("Đơn vị cơ bản"));
            addNewMedicine.clickButtonLuu();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addNewProductPageUI.getMessageRequire())));
            String message = toast.getText();
            Assert.assertTrue(message.contains("Đây là trường bắt buộc!"));
        }
    }

    @Test
    public void testAddNewMedicineFailedWhenCodeExisted() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData1 = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");
        Thread.sleep(2000);
        for(int i=14; i<15; i++){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMedicine.navigateToAddNewMedicinePage();
            Map<String, String> rowData = excelData1.get(i);
            addNewMedicine.inputData(rowData.get("Mã hàng"),rowData.get("Mã vạch"),rowData.get("Tên sản phẩm"),rowData.get("Tên viết tắt"),rowData.get("Nhóm sản phẩm"),rowData.get("Đường dùng"), rowData.get("Vị trí"), rowData.get("Giá vốn"), rowData.get("Giá bán"),rowData.get("Trọng lượng"),rowData.get("Đơn vị cơ bản"));
            addNewMedicine.clickButtonLuu();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = addNewProductPageUI.getDuplicateCodeToastXpath(rowData.get("Mã hàng"));
            String message = toast.getText();
            Assert.assertEquals(message,"Mã hàng "+rowData.get("Mã hàng")+" đã tồn tại.");
        }
    }

    @Test
    public void testAddNewMedicineFailedWhenBarCodeExisted() throws InterruptedException, AWTException {
        List<Map<String, String>> excelData1 = ExcelUntils.readExcelData(excelFilePath, "Danh sách sản phẩm");
        Thread.sleep(2000);
        for(int i=15; i<16; i++){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://mephar-sit.acdtech.asia/auth/sign-in/");
            login.Login();
            addNewMedicine.navigateToAddNewMedicinePage();
            Map<String, String> rowData = excelData1.get(i);
            addNewMedicine.inputData(rowData.get("Mã hàng"),rowData.get("Mã vạch"),rowData.get("Tên sản phẩm"),rowData.get("Tên viết tắt"),rowData.get("Nhóm sản phẩm"),rowData.get("Đường dùng"), rowData.get("Vị trí"), rowData.get("Giá vốn"), rowData.get("Giá bán"),rowData.get("Trọng lượng"),rowData.get("Đơn vị cơ bản"));
            addNewMedicine.clickButtonLuu();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
