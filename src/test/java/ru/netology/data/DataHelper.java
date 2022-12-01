package ru.netology.data;

import lombok.Value;

//класс, который отдаёт данные для авторизации
public class DataHelper {
    private DataHelper() {
    }

    @Value
    //данные для авторизации (логин и пароль)
    public static class AuthorizationInfo {
        private String login;
        private String password;

    }

    public static AuthorizationInfo getAuthorizationInfo() {
        return new AuthorizationInfo("vasya", "qwerty123");
    }

    @Value
    //данные для кода
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    //карты пользователя
    public static class CardsUser {
        private String number;
    }

    public static CardsUser getFirstCard() {
        return new CardsUser("5559 0000 0000 0001");
    }

    public static CardsUser
    getSecondCard() {
        return new CardsUser("5559 0000 0000 0002");
    }

}