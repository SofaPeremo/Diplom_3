package org.example;

import com.github.javafaker.Faker;

import io.qameta.allure.Step;
import pages.LoginPage;
import pages.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.RegisterPage;

import java.time.Duration;


public class RegistrationTests {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private final Faker faker = new Faker();

    @Before
    @Step("Открытие браузера и инициализация драйвера")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        mainPage.open();
    }

    @Test
    @Step("Тест успешной регистрации пользователя")
    public void testSuccessfulRegistration() {

        registerPage.clickRegistrationButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue("Успешная регистрация", loginPage.isLoginFormDisplayed());
    }

    @Test
    @Step("Тест валидации пароля при регистрации")
    public void testShortPasswordValidation() {

        mainPage.registerUser(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password(1, 5)
        );

        String expectedError = "Некорректный пароль";
        Assert.assertEquals("Текст ошибки должен соответствовать ожидаемому", expectedError, mainPage.textErrorElement());
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        driver.quit();
    }
}