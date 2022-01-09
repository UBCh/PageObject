package data;

import com.codeborne.selenide.Selenide;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DataHelper {

    public DataHelper() {
    }

    public static void DoneUser(){
        LoginPage loginPage= new LoginPage();
        open("http://localhost:9999");
        $("[data-test-id='login'] input").setValue(loginPage.getLogin());
        $("[data-test-id='password'] input").setValue(loginPage.getPassword());
        $(".button__text").click();
        $("[data-test-id='code'] input").setValue(loginPage.getHardcoded());
        $(".button__text").click();
    }
}
