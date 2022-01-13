package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.SneakyThrows;
import lombok.Value;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.Thread.sleep;

public class LoginPage {

    private LoginPage() {
    }

    private static SelenideElement login = $("[data-test-id='login'] input");
    private static SelenideElement password = $("[data-test-id='password'] input");
    private static SelenideElement button = $(".button__text");

    public static VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }

}

