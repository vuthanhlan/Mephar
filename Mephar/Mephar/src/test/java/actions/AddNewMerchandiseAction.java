package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.AddNewProductPageUI;
import ui.NavigatePageUI;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Map;

public class AddNewMerchandiseAction {
    WebDriver driver;
    AddNewProductPageUI addNewProductPageUI;
    NavigatePageUI navigatePageUI;

    public AddNewMerchandiseAction(WebDriver driver) {
        this.driver = driver;
        addNewProductPageUI = new AddNewProductPageUI(driver);
        navigatePageUI = new NavigatePageUI(driver);
    }
    public void navigateToAddNewMerchandisePage() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigatePageUI.getLinkProduct().click();
        navigatePageUI.getLinkListProduct().click();
        navigatePageUI.getButtonAddProduct().click();
//        wait.until(ExpectedConditions.elementToBeClickable(addNewProductPageUI.getButtonAddProduct())).click();
        Thread.sleep(1000);
        navigatePageUI.getLinkAddNewMerchandise().click();
    }
    public void inputRequireData(String MerchandiseName, int sellingPrice, int inventor, String Unit) {
        addNewProductPageUI.getTextMerchandiseName().sendKeys(MerchandiseName);
        addNewProductPageUI.getTextSellingPrice().sendKeys(String.valueOf(sellingPrice));
        addNewProductPageUI.getTextInventor().sendKeys(String.valueOf(inventor));
        addNewProductPageUI.getTextBasicUnit().sendKeys(Unit);

    }
    public void loadImage() throws AWTException {
        // Click vào nút "Tải ảnh lên"
        addNewProductPageUI.getButtonLoadImage().click();

        // Copy đường dẫn file vào clipboard
        String filePath = "C:\\Users\\Vu Thanh Lan\\Downloads\\background\\anh24.jpg";
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();

        // Nhấn Ctrl + V để dán đường dẫn file
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Nhấn Enter để chọn file
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }



    public void inputData(Map<String, String> data) throws InterruptedException {

        addNewProductPageUI.getTextCode().sendKeys(data.get("Mã hàng"));
        addNewProductPageUI.getTextBarcode().sendKeys(data.get("Mã vạch"));
        addNewProductPageUI.getTextMerchandiseName().sendKeys(data.get("Tên sản phẩm"));
        addNewProductPageUI.getTextSummaryName().sendKeys(data.get("Tên viết tắt"));
        addNewProductPageUI.getTextGroup().click();
        addNewProductPageUI.getSubGroup(data.get("Nhóm sản phẩm")).click();
        addNewProductPageUI.getTextLocation().click();
        addNewProductPageUI.getSubLocation(data.get("Vị trí")).click();

        addNewProductPageUI.getTextImportPrice().sendKeys(String.valueOf(data.get("Giá vốn")));
        addNewProductPageUI.getTextSellingPrice().sendKeys(String.valueOf(data.get("Giá bán")));
        addNewProductPageUI.getTextWeight().sendKeys(String.valueOf(data.get("Trọng lượng")));
        addNewProductPageUI.getTextPacking().sendKeys(data.get("Quy cách đóng gói"));
        addNewProductPageUI.getTextBrand().click();
        addNewProductPageUI.getSubRouteBrand(data.get("Hãng sản xuất")).click();

        addNewProductPageUI.getTextManufacturingCountry().click();
        addNewProductPageUI.getSubManufacturingCountry(data.get("Nước sản xuẩt")).click();
        addNewProductPageUI.getTextInventor().sendKeys(String.valueOf(data.get("Tồn kho")));
        addNewProductPageUI.getTextBasicUnit().sendKeys(data.get("Đơn vị cơ bản"));

    }
    public void clickButtonLuu(){
        addNewProductPageUI.getButtonLuu().click();
    }

}
