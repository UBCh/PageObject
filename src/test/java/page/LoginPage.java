package page;

import lombok.Value;

public class LoginPage {

    //int amount;

    private  LoginPage() {
    }
    public static String getLogin() {
        return "vasya";
    }
    public static String getPassword(){
        return "qwerty123";
    }

    public static String getVerificationCodeFor() {
        return "12345";
    }

    public static String getFirstCardInfo() {
        return "5559000000000001";
    }

    public static String getSecondCardInfo() {
        return "5559000000000002";
    }
    public static int balanceFirst(){
        return 10000;
    }
    public static int balanceSecond(){
        return 10000;
    }
    @Value
    public static class Login {
        String login;
        }

    @Value
       public static class Password {
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
    }
   @Value
    public static class BalanceCard {
        int balanceFirst;
        int balanceSecond;}
   }

