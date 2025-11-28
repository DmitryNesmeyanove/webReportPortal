package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Epic("Управление дашбордами")
@Feature("Работа с виджетами")
public class DashboardTest extends BaseTest {
    String widgetName = "TEST_DEMO_" + System.currentTimeMillis();


    @Test
    @Story("Добавление нового виджета на дашборд")
    @DisplayName("Успешное создание виджета")
    @Owner("Дмитрий")
    @TmsLink("TM-37121")
    @Severity(SeverityLevel.NORMAL)
    @Description("""
        1. Выполнить вход в систему с валидными учётными данными.
        2. Перейти на существующий дашборд.
        3. Нажать кнопку 'Добавить виджет'.
        4. Выбрать тип виджета 'Launches table'.
        5. На шаге выбора фильтра выбрать р/к 'Demo_filter'.
        6. Ввести уникальное имя виджета.
        7. Нажать 'Добавить'.
        8. Убедиться, что появилось сообщение об успехе.
        9. Убедиться, что виджет отображается на дашборде.
        """)
    void test3_AddWidgetToDashboard(){
        loginPage.enterLogin("default")
                .enterPassword("1q2w3e")
                .clickButtonLogin();

        assertTrue(loginPage.isUserLoggedIn(),
                "Пароль или логин не верный");
        dashboardPage
                .navigateToDashboard()
                .selectAnExistingDashboard()
                .clickAddWidgetButton()
                .selectWidgetType()
                .clickNextStepButton()
                .clickRadioDemoFilter()
                .clickNextStepButton()
                .addWidgetName(widgetName)
                .clickAddButton()
                .verifySuccessMessageIsVisible()
                .verifyWidgetWithNameIsVisible(widgetName);
    }
}