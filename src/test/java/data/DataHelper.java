package data;

import lombok.SneakyThrows;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.Thread.sleep;

public class DataHelper {

    public DataHelper() {
    }

       @SneakyThrows
       public static void DoneUser(){

        open("http://localhost:9999");
        $("[data-test-id='login'] input").setValue(LoginPage.getLogin());
        $("[data-test-id='password'] input").setValue(LoginPage.getPassword());
        $(".button__text").click();
        $("[data-test-id='code'] input").setValue(LoginPage.getVerificationCodeFor());
        $(".button__text").click();
           sleep(5000);
    }
}
