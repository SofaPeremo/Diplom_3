package org.example;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.api.User;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

public class LoginTests {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserGenerator userGenerator;
    protected String accessToken;
    protected User testUser;
    protected String userEmail;
    protected String userPassword;


    @Before
    @Step("Подготовка тестовых данных и инициализация драйвера")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        registerPage = new RegisterPage(driver);

        // Регистрация и получение тестового пользователя
        userGenerator = new UserGenerator();
        userGenerator.registerUser();
        this.accessToken = userGenerator.getAccessToken();

        // Сохраняем данные пользователя для использования в тестах
        this.testUser = userGenerator.getUser();
        this.userEmail = testUser.getEmail();
        this.userPassword = testUser.getPassword();

        mainPage.open();
    }

    @Test
    @Step("Проверка входа через кнопку «Войти в аккаунт»")
    public void testLoginViaMainButton() {
        mainPage.clickLoginToPersonalAccountButton();
        loginPage.enterEmailInput(userEmail);
        loginPage.enterPasswordInput(userPassword);
        loginPage.clickLoginButton();

        Assert.assertTrue("Успешная авторизация через кнопку «Войти в аккаунт»",
                mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @Step("Проверка входа через кнопку «Личный кабинет»")
    public void testLoginViaPersonalAccountButton() {
        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailInput(userEmail);
        loginPage.enterPasswordInput(userPassword);
        loginPage.clickLoginButton();

        Assert.assertTrue("Успешная авторизация через кнопку «Личный кабинет»",
                mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @Step("Проверка входа через кнопку в форме регистрации")
    public void testLoginViaLoginButtonInRegisterPage() {
        mainPage.clickPersonalAccountButton();
        registerPage.clickLoginButton();
        loginPage.enterEmailInput(userEmail);
        loginPage.enterPasswordInput(userPassword);
        loginPage.clickLoginButton();

        Assert.assertTrue("Успешная авторизация через кнопку «Вход» в форме регистрации",
                mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @Step("Проверка входа через кнопку в форме восстановления пароля")
    public void testLoginViaFormPasswordRecovery() {
        mainPage.clickPersonalAccountButton();
        loginPage.clickRecoverPasswordButton();
        forgotPasswordPage.clickLoginButtonInForgotPasswordPage();
        loginPage.enterEmailInput(userEmail);
        loginPage.enterPasswordInput(userPassword);
        loginPage.clickLoginButton();

        Assert.assertTrue("Успешная авторизация через кнопку «Восстановить пароль»",
                mainPage.isMakeOrderButtonDisplayed());
    }

    @After
    @Step("Очистка тестовых данных и закрытие браузера")
    public void tearDown() {
        if (accessToken != null) {
            userGenerator.deleteUser(accessToken);
        }
        driver.quit();
    }
}