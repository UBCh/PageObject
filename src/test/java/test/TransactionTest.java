package test;

import data.DataHelper;
import data.TransactionHelper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import page.DashboardPage;
import page.LoginPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        DataHelper.DoneUser();
    }

    @Before
    public void clearCache() {
        clearBrowserCache();
    }


    @Test
    @DisplayName("transfer from account No. 2 to account No. 1")
    void shouldTransferFromAccount2ToAccount1() {
        int amaunt = 1000;
        int balanceStart = DashboardPage.getCardBalance(0);
        TransactionHelper.transaction2(amaunt);
        int expected = balanceStart + amaunt;
        int actual = DashboardPage.getCardBalance(0);
        assertEquals(expected, actual);
        TransactionHelper.initialBalance2();
    }

    @Test
    @DisplayName("transfer from account No. 1 to account No. 2")
    void shouldTransferFromAccount1ToAccount2() {
        int amaunt = 1000;
        int balanceStart = DashboardPage.getCardBalance(1);
        TransactionHelper.transaction1(amaunt);
        int expected = balanceStart + amaunt;
        int actual = DashboardPage.getCardBalance(1);
        assertEquals(expected, actual);
        TransactionHelper.initialBalance2();

    }

    @Test
    @DisplayName("transfer from account No. 1 to account No. 2,with balance=0 ")
    void shouldTransferWithBalanceZeroAccount1() {
        int amaunt = 5000;
        TransactionHelper.balansZero1();
        TransactionHelper.transaction1(amaunt);
        int expected = -amaunt;
        int actual = DashboardPage.getCardBalance(0);
        assertEquals(expected, actual);
        TransactionHelper.initialBalance2();

    }

    @Test
    @DisplayName("transfer from account No. 2 to account No. 1,with balance=0 ")
    void shouldTransferWithBalanceZeroAccount2() {
        int amaunt = 5000;
        TransactionHelper.balansZero2();
        TransactionHelper.transaction2(amaunt);
        int expected = -amaunt;
        int actual = DashboardPage.getCardBalance(1);
        assertEquals(expected, actual);
        TransactionHelper.initialBalance1();
    }

    @Test
    @DisplayName("transfer from account No. 1 to account No. 2, the number of 7 times")
    void shouldTransferNumber7TimesNo1() {
        int amaunt = 1000000;
        int expected = DashboardPage.getCardBalance(1);
        TransactionHelper.transaction1(amaunt);
        int actual = DashboardPage.getCardBalance(1);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("transfer from account No. 2 to account No. 1, the number of 7 times")
    void shouldTransferNumber7TimesNo2() {
        int amaunt = 1000000;
        int expected = DashboardPage.getCardBalance(0);
        TransactionHelper.transaction2(amaunt);
        int actual = DashboardPage.getCardBalance(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("replenishment with an empty amount fieldan account")
    void shouldTransferAmauntField() {
        $$(".button__text").get(0).click();
        $(".button_theme_alfa-on-white").click();
        $(".notification__content").shouldBe(visible).shouldHave(exactText("Ошибка! Произошла ошибка"));
    }

    @Test
    @DisplayName("replenishment from an invalid account number")
    void shouldTransferInvalidAccount() {
        int amount = 1000;
        $$(".button__text").get(1).click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue("0000000000000000000");
        $(".button_theme_alfa-on-white").click();
        $(".notification__content").shouldBe(visible).shouldHave(exactText("Ошибка! Произошла ошибка"));
    }
}
