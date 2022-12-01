package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

//страница авторизации
public class LoginPage {
    //логин
    private SelenideElement loginField = $("[data-test-id=login] input");
    //пароль
    private SelenideElement passwordField = $("[data-test-id=password] input");
    //кнопка продолжить
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    //метод для валидного логина. берёт информацию из класса AuthorizationData
    public VerificationPage validLogin(DataHelper.AuthorizationInfo authoInfo) {
        //вводим логин
        loginField.setValue(authoInfo.getLogin());
        //водим пароль
        passwordField.setValue(authoInfo.getPassword());
        //кликаем кнопку продолжить
        loginButton.click();
        //возвращаем  VerificationPage, так как полсе нажатия на кнопки продолжить мы попадаем на страницу ввода кода
        return new VerificationPage();
    }

}