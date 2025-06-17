package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private final By profileButtonInPersonalAccount = By.xpath("//*[text()='Профиль']");
    private final By constructorInHeaderButton = By.xpath("//*[text()='Конструктор']");
    private final By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private final By logout = By.xpath("//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()='Выход']");

    public boolean isProfileButtonInPersonalAccount() {
        return driver.findElement(profileButtonInPersonalAccount).isDisplayed();
    }

    @Step("Клик по кнопке 'Конструктор' в шапке сайта")
    public void clickConstructorInHeaderButton() {
        driver.findElement(constructorInHeaderButton).click();
    }

    @Step("Клик по лого сайта")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickLogout() {
        driver.findElement(logout).click();
    }
}