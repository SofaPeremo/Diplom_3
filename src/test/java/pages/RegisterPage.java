package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private final By nameRegistrationInput = By.xpath("//div[label[text()='Имя']]//input");
    private final By emailRegistrationInput = By.xpath("//div[label[text()='Email']]//input");
    private final By passwordRegistrationInput = By.xpath("//div[label[text()='Пароль']]//input");
    private final By registrationButton = By.xpath("//*[text()='Зарегистрироваться']");
    private final By errorIncorrectPassword = By.xpath("//*[text()='Некорректный пароль']");
    private final By loginButtonInRegisterPage = By.xpath("//*[text()='Войти']");

    @Step("Ввод имени: {0}")
    public void enterNameRegistration(String name) {
        driver.findElement(nameRegistrationInput).sendKeys(name);
    }

    @Step("Ввод e-mail: {0}")
    public void enterEmailRegistration(String email) {
        driver.findElement(emailRegistrationInput).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPasswordRegistration(String password) {
        driver.findElement(passwordRegistrationInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public String textErrorElement() {
        return driver.findElement(errorIncorrectPassword).getText();
    }

    @Step("Нажатие на кнопку 'Войти' со страницы регистрации")
    public void clickLoginButton() {
        driver.findElement(loginButtonInRegisterPage).click();
    }
}