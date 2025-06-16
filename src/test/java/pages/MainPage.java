package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MainPage {

    LoginPage loginPage;
    RegisterPage registerPage;

    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.loginPage = new LoginPage(driver);
        this.registerPage = new RegisterPage(driver);
    }

    private final By personalAccountButton = By.xpath("//*[text()='Личный Кабинет']");
    private final By loginToPersonalAccountButton = By.xpath("//*[text()='Войти в аккаунт']");
    private final By makeOrderButton = By.xpath("//*[text()='Оформить заказ']");
    private final By siteTitle = By.xpath("//h1[text()='Соберите бургер']");
    private final By bunTab = By.xpath("//span[@class='text text_type_main-default' and text()='Булки']");
    private final By saucesTab = By.xpath("//span[@class='text text_type_main-default' and text()='Соусы']");
    private final By fillingTab = By.xpath("//span[@class='text text_type_main-default' and text()='Начинки']");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span");

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(URL);
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickLoginToPersonalAccountButton() {
        driver.findElement(loginToPersonalAccountButton).click();
    }

    public String textErrorElement() {
        return registerPage.textErrorElement();
    }

    @Step("Нажатие на кнопку 'Оформить заказ'")
    public void clickMakeOrderButton() {
        driver.findElement(makeOrderButton).click();
    }

    public boolean isMakeOrderButtonDisplayed() {
        return driver.findElement(makeOrderButton).isDisplayed();
    }

    public boolean isSiteTitleDisplayed() {
        return driver.findElement(siteTitle).isDisplayed();
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunTab() {
        driver.findElement(bunTab).click();
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingTab() {
        driver.findElement(fillingTab).click();
    }

    public Object getTextActiveTab() {
        return driver.findElement(activeTab).getText();
    }

    @Step("Регистрация пользователя: имя = {0}, email = {1}")
    public void registerUser(String name, String email, String password) {
        clickPersonalAccountButton();
        loginPage.clickRegistrationLinkInFooter();
        registerPage.enterNameRegistration(name);
        registerPage.enterEmailRegistration(email);
        registerPage.enterPasswordRegistration(password);
        registerPage.clickRegistrationButton();
    }
}