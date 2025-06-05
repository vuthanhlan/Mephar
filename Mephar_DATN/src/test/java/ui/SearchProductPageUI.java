package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchProductPageUI {
    WebDriver driver;
    public SearchProductPageUI(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSearchTextbox() { return  driver.findElement(By.xpath("//input[contains(@placeholder,'Tìm kiếm sản phẩm')]"));}
    public static String getResultText() { return "//h4[contains(text(),'Kết quả')]";}
    public List<WebElement> getProductName()  {return driver.findElements(By.xpath("//h3[ contains(@class,'text-base')]"));}
    public static  String getMessNotResultText() { return "//h4[contains(text(),'Không tìm thấy sản phẩm')]";}
}
