package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
@Epic("Авторизация на портале 'reportPortal'")
class LoginTests extends BaseTest {

    @Test
    @Story("Авторизация с невалидным логином")
    @DisplayName("Вход в систему с некорректным логином и невалидным паролем")
    @Owner("Dmitry")
    @TmsLink("TM_37120")
    @Severity(SeverityLevel.NORMAL)
    @Description("""
    1. Открыть страницу авторизации.
    2. В поле «Логин» ввести некорректное значение.
    3. В поле «Пароль» ввести некорректное значение.
    4. Нажать кнопку «Войти».
    5. Убедиться, что вход в систему не выполнен.
    6. Убедиться, что отображается сообщение об ошибке.
    """)
    public void testNegative_logInWithAnIncorrectUsername() {
        loginPage.enterLogin("invalidUser")
                .enterPassword("wrongPass")
                .clickButtonLogin();

        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Сообщение об ошибке не отображается");
    }

    @Test
    @Story("Успешная авторизация с валидными учётными данными")
    @DisplayName("Вход в систему с корректным логином и паролем")
    @Owner("Dmitry")
    @TmsLink("TM_37119")
    @Severity(SeverityLevel.CRITICAL)
    @Description("""
        1. Открыть страницу авторизации.
        2. Ввести валидные логин и пароль.
        3. Нажать кнопку входа.
        4. Убедиться, что пользователь успешно авторизован.
        """)
    public void testPositive_LoginWithDefaultUser() {
        loginPage.enterLogin("default")
                .enterPassword("1q2w3e")
                .clickButtonLogin();

        assertTrue(loginPage.isUserLoggedIn(),
                "Пароль или логин не верный");
    }
}
