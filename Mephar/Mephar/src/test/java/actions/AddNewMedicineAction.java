package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.AddNewProductPageUI;
import ui.NavigatePageUI;

import java.time.Duration;
import java.util.Map;

public class AddNewMedicineAction {
    WebDriver driver;
    AddNewProductPageUI addNewProductPageUI;
    NavigatePageUI navigatePageUI;

    public AddNewMedicineAction(WebDriver driver) {
        this.driver = driver;
        this.addNewProductPageUI = new AddNewProductPageUI(driver);
        this.navigatePageUI = new NavigatePageUI(driver);
    }
    public void navigateToAddNewMedicinePage() throws InterruptedException {

        navigatePageUI.getLinkProduct().click();
        navigatePageUI.getLinkListProduct().click();
        navigatePageUI.getButtonAddProduct().click();
//        wait.until(ExpectedConditions.elementToBeClickable(addNewProductPageUI.getButtonAddProduct())).click();
        Thread.sleep(1000);
        navigatePageUI.getLinkAddNewMedicine().click();

    }

    public void inputRequireData(Map<String, String> data){
        addNewProductPageUI.getTextMedicineName().click();
        addNewProductPageUI.getSubMedicineName(data.get("Tên sản phẩm")).click();


        addNewProductPageUI.getRouteOfUse().click();
        addNewProductPageUI.getSubRouteOfUse(data.get("Đường dùng")).click();
        addNewProductPageUI.getTextSellingPrice().sendKeys(data.get("Giá bán"));
        addNewProductPageUI.getTextBasicUnit().sendKeys(data.get("Đơn vị cơ bản"));

    }

    public void inputData(String code, String Barcode, String Name, String SummaryName,String group,String RouteOfUse,String location, String ImportPrice, String sellingPrice, String weight, String Unit) throws InterruptedException {

        addNewProductPageUI.getTextCode().sendKeys(code);
        addNewProductPageUI.getTextBarcode().sendKeys(Barcode);
        addNewProductPageUI.getTextMedicineName().click();
        addNewProductPageUI.getSubMedicineName(Name).click();

        addNewProductPageUI.getTextSummaryName().sendKeys(SummaryName);
        addNewProductPageUI.getTextGroup().click();
        addNewProductPageUI.getSubGroup(group).click();

        addNewProductPageUI.getRouteOfUse().click();
        addNewProductPageUI.getSubRouteOfUse(RouteOfUse).click();

        addNewProductPageUI.getTextLocation().click();
        addNewProductPageUI.getSubLocation(location).click();
        addNewProductPageUI.getTextImportPrice().sendKeys(String.valueOf(ImportPrice));
        addNewProductPageUI.getTextSellingPrice().sendKeys(String.valueOf(sellingPrice));
        addNewProductPageUI.getTextWeight().sendKeys(String.valueOf(weight));
        addNewProductPageUI.getTextBasicUnit().sendKeys(Unit);

    }

    public void clickButtonLuu(){
        addNewProductPageUI.getButtonLuu().click();
    }
}
