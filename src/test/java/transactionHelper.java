

import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class transactionHelper {

      String amount;
      String from;

      public transactionHelper(String amount, String from) {
            this.amount = amount;
            this.from = from;
      }
    public class DashboardPage {
        private ElementsCollection cards = $$(".list__item");
        private final String balanceStart = "баланс: ";
        private final String balanceFinish = " р.";

        public DashboardPage() {
        }
        public int getFirstCardBalance() {
            val text = cards.first().text();
            return extractBalance(text);
        }

        private int extractBalance(String text) {
            val start = text.indexOf(balanceStart);
            val finish = text.indexOf(balanceFinish);
            val value = text.substring(start + balanceStart.length(), finish);
            return Integer.parseInt(value);
        }
    }


           private void Transaction1(){
             loginPage.DoneUser();
            $(".button__text").click();
            $("[data-test-id='amount'] input").setValue(amount);
            $("[data-test-id='from'] input").setValue(from);
            $(".button_theme_alfa-on-white").click();
            $(withText("Ваши карты")).shouldBe(visible);}

               private void Transaction2(){
                   loginPage.DoneUser();
                   $(".button__text").click();
                   $("[data-test-id='amount'] input").setValue(amount);
                   $("[data-test-id='from'] input").setValue(from);
                   $(".button_theme_alfa-on-white").click();
                   $(withText("Ваши карты")).shouldBe(visible);



      }
    
}
