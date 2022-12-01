package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

//страница, где вводим код
public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        //проверяем - видно поле на странице или нет
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        //заполяем поле с кодом
        codeField.setValue(verificationCode.getCode());
        //кликаем по кнопке продолжить
        verifButton.click();
        //возвращаем страницу DashboardPage
        return new DashboardPage();

    }


}