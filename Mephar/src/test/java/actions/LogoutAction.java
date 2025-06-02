package actions;

import org.openqa.selenium.WebDriver;
import ui.LoginPageUI;
import ui.LogoutPageUI;

public class LogoutAction {
    WebDriver driver;
    LogoutPageUI logoutPageUI;
    public LogoutAction(WebDriver driver) {
        this.driver = driver;
        logoutPageUI = new LogoutPageUI(driver);
    }
    public void logout() {
        logoutPageUI.getLogoutDropdown().click();
       logoutPageUI.getLogoutBtn().click();
    }
}
