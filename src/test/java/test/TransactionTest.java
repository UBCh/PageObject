package test;

import data.DataHelper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.*;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static data.DataHelper.*;
import static java.awt.SystemColor.info;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static page.TransactionPage.*;

public class TransactionTest {


    @Before
    public void clearCache() {
        clearBrowserCache();
    }


    @Test
    @DisplayName("transfer from account No. 1 to account No. 2")
    void shouldTransferFromAccount1ToAccount2() {
        int amount = 1;
        open("http://localhost:9999");
        LoginPage.validLogin(DataHelper.getAuthInfo());
        var autoInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(autoInfo);
        var dashboardPage = VerificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        int balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var transactionPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transactionPage.makeTransfer(String.valueOf(amount), firstCardInfo);
        var expectedBalanceSecondCard = balanceSecondCard + amount;
        var expectedBalanceFirstCard = balanceFirstCard - amount;
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    @DisplayName("transfer from account No. 2 to account No. 1")
    void shouldTransferFromAccount2ToAccount1() {
        int amount = 1;
        open("http://localhost:9999");
        LoginPage.validLogin(DataHelper.getAuthInfo());
        var autoInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(autoInfo);
        var dashboardPage = VerificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        int balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var transactionPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        sleep(5000);
        dashboardPage = transactionPage.makeTransfer(String.valueOf(amount), secondCardInfo);
        sleep(5000);
        var expectedBalanceSecondCard = balanceSecondCard - amount;
        var expectedBalanceFirstCard = balanceFirstCard + amount;
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }



    @Test
    @DisplayName("transfer  from account No. 1 to account No. 2")
    void shouldTransferFromAccount1ToAccount2_Limit() {
        int amount = 10000;
        open("http://localhost:9999");
        LoginPage.validLogin(DataHelper.getAuthInfo());
        var autoInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(autoInfo);
        var dashboardPage = VerificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        int balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var transactionPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transactionPage.makeTransfer(String.valueOf(amount), firstCardInfo);
        var expectedBalanceSecondCard = balanceSecondCard+amount;
        var expectedBalanceFirstCard = balanceFirstCard-amount;
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    @DisplayName("transfer from account No. 2 to account No. 1")
    void shouldTransferFromAccount2ToAccount1_Limit() {
        int amount = 10000;
        open("http://localhost:9999");
        LoginPage.validLogin(DataHelper.getAuthInfo());
        var autoInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(autoInfo);
        var dashboardPage = VerificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        int balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var transactionPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        sleep(5000);
        dashboardPage = transactionPage.makeTransfer(String.valueOf(amount), secondCardInfo);
        sleep(5000);
        var expectedBalanceSecondCard = balanceSecondCard - amount;
        var expectedBalanceFirstCard = balanceFirstCard + amount;
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    @DisplayName("transfer from account No. 1 to account No. 2, checking the limit")
    void shouldTransferFromAccount1ToAccount2CheckingLimit() {
        int amount = 11000;
        open("http://localhost:9999");
        LoginPage.validLogin(DataHelper.getAuthInfo());
        var autoInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(autoInfo);
        var dashboardPage = VerificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        int balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var transactionPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transactionPage.makeTransfer(String.valueOf(amount), firstCardInfo);
        TransactionPage.getError();
        sleep(5000);
        var expectedBalanceSecondCard = balanceSecondCard ;
        var expectedBalanceFirstCard = balanceFirstCard ;
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        //$(".notification__content").shouldBe(visible).shouldHave(exactText("Ошибка! Произошла ошибка"));
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);


        //dashboardPage= ErrorPage.superTransfer (String.valueOf(amount), firstCardInfo);
        //var expectedError = ErrorPage.error;
        // var actualError = dashboardPage = transactionPage.makeTransfer(String.valueOf(amount), firstCardInfo);
        //assertEquals(expectedError, actualError);
    }
    @Test
    @DisplayName("transfer from account No. 2 to account No. 1, checking the limit")
    void shouldTransferFromAccount2ToAccount1CheckingLimit() {
        int amount = 100000;
        open("http://localhost:9999");
        LoginPage.validLogin(DataHelper.getAuthInfo());
        var autoInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(autoInfo);
        var dashboardPage = VerificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        int balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var transactionPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transactionPage.makeTransfer(String.valueOf(amount), secondCardInfo);
        TransactionPage.getError();
        sleep(5000);
        var expectedBalanceSecondCard = balanceSecondCard ;
        var expectedBalanceFirstCard = balanceFirstCard ;
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
       // $(".notification__content").shouldBe(visible).shouldHave(exactText("Ошибка! Произошла ошибка"));
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);


        //dashboardPage= ErrorPage.superTransfer (String.valueOf(amount), firstCardInfo);
        //var expectedError = ErrorPage.error;
        // var actualError = dashboardPage = transactionPage.makeTransfer(String.valueOf(amount), firstCardInfo);
        //assertEquals(expectedError, actualError);
    }
}
