package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private final By loginButtonInForgotPasswordPage = By.xpath("//*[text()='Войти']");

    @Step("Нажатие на кнопку 'Войти' на странице восстановления пароля")
    public void clickLoginButtonInForgotPasswordPage() {
        driver.findElement(loginButtonInForgotPasswordPage).click();
    }

}