package data;

import com.codeborne.selenide.ElementsCollection;
import lombok.SneakyThrows;
import lombok.val;
import org.openqa.selenium.Keys;
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

    @SneakyThrows
    public static void clear(int id) {
        $$(".button__text").get(id).click();
        $("[data-test-id='amount'] input").clear();
        $("[data-test-id='from'] input").clear();
    }


    @SneakyThrows
    public static void transaction1(int amount) {
        //перевод с карты1 на карту2
        $$(".button__text").get(1).click();

        $("[data-test-id='amount'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='amount'] input").sendKeys(Keys.BACK_SPACE);

        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));

        $("[data-test-id='from'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='from'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='from'] input").setValue(LoginPage.getFirstCardInfo());

        $(".button_theme_alfa-on-white").click();


    }


    @SneakyThrows
    public static void transaction2(int amount) {
        // перевод с карты2 на карту1
        $$(".button__text").get(0).click();

        $("[data-test-id='amount'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='amount'] input").sendKeys(Keys.BACK_SPACE);

        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));

        $("[data-test-id='from'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='from'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='from'] input").setValue(LoginPage.getSecondCardInfo());

        $(".button_theme_alfa-on-white").click();

        //$(withText("Ваши карты")).shouldBe(visible);

    }

    public static void initialBalance1() {
        // восстановление первоначального баланса на карте1

        int moneyСurrent = DashboardPage.getCardBalance(0);
        if (moneyСurrent > 0 || moneyСurrent == 0) {
            int intermediate = LoginPage.balanceFirst() - moneyСurrent;
            if (intermediate > 0) {
                transaction2(intermediate);
            }
            if (intermediate < 0) {
                transaction1(intermediate);
            }
        }
        if (moneyСurrent < 0) {
            transaction2(moneyСurrent);
            clear(0);
            transaction2(LoginPage.balanceSecond());
        }
    }

    public static void initialBalance2() {
        // восстановление первоначального баланса на карте2

        int moneyСurrent = DashboardPage.getCardBalance(1);
        if (moneyСurrent > 0 || moneyСurrent == 0) {
            int intermediate = LoginPage.balanceSecond() - moneyСurrent;
            if (intermediate > 0) {
                transaction1(intermediate);
            }

            if (intermediate < 0) {
                transaction2(intermediate);
            }
        }
        if (moneyСurrent < 0) {
            transaction1(moneyСurrent);
            transaction1(LoginPage.balanceFirst());
        }
    }

    public static void balansZero1() {
        // приведение баланса к нулю 1 карта
        int moneyСurrent = DashboardPage.getCardBalance(0);
        if (moneyСurrent > 0) {
            transaction1(moneyСurrent);
        } else transaction2(moneyСurrent);
    }

    public static void balansZero2() {
        // приведение баланса к нулю 2 карта
        int moneyСurrent = DashboardPage.getCardBalance(1);
        if (moneyСurrent > 0) {
            transaction2(moneyСurrent);
        } else transaction1(moneyСurrent);

    }
}
