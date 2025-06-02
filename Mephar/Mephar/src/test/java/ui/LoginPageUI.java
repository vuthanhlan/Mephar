package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageUI {
    WebDriver driver;
    public LoginPageUI(WebDriver driver) {this.driver = driver;}
    public WebElement getTextUserNameField() {return driver.findElement(By.xpath("//input[contains(@placeholder,'Tên đăng nhập')]"));}
    public WebElement getTextPasswordField() {return driver.findElement(By.xpath("//input[contains(@placeholder,'Mật khẩu')]"));}
    public WebElement getButtonLogin() {return driver.findElement(By.xpath("//span[contains(text(),'Đăng nhập')]/parent::button"));}
}
