package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private static final By LOGIN_ID = By.xpath("//input[@name = 'login']");
    private static final By PASSWORD_ID = By.xpath("//input[@name = 'password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type = 'submit']");
    protected static final By MESSAGE_LOGIN_ERROR = By.xpath("//h2[@class = '_title_14lm6_32']");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Заполнить поле 'Login' значением")
    public LoginPage enterLogin(String login) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_ID)).clear();
        driver.findElement(LOGIN_ID).sendKeys(login);
        return this;
    }
    @Step("Заполнить поле 'PASSWORD' значением")
    public LoginPage enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ID)).clear();
        driver.findElement(PASSWORD_ID).sendKeys(password);
        return this;
    }
    @Step("Произвести клик по кнопку 'Login' для авторизации")
    public LoginPage clickButtonLogin(){
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }
    @Step("Проверить, что пользователь авторизован")
    public boolean isUserLoggedIn() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class, 'actionPanel__action-panel')]")
            ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Проверить, что отображается сообщение об ошибке входа")
    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_LOGIN_ERROR)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
