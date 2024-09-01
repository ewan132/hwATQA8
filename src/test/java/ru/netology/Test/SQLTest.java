package ru.netology.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Data.DataHelper;
import ru.netology.Data.SQLHelper;
import ru.netology.Page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.Data.SQLHelper.cleanDatabase;

public class SQLTest {
    LoginPage loginPage;

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @BeforeEach
    void setUp(){ loginPage = open("http://localhost:9999", LoginPage.class);}

    @Test

    void shouldSuccessfulLogin() {
        var authInfo = DataHelper.getAuthInfoTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldGetErrortheUserIsNotInTheDataBase() {
        var authInfo = DataHelper.genRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotification ("Ошибка! \nНеверно указан логин или пароль");
    }

    @Test
    void shouldGetErrorNotificationIfLoginWithExistUser() {
        var authInfo = DataHelper.getAuthInfoTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisibility();
        var verificationCode = DataHelper.genRandomVerCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotification("Ошибка! \nНеверно указан код! Попробуйте ещё раз.");
    }
}
