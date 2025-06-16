package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private final By emailLoginInput = By.xpath("//div[label[text()='Email']]//input");
    private final By passwordLoginInput = By.xpath("//div[label[text()='Пароль']]//input");
    private final By registrationLinkInFooter = By.xpath("//*[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath("//*[text()='Войти']");
    private final By recoverPasswordLinkInFooter = By.xpath("//*[text()='Восстановить пароль']");
    private final By loginForm = By.xpath("//div[@class='Auth_login__3hAey']");

    @Step("Ввод e-mail: {0}")
    public void enterEmailInput(String email) {
        driver.findElement(emailLoginInput).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPasswordInput(String password) {
        driver.findElement(passwordLoginInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Войти' под формой захвата")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Переход по ссылке 'Зарегистрироваться' в футере")
    public void clickRegistrationLinkInFooter() {
        driver.findElement(registrationLinkInFooter).click();
    }

    @Step("Переход по ссылке 'Восстановить пароль'")
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordLinkInFooter).click();
    }

    public boolean isLoginFormDisplayed() {
        return driver.findElement(loginForm).isDisplayed();
    }

}