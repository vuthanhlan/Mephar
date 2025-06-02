package features;

import actions.DeleteProductAction;
import actions.LoginAction;
import actions.RegisterAction;
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
import ui.RegisterPageUI;
import utils.ExcelUntils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RegisterTest {
    WebDriver driver;
    RegisterAction registerAction;
    String excelFilePath="data/register.xlsx";

    @BeforeMethod
    public void setUp() throws Exception {
        // Khởi tạo ChromeOptions và bật chế độ ẩn danh
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        registerAction = new RegisterAction(driver);
    }

    @Test(testName = "SU_1")
    public void RegisterSuccess() throws Exception {
        List<Map<String, String>> data = ExcelUntils.readExcelData(excelFilePath, "Sheet1");
        Map<String, String> rowData = data.get(0);
        registerAction.dangKy(rowData);
        String msg = registerAction.getSuccessMessage();
        Assert.assertEquals(msg, "Đăng ký tài khoản thành công");
    }

    @Test(testName = "SU_2")
    public void RegisterSDTNotValid() throws Exception {
        List<Map<String, String>> data = ExcelUntils.readExcelData(excelFilePath, "Sheet1");
        Map<String, String> rowData = data.get(1);
        registerAction.dangKy(rowData);
        String msg = registerAction.getErrorMessage();
        Assert.assertEquals(msg, "Vui lòng nhập đúng định dạng số điện thoại");
    }

    @Test(testName = "SU_3")
    public void RegisterEmailNotValid() throws Exception {
        List<Map<String, String>> data = ExcelUntils.readExcelData(excelFilePath, "Sheet1");
        Map<String, String> rowData = data.get(2);
        registerAction.dangKy(rowData);
        String msg = registerAction.getErrorMessage();
        Assert.assertEquals(msg, "Vui lòng nhập đúng định dạng email");
    }

    @Test(testName = "SU_4")
    public void RegisterEmpty() throws Exception {
        List<Map<String, String>> data = ExcelUntils.readExcelData(excelFilePath, "Sheet1");
        Map<String, String> rowData = data.get(3);
        registerAction.dangKy(rowData);
        String msg = registerAction.getErrorMessage();
        Assert.assertTrue(msg.contains("bắt buộc"));
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
