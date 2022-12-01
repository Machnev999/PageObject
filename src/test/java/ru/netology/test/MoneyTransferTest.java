package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    @BeforeEach
    public void setUp() {
        //открываем сайт
        open("http://localhost:9999/");
    }

    //пополнение первой карты (сумма не больше баланса 2 карты). ПОЗИТИВНЫЙ СЦЕНАРИЙ
    @Test
    void shouldFormSentSuccessfully() {
        var loginPage = new LoginPage();
        int sumReplenishment = 1000;
        //переменная для хранения данных для авторизации
        var authInfo = DataHelper.getAuthorizationInfo();
        //логинимся
        var verificationPage = loginPage.validLogin(authInfo);
        //вытаскиваем код верификации
        var verificationCode = DataHelper.getVerificationCode();
        //переходим на страницу дашборд
        var dashboardPage = verificationPage.validVerify(verificationCode);
        //вытаскиваем номер карты с которой списываем
        var card2 = DataHelper.getSecondCard().getNumber();
        //определяем баланс на 2 карте, до списания
        var beforeBalance2 = dashboardPage.getCardBalance(1);
        //определяем баланс на 1 карте, до пополнения
        var beforeBalance1 = dashboardPage.getCardBalance(0);
        //считаем ожидаемый баланс 2 карты после списания
        int expectedBalanceCard2 = beforeBalance2 - sumReplenishment;
        //считаем ожидаемый баланс 1 карты после пополнения
        int expectedBalanceCard1 = beforeBalance1 + sumReplenishment;
        //переходим на страницу пополнения 1 карты
        var cardReplenishmentPage = dashboardPage.amountCards(0);

        //выполняем перевод
        var actualBalance = cardReplenishmentPage.card1Replenishment(card2, String.valueOf(sumReplenishment));
        //определяем баланс 1 карты после пополнени
        var actualBalance1 = actualBalance.getCardBalance(0);
        //определяем баланс 2 карты после списания
        var actualBalance2 = actualBalance.getCardBalance(1);

        //сравниваем ожидаемый и фактический результат
        assertEquals(expectedBalanceCard2, actualBalance2);
        assertEquals(expectedBalanceCard1, actualBalance1);
    }

    //пополнение первой карты (сумма больше баланса 2 карты)
    @Test
    void shouldFormSentSuccessfully40000() {
        var loginPage = new LoginPage();
        int sumReplenishment = 40000;
        //переменная для хранения данных для авторизации
        var authInfo = DataHelper.getAuthorizationInfo();
        //логинимся
        var verificationPage = loginPage.validLogin(authInfo);
        //вытаскиваем код верификации
        var verificationCode = DataHelper.getVerificationCode();
        //переходим на страницу дашборд
        var dashboardPage = verificationPage.validVerify(verificationCode);
        //вытаскиваем номер карты с которой списываем
        var card2 = DataHelper.getSecondCard().getNumber();
        //определяем баланс на 2 карте (баланс до и после списания не меняется,так как сумма перевода больше баланса на карте)
        var expectedBalanceCard2 = dashboardPage.getCardBalance(1);
        //определяем баланс на 1 карте, (баланс до и после списания не меняется,так как сумма перевода больше баланса на карте)
        var expectedBalanceCard1 = dashboardPage.getCardBalance(0);
        //переходим на страницу пополнения 1 карты
        var cardReplenishmentPage = dashboardPage.amountCards(0);

        //выполняем перевод
        var actualBalance = cardReplenishmentPage.card1Replenishment(card2, String.valueOf(sumReplenishment));
        //определяем баланс 1 карты после пополнени
        var actualBalance1 = actualBalance.getCardBalance(0);
        //определяем баланс 2 карты после списания
        var actualBalance2 = actualBalance.getCardBalance(1);

        //сравниваем ожидаемый и фактический результат
        assertEquals(expectedBalanceCard2, actualBalance2);
        assertEquals(expectedBalanceCard1, actualBalance1);
    }
}