package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldTransferBetweenOwnCards() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var depositPage = dashboardPage.addToCard1();
        dashboardPage = depositPage.transferFrom1to2(500);
        Assertions.assertEquals(9_500, dashboardPage.getFirstCardBalance() - 500);
        Assertions.assertEquals(10_500, dashboardPage.getSecondCardBalance() + 500);
       depositPage = dashboardPage.addToCard2();
        depositPage.transferFrom2to1(700);
        Assertions.assertEquals(10_700, dashboardPage.getFirstCardBalance() + 700);
        Assertions.assertEquals(9_300, dashboardPage.getSecondCardBalance() - 700);
    }
}
