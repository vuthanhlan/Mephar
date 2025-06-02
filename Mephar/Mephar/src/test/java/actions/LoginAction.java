package actions;

import org.openqa.selenium.WebDriver;
import ui.LoginPageUI;

public class LoginAction {
    WebDriver driver;
    LoginPageUI loginPageUI;
    public LoginAction(WebDriver driver) {
        this.driver = driver;
        loginPageUI = new LoginPageUI(driver);
    }
    public void inputData(String username, String password) {
        loginPageUI.getTextUserNameField().sendKeys(username);
        loginPageUI.getTextPasswordField().sendKeys(password);
    }
    public void clickLoginButton() {loginPageUI.getButtonLogin().click();}

    public void Login(){
        inputData("test30","12345678");
        clickLoginButton();
    }
    public void LoginAccount(){
        inputData("test31","12345678");
        clickLoginButton();
    }
}
