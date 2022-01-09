package data;

import com.codeborne.selenide.ElementsCollection;
import lombok.SneakyThrows;
import lombok.val;
import page.DashboardPage;
import page.LoginPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Thread.sleep;

public class TransactionHelper {


    public TransactionHelper() {
    }

    public void transaction1(int amount) {
        //перевод с карты1 на карту2
        LoginPage loginPage = new LoginPage();
        $$(".button__text").get(1).click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(loginPage.getAmount()));
        $("[data-test-id='from'] input").setValue(loginPage.getScore1());
        $(".button_theme_alfa-on-white").click();
        //$(withText("Ваши карты")).shouldBe(visible);
    }


    public void transaction2(int amount) {
        // перевод с карты2 на карту1
        LoginPage loginPage = new LoginPage();
        $$(".button__text").get(0).click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(loginPage.getAmount()));
        $("[data-test-id='from'] input").setValue(loginPage.getScore2());
        $(".button_theme_alfa-on-white").click();
        //$(withText("Ваши карты")).shouldBe(visible);

    }

    public void initialBalance1() {
        // восстановление первоначального баланса на карте1
        DashboardPage dashboardPage = new DashboardPage();
        LoginPage loginPage = new LoginPage();
        int moneyСurrent = dashboardPage.getCardBalance(0);
        if (moneyСurrent > 0 || moneyСurrent == 0) {
            int intermediate = loginPage.getBalanse1() - moneyСurrent;
            if (intermediate > 0) {
                transaction2(intermediate);
            }
            if (intermediate == 0) {
                transaction2(intermediate);
            }
            if (intermediate < 0) {
                transaction1(intermediate);
            }
        }
        if (moneyСurrent < 0) {
            transaction2(moneyСurrent);
            transaction2(loginPage.getBalanse1());
        }
    }

    public void initialBalance2() {
        // восстановление первоначального баланса на карте2
        DashboardPage dashboardPage = new DashboardPage();
        LoginPage loginPage = new LoginPage();
        int moneyСurrent = dashboardPage.getCardBalance(1);
        if (moneyСurrent > 0 || moneyСurrent == 0) {
            int intermediate = loginPage.getBalanse2() - moneyСurrent;
            if (intermediate > 0) {
                transaction1(intermediate);
            }
            if (intermediate == 0) {
                transaction1(intermediate);
            }
            if (intermediate < 0) {
                transaction2(intermediate);
            }
        }
        if (moneyСurrent < 0) {
            transaction1(moneyСurrent);
            transaction1(loginPage.getBalanse1());
        }
    }

    public void balansZero1() {
        // приведение баланса к нулю 1 карта
        DashboardPage dashboardPage = new DashboardPage();
        LoginPage loginPage = new LoginPage();
        int moneyСurrent = dashboardPage.getCardBalance(0);
        transaction1(moneyСurrent);
    }

    public void balansZero2() {
        // приведение баланса к нулю 2 карта
        DashboardPage dashboardPage = new DashboardPage();
        LoginPage loginPage = new LoginPage();
        int moneyСurrent = dashboardPage.getCardBalance(1);
        transaction2(moneyСurrent);
    }
}
