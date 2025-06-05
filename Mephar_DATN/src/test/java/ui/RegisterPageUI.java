package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegisterPageUI {
    WebDriver driver;
    WebDriverWait wait;

    public RegisterPageUI(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ----------- THÔNG TIN NGƯỜI DÙNG -----------

    public WebElement inputHoTen() {
        return getInputByIndex(0);
    }

    public WebElement inputTenDangNhap() {
        return getInputByIndex(1);
    }

    public WebElement inputMatKhau() {
        return getInputByIndex(2);
    }

    public WebElement inputNhapLaiMatKhau() {
        return getInputByIndex(3);
    }

    public WebElement inputDienThoaiNguoiDung() {
        return getInputByIndex(4);
    }

    public WebElement inputEmail() {
        return getInputByIndex(5);
    }

    // ----------- THÔNG TIN CỬA HÀNG -----------

    public WebElement inputTenCuaHang() {
        return getInputByIndex(6);
    }

    public WebElement inputDienThoaiCuaHang() {
        return getInputByIndex(7);
    }

    public WebElement inputSoDangKyKinhDoanh() {
        return getInputByIndex(8);
    }

    public WebElement inputDiaChi() {
        return getInputByIndex(12);
    }

    // ----------- DROPDOWN TỈNH – QUẬN – PHƯỜNG -----------

    public WebElement dropdownTinhThanh() {
        return getDropdownByPlaceholder("Chọn tỉnh thành");
    }

    public WebElement dropdownQuanHuyen() {
        return getDropdownByPlaceholder("Chọn Quận huyện");
    }

    public WebElement dropdownPhuongXa() {
        return getDropdownByPlaceholder("Chọn Phường xã");
    }

    public List<WebElement> listDropdownOptions() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".el-select-dropdown__item")));
    }

    // ----------- NÚT ĐĂNG KÝ -----------

    public WebElement buttonDangKy() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[span[text()='Đăng ký']]")));
    }

    // ----------- TIỆN ÍCH -----------

    private WebElement getInputByIndex(int index) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("input.el-input__inner"))).get(index);
    }

    private WebElement getDropdownByPlaceholder(String placeholderText) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@placeholder='" + placeholderText + "']")));
    }

    public WebElement thongBaoDangKyThanhCong() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'el-tag--success')]")
        ));
    }

    public WebElement thongBaoLoi() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'el-form-item__error')]")
        ));
    }

}
