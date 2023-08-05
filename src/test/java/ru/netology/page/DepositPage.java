package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class DepositPage {
    public SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement toField = $("[data-test-id=to] input");
    private String card1Number = "5559 0000 0000 0001";
    private String card2Number = "5559 0000 0000 0002";

    public String getCard1Number() {
        return card1Number;
    }

    public String getCard2Number() {
        return card2Number;
    }

    public void clearField() {
        amountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fromField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    public DepositPage() {
        amountField.shouldBe(Condition.visible);
        fromField.shouldBe(Condition.visible);
        toField.shouldBe(Condition.visible);
    }

    public DashboardPage depositFrom2to1(int amount) {
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(card2Number);
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

    public DashboardPage depositFrom1to2(int amount) {
            amountField.setValue(String.valueOf(amount));
            fromField.setValue(card1Number);
            $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
        }
    }