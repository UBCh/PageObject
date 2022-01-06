

import com.codeborne.selenide.ElementsCollection;
import lombok.SneakyThrows;
import lombok.val;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Thread.sleep;

public class TransactionHelper {

    int amount;
    String score1;
    String score2;


    public TransactionHelper(int amount, String score1, String score2) {
        this.amount = amount;
        this.score1 = score1;
        this.score2 = score2;
    }

    public TransactionHelper() {
    }


    public void transaction1() {
        // DashboardPage dashboardPage = new DashboardPage();
        //loginPage.DoneUser();

        //$$(".list__item").find(exactText(score1)).click();
        //$(".button__text").click();
        $$(".button__text").get(0).click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue(score2);
        $(".button_theme_alfa-on-white").click();
        $(withText("Ваши карты")).shouldBe(visible);

    }

    public void transaction2() {
        //loginPage.DoneUser();
        //$$(".list__item").find(exactText(score2)).click();
        // $(".button__text").click();
        $$(".button__text").get(1).click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue(score1);
        $(".button_theme_alfa-on-white").click();
        $(withText("Ваши карты")).shouldBe(visible);


    }

}
