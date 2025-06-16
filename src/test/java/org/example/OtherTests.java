package org.example;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.api.User;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

public class OtherTests {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
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
        profilePage = new ProfilePage(driver);

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
    @Step("Проверка перехода в личный кабинет по клику на «Личный кабинет»")
    public void testCheckTheTransitionOnPersonalAccount() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailInput(userEmail);
        loginPage.enterPasswordInput(userPassword);
        loginPage.clickLoginButton();
        mainPage.clickPersonalAccountButton();
        Assert.assertTrue("Кнопка Профиль должна быть видимой", profilePage.isProfileButtonInPersonalAccount());

    }

    @Test
    @Step("Проверка перехода из личного кабинета в конструктор по клику на «Конструктор»")
    public void testTransitionFromPersonalAccountToTheConstructorByClickingOnConstructor() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailInput(userEmail);
        loginPage.enterPasswordInput(userPassword);
        loginPage.clickLoginButton();
        mainPage.clickPersonalAccountButton();
        profilePage.clickConstructorInHeaderButton();
        Assert.assertTrue("Заголовок сайта отображается на странице", mainPage.isSiteTitleDisplayed());
    }

    @Test
    @Step("Переход из личного кабинета в конструктор по клику на лого Stellar Burgers")
    public void testTransitionFromPersonalAccountToTheConstructorByClickingOnLogo() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailInput(userEmail);
        loginPage.enterPasswordInput(userPassword);
        loginPage.clickLoginButton();
        mainPage.clickPersonalAccountButton();
        profilePage.clickLogo();
        Assert.assertTrue("Заголовок сайта отображается на странице", mainPage.isSiteTitleDisplayed());
    }

    @Test
    @Step("Проверка выхода из аккаунта по кнопке «Выйти» в личном кабинете")
    public void testCheckingForAccountLogout() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.enterEmailInput(userEmail);
        loginPage.enterPasswordInput(userPassword);
        loginPage.clickLoginButton();
        mainPage.clickPersonalAccountButton();
        profilePage.clickLogout();
        Assert.assertTrue("Форма авторизации отображается на странице", loginPage.isLoginFormDisplayed());
    }

    @Test
    @Step("Проверка, что активна вкладка Булки")
    public void assertBunsTabIsActive() {
        mainPage.open();
        Assert.assertEquals("Вкладка Булки не активна", "Булки", mainPage.getTextActiveTab());
    }

    @Test
    @Step("Проверка, что активна вкладка Соусы")
    public void assertSaucesTabIsActive() {
        mainPage.open();
        mainPage.clickSaucesTab();
        Assert.assertEquals("Вкладка Соусы не активна", "Соусы", mainPage.getTextActiveTab());
    }

    @Test
    @Step("Проверка, что активна вкладка Начинки")
    public void assertFillingTabIsActive() {
        mainPage.open();
        mainPage.clickFillingTab();
        Assert.assertEquals("Вкладка Начинки не активна", "Начинки", mainPage.getTextActiveTab());
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        if (accessToken != null) {
            userGenerator.deleteUser(accessToken);
        }
        driver.quit();
    }
}