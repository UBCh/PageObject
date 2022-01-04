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
        TransactionHelper.DashboardPage dashboardPage = new TransactionHelper.DashboardPage();
        int moneyStart =dashboardPage.getFirstCardBalance;
        transactionHelper.transaction1();
        int expected = moneyStart + transactionHelper.amount;
        int actual = dashboardPage.getFirstCardBalance;
        assertEquals(expected, actual);

    }
}
