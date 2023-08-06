package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.security.SecureRandom;

import static com.codeborne.selenide.Selenide.$;

public class DepositPage {
    public SelenideElement amountField = $("[data-test-id=amount] input");
    public SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement toField = $("[data-test-id=to] input");
    public SelenideElement button = $("[data-test-id='action-transfer']");

    public void clearField() {
        amountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fromField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    public DepositPage() {
        amountField.shouldBe(Condition.visible);
        fromField.shouldBe(Condition.visible);
        toField.shouldBe(Condition.visible);
    }

    //
//            public boolean from(String cardNumber) {
//        if (toField.shouldHave(Condition.text("0001")); {
//            return cardNumber = DataHelper.getCardInfo().getCard2Number();
//        } if (toField.shouldHave(Condition.text("0002"))) {
//            return String DataHelper.getCardInfo().getCard1Number();
//        }
    public DashboardPage transfer(String cardNumber, int amount) {
        clearField();
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(cardNumber);
        button.click();
        return new DashboardPage();
    }
//
//    public DashboardPage transferFrom2to1(int amount) {
//        clearField();
//        amountField.setValue(String.valueOf(amount));
//        fromField.setValue(DataHelper.getCardInfo().getCard2Number());
//        button.click();
//        return new DashboardPage();
//    }
//
//    public DashboardPage transferFrom1to2(int amount) {
//            amountField.setValue(String.valueOf(amount);
//            fromField.setValue(from);
//            button.click();
//        return new DashboardPage();
//        }
}