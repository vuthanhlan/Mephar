package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigatePageUI {
    WebDriver driver;
    public NavigatePageUI(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getLinkProduct(){return driver.findElement(By.xpath("//span[contains(text(),'Sản phẩm')]"));}
    public WebElement getLinkListProduct(){return driver.findElement(By.xpath("//span[contains(text(),'Danh sách sản phẩm')]")); }
    public WebElement getButtonAddProduct(){return driver.findElement(By.xpath("//span[contains(text(),'Thêm mới')]/ancestor::button"));}
    public WebElement getLinkAddNewMedicine(){return driver.findElement(By.xpath("//span[contains(text(),'Thêm mới thuốc')]/parent::li"));}
    public WebElement getLinkAddNewMerchandise(){return driver.findElement(By.xpath("//span[contains(text(),'Thêm mới hàng hóa')]/parent::li"));}
    public static String getMarketButton() {return "//span[contains(text(),'Chợ')]";}
    public WebElement getMarketLink() {return driver.findElement(By.xpath("//a[contains(text(),'Chợ')]"));}
}
