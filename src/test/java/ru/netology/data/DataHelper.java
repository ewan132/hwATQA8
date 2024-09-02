package ru.netology.data;


import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataHelper(){
    }
    public static AuthInfo getAuthInfoTestData(){
    return new AuthInfo("vasya", "qwerty123");
    }
    private static String genRandomLogin(){
            return FAKER.name().username();
    }
    private static String genRandomPass(){
        return FAKER.internet().password();
    }
    public static AuthInfo genRandomUser(){
        return new AuthInfo(genRandomLogin(),genRandomPass());
    }
    public static VerificationCode genRandomVerCode(){
        return new VerificationCode(FAKER.numerify("#####"));
    }
    @Value
    public static class AuthInfo{
        String login;
        String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode{
        String code;
    }

}
