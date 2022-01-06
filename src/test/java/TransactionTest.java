import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    TransactionHelper transactionHelper =new TransactionHelper(1000,"5559 0000 0000 0001","5559 0000 0000 0002" );

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        loginPage.DoneUser();
    }

    @Test
    @DisplayName("transfer from account No. 1 to account No. 2")
    void shouldTransferFromAccount1ToAccount2() {
        DashboardPage dashboardPage = new DashboardPage();
        int moneyStart =dashboardPage.getCardBalance(0);
        transactionHelper.transaction1();
        int expected = moneyStart + transactionHelper.amount;
        //int expected = 0;
        int actual = dashboardPage.getCardBalance(0);
        assertEquals(expected, actual);

    }
    @Test
    @DisplayName("transfer from account No. 2 to account No. 1")
    void shouldTransferFromAccount2ToAccount1() {
        DashboardPage dashboardPage = new DashboardPage();
        int moneyStart =dashboardPage.getCardBalance(1);
        transactionHelper.transaction2();
        int expected = moneyStart + transactionHelper.amount;
        //int expected = 0;
        int actual = dashboardPage.getCardBalance(1);
        assertEquals(expected, actual);

    }
}
