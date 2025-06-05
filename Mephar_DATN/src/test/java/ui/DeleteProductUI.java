package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteProductUI {
    WebDriver driver;
    public DeleteProductUI(WebDriver driver) {
        this.driver = driver;
    }
    public static String getButtonDelete() { return "//span[contains(text(),'Xoá')]/parent::button";}
    public WebElement getButtonDefineDelete(){return driver.findElement(By.xpath("//span[contains(text(),'Xóa')]/parent::button"));}
    public static String getCancelButton() {return "//span[contains(text(),'Hủy')]/parent::button";}
    public static String getPopupDelete() {return "//div[contains(@role,'dialog')]";}
    public WebElement getTextSearch() {return driver.findElement(By.xpath("//input[contains(@placeholder,'Tìm kiếm')]"));}
    public static String getProduct(String product) {return "//td[contains(text(),'"+product+"')]";}
    public static String SuccessMessageDelete() {return "//div[contains(@class,'message-success')]";}
    public static String ErrorMessageDelete() {return "//div[contains(@class,'message-error')]";}
}
