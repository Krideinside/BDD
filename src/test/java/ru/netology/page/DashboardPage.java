package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;

import javax.lang.model.element.Element;

import java.awt.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private SelenideElement header = $("[data-test-id=dashboard]");
    public ElementsCollection cards = $$(".list__item div");
    public ElementsCollection addButtons = $$("[data-test-id=action-deposit]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DepositPage addToCard1() {
        addButtons.first().click();
        return new DepositPage();
    }
    public DepositPage addToCard2() {
        addButtons.last().click();
        return new DepositPage();
    }

//    public void depositToCard2Button() {
//        buttons.last().click();
////        new DepositPage();


    public DashboardPage() {
        header.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

   public int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}
