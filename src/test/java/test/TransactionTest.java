package test;

import data.DataHelper;
import data.TransactionHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    LoginPage loginPage = new LoginPage(
            "vasya",
            "qwerty123",
            "12345",
            1000,
            "5559 0000 0000 0001",
            "5559 0000 0000 0002",
            10000,
            1000);
    TransactionHelper transactionHelper = new TransactionHelper();


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        DataHelper.DoneUser();
    }

    @Test
    @DisplayName("transfer from account No. 2 to account No. 1")
    void shouldTransferFromAccount2ToAccount1() {
        DashboardPage dashboardPage = new DashboardPage();
        transactionHelper.initialBalance1();
        int balanceStart = dashboardPage.getCardBalance(0);
        transactionHelper.transaction1(loginPage.getAmount());
        int expected = balanceStart + loginPage.getAmount();
        int actual = dashboardPage.getCardBalance(0);
        assertEquals(expected, actual);
        transactionHelper.initialBalance1();
    }

    @Test
    @DisplayName("transfer from account No. 1 to account No. 2")
    void shouldTransferFromAccount1ToAccount2() {
        DashboardPage dashboardPage = new DashboardPage();
        transactionHelper.initialBalance2();
        int balanceStart = dashboardPage.getCardBalance(1);
        transactionHelper.transaction1(loginPage.getAmount());
        int expected = balanceStart + loginPage.getAmount();
        int actual = dashboardPage.getCardBalance(1);
        assertEquals(expected, actual);
        transactionHelper.initialBalance2();
    }

    @Test
    @DisplayName("transfer from account No. 1 to account No. 2,with balance=0 ")
    void shouldTransferWithBalanceZeroAccount1() {
        DashboardPage dashboardPage = new DashboardPage();
        transactionHelper.balansZero1();
        transactionHelper.transaction1(loginPage.getAmount());
        int expected = -loginPage.getAmount();
        int actual = dashboardPage.getCardBalance(0);
        assertEquals(expected, actual);
        transactionHelper.initialBalance1();

    }

    @Test
    @DisplayName("transfer from account No. 2 to account No. 1,with balance=0 ")
    void shouldTransferWithBalanceZeroAccount2() {
        DashboardPage dashboardPage = new DashboardPage();
        transactionHelper.balansZero2();
        transactionHelper.transaction2(loginPage.getAmount());
        int expected = -loginPage.getAmount();
        int actual = dashboardPage.getCardBalance(1);
        assertEquals(expected, actual);
        transactionHelper.initialBalance1();
    }
}
