package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.RegisterPageUI;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RegisterAction {
    WebDriver driver;
    WebDriverWait wait;
    RegisterPageUI page;

    public RegisterAction(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.page = new RegisterPageUI(driver);
    }

    public void moTrangDangKy() {
        driver.get("https://mephareg-sit.acdtech.asia/");
    }

    public void dienThongTinNguoiDung(Map<String, String> data) {
        page.inputHoTen().sendKeys(data.get("HoTen"));
        page.inputTenDangNhap().sendKeys(data.get("TenDangNhap"));
        page.inputMatKhau().sendKeys(data.get("MatKhau"));
        page.inputNhapLaiMatKhau().sendKeys(data.get("MatKhau")); // Nhập lại giống mật khẩu
        page.inputDienThoaiNguoiDung().sendKeys(data.get("SoDienThoai"));
        page.inputEmail().sendKeys(data.get("Email"));
    }

    public void dienThongTinCuaHang(Map<String, String> data) {
        page.inputTenCuaHang().sendKeys(data.get("TenCuaHang"));
        page.inputDienThoaiCuaHang().sendKeys(data.get("DienThoaiCH"));
        page.inputSoDangKyKinhDoanh().sendKeys(data.get("SoDKKD"));
        page.inputDiaChi().sendKeys(data.get("DiaChi"));
    }

    public void chonTinhThanh(String tinhThanh) {
        page.dropdownTinhThanh().click();
        List<WebElement> options = page.listDropdownOptions();
        for (WebElement opt : options) {
            if (opt.getText().trim().equalsIgnoreCase(tinhThanh)) {
                opt.click();
                break;
            }
        }
    }

    public void chonQuanHuyen(String quanHuyen) {
        page.dropdownQuanHuyen().click();
        List<WebElement> options = page.listDropdownOptions();
        for (WebElement opt : options) {
            if (opt.getText().trim().equalsIgnoreCase(quanHuyen)) {
                opt.click();
                break;
            }
        }
    }

    public void chonPhuongXa(String phuongXa) {
        page.dropdownPhuongXa().click();
        List<WebElement> options = page.listDropdownOptions();
        for (WebElement opt : options) {
            if (opt.getText().trim().equalsIgnoreCase(phuongXa)) {
                opt.click();
                break;
            }
        }
    }

    public void dangKyTaiKhoan() {
        page.buttonDangKy().click();
    }

    public void dangKy(Map<String, String> data) throws Exception {
        moTrangDangKy();
        dienThongTinNguoiDung(data);
        dienThongTinCuaHang(data);
        chonTinhThanh(data.get("TinhThanh"));
        Thread.sleep(3000);
        chonQuanHuyen(data.get("QuanHuyen"));
        Thread.sleep(3000);
        chonPhuongXa(data.get("PhuongXa"));
        dangKyTaiKhoan();
    }

    public String getSuccessMessage() {
        return page.thongBaoDangKyThanhCong().getText();
    }

    public String getErrorMessage() {
        return page.thongBaoLoi().getText();
    }

}
