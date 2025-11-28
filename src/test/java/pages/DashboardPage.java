package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    private static final By DASHBOARD_MENU = By.xpath("//a[contains(@href, 'dashboard')]");
    private static final By EXISTING_DASHBOARD = By.xpath("//a[contains(@class, 'dashboardTable__name')]");
    private static final By ADD_NEW_WIDGET = By.xpath("//span[text()='Add new widget']");
    private static final By ADD_WIDGET_LAUNCHES_TABLE = By.xpath("//div[text()='Launches table']");
    private static final By BUTTON_NEXT_STEP = By.xpath("//span[text()='Next step']");
    private static final By DEMO_FILTER = By.xpath("//div[contains(@class, 'filtersItem__filter-item')]");
    private static final By CREATE_WIDGET_NAME = By.xpath("//input[@placeholder='Enter widget name']"); // или другой стабильный локатор
    private static final By ADD_BUTTON = By.xpath("//button[text()='Add']");
    private static final By DASHBOARD_HEADER = By.xpath("//span[@title='All Dashboards']");
    private static final By SUCCESS_MESSAGE = By.xpath("//div[contains(@class, 'system-alert') and contains(@class, 'success')]");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Перейти в раздел Dashboard")
    public DashboardPage navigateToDashboard() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(DASHBOARD_MENU)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(DASHBOARD_HEADER));
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось перейти в раздел Dashboard", e);
        }
        return this;
    }

    @Step("Выбрать существующий Dashboard")
    public DashboardPage selectAnExistingDashboard() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(EXISTING_DASHBOARD)).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось перейти в существующий Dashboard", e);
        }
        return this;
    }

    @Step("Нажать кнопку 'Add Widget'")
    public DashboardPage clickAddWidgetButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_WIDGET)).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось найти кнопку Add Widget", e);
        }
        return this;
    }

    @Step("Выбрать тип виджета 'Launches table'")
    public DashboardPage selectWidgetType() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_WIDGET_LAUNCHES_TABLE)).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось выбрать тип виджета 'Launches table'", e);
        }
        return this;
    }

    @Step("Нажать кнопку 'Next step'")
    public DashboardPage clickNextStepButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(BUTTON_NEXT_STEP)).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось нажать кнопку 'Next step'", e);
        }
        return this;
    }

    @Step("Выбрать фильтр 'Demo filter'")
    public DashboardPage clickRadioDemoFilter() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(DEMO_FILTER)).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось выбрать фильтр 'Demo filter'", e);
        }
        return this;
    }

    @Step("Ввести название виджета '{widgetName}'")
    public DashboardPage addWidgetName(String widgetName) {
        try {
            WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_WIDGET_NAME));
            nameInput.clear();
            nameInput.sendKeys(widgetName);
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось найти поле ввода 'Widget name'", e);
        }
        return this;
    }

    @Step("Нажать кнопку 'Add'")
    public DashboardPage clickAddButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_BUTTON)).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Не удалось найти кнопку 'Add'", e);
        }
        return this;
    }

    @Step("Проверить, что отображается сообщение об успешном выполнении")
    public DashboardPage verifySuccessMessageIsVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
        } catch (TimeoutException e) {
            throw new AssertionError("Сообщение об успешном выполнении не появилось", e);
        }
        return this;
    }

    @Step("Проверить, что виджет с названием '{widgetName}' отображается на Dashboard")
    public DashboardPage verifyWidgetWithNameIsVisible(String widgetName) {
        By widgetLocator = By.xpath("//div[text() = '" + widgetName + "']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(widgetLocator));
        } catch (TimeoutException e) {
            throw new AssertionError("Виджет с названием '" + widgetName + "' не отображается на Dashboard", e);
        }
        return this;
    }
}
