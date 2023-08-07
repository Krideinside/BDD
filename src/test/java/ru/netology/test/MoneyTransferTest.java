package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferTest {

    @Test
    void shouldTransferBetweenOwnCards() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var expectedBalanceCard1 = dashboardPage.getFirstCardBalance();
        var expectedBalanceCard2 = dashboardPage.getSecondCardBalance();
        var depositPage = dashboardPage.addToCard1();
        depositPage.transfer(getSecondCardInfo(), 500);
        Assertions.assertEquals(expectedBalanceCard1 + 500, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(expectedBalanceCard2 - 500, dashboardPage.getSecondCardBalance());
        expectedBalanceCard1 = dashboardPage.getFirstCardBalance();
        expectedBalanceCard2 = dashboardPage.getSecondCardBalance();
        dashboardPage.addToCard2();
        depositPage.transfer(getFirstCardInfo(), 700);
        Assertions.assertEquals(expectedBalanceCard1 - 700, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(expectedBalanceCard2 + 700, dashboardPage.getSecondCardBalance());
    }
}
