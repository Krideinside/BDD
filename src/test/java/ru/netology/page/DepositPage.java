package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;
import static com.codeborne.selenide.Selenide.$;

public class DepositPage {
    public SelenideElement amountField = $("[data-test-id=amount] input");
    public SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement toField = $("[data-test-id=to]");
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

    public DashboardPage transfer(DataHelper.CardInfo cardInfo, int amount) {
        clearField();
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(cardInfo.getCardNumber());
        button.click();
        return new DashboardPage();
    }
}