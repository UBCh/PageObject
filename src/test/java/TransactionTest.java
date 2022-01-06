import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    TransactionHelper transactionHelper =new TransactionHelper("5559 0000 0000 0001","5559 0000 0000 0002" );

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        loginPage.DoneUser();
    }

    @Test
    @DisplayName("transfer from account No. 2 to account No. 1")
    void shouldTransferFromAccount2ToAccount1() {
       int amount =1000;
        DashboardPage dashboardPage = new DashboardPage();
        int moneyStart =dashboardPage.getCardBalance(0);
        transactionHelper.transaction1(amount);
        int expected = moneyStart + amount;
        //int expected = 0;
        int actual = dashboardPage.getCardBalance(0);
        assertEquals(expected, actual);
        transactionHelper.transaction2(amount);
    }
    @Test
    @DisplayName("transfer from account No. 1 to account No. 2")
    void shouldTransferFromAccount1ToAccount2() {
        int amount =1000;
        DashboardPage dashboardPage = new DashboardPage();
        int moneyStart =dashboardPage.getCardBalance(1);
        transactionHelper.transaction2(amount);
        int expected = moneyStart + amount;
        //int expected = 0;
        int actual = dashboardPage.getCardBalance(1);
        assertEquals(expected, actual);
        transactionHelper.transaction1(amount);
    }

    @Test
    @DisplayName("transfer from account No. 1 to account No. 2,with balance=0 ")
    void shouldTransferWithBalanceZeroAccount1() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount=1;
        int moneyStart =dashboardPage.getCardBalance(0);
       if (moneyStart >0 ) {
                transactionHelper.transaction2(moneyStart+1);}
      if (moneyStart ==0) { transactionHelper.transaction2(amount);}

      if (moneyStart<0) {
          transactionHelper.transaction1(moneyStart+amount);
          transactionHelper.transaction2(amount);
      }
        //$(".notification__content").shouldBe(visible).shouldHave(exactText("Ошибка! "));
        //transactionHelper.transaction2();
        int actual = dashboardPage.getCardBalance(0);
        assertEquals( -amount, actual);
        transactionHelper.transaction1(amount);
    }
    @Test
    @DisplayName("transfer from account No. 2 to account No. 1,with balance=0 ")
    void shouldTransferWithBalanceZeroAccount2() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount=1;
        int moneyStart =dashboardPage.getCardBalance(1);
        if (moneyStart >0 ) {
            transactionHelper.transaction1(moneyStart+1);}
        if (moneyStart ==0) { transactionHelper.transaction1(amount);}

        if (moneyStart<0) {
            transactionHelper.transaction2(moneyStart+amount);
            transactionHelper.transaction1(amount);
        }
        //$(".notification__content").shouldBe(visible).shouldHave(exactText("Ошибка! "));
        //transactionHelper.transaction1();
        int actual = dashboardPage.getCardBalance(1);
        assertEquals( -amount, actual);
        transactionHelper.transaction2(amount);
    }
}
