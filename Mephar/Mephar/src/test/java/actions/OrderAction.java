package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.OrderUI;

import java.util.List;

public class OrderAction {
    WebDriver driver;
    OrderUI page;
    public OrderAction(WebDriver driver) {
        this.driver = driver;
        page = new OrderUI(driver);
    }

    public List<WebElement> buyOrder() throws Exception{
        driver.get("https://mephar-sit.acdtech.asia/markets");
        page.getOrderBtn().click();
//        page.getOrderBuyBtn().click();
        driver.get("https://mephar-sit.acdtech.asia/markets/buy-order/");
        Thread.sleep(4000);
        return driver.findElements(By.cssSelector(".ant-table-tbody .ant-table-row"));
    }
}
