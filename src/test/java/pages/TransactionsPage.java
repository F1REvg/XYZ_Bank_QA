package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MakeCSV;

import java.io.IOException;

public class TransactionsPage extends BasePage {

    public TransactionsPage (WebDriver webDriver) {
        super(webDriver);
    }

    private final String BUTTON_TRANSACTIONS = "//button[@ng-click='transactions()']";
    private final String BUTTON_DEPOSIT = "//button[@ng-click='deposit()']";
    private final String BUTTON_WITHDRAWL = "//button[@ng-click='withdrawl()']";
    private final String INPUT_AMOUNT_DEPOSIT = "//input[@ng-model='amount']";
    private final String INPUT_AMOUNT_WITHDRAWL = "//form[@ng-submit='withdrawl()']//input";
    private final String TEXT_BALANCE = "(//strong[@class='ng-binding'])[2]";
    private final String TEXT_AMOUNT_FIRST = "(//td[@class='ng-binding'])[2]";
    private final String TEXT_AMOUNT_SECOND = "(//td[@class='ng-binding'])[5]";
    private final String TEXT_DATE_TIME_FIRST = "(//td[@class='ng-binding'])[1]";
    private final String TEXT_DATE_TIME_SECOND = "(//td[@class='ng-binding'])[4]";
    private final String TEXT_TYPE_FIRST = "(//td[@class='ng-binding'])[3]";
    private final String TEXT_TYPE_SECOND = "(//td[@class='ng-binding'])[6]";

    @Step("Проверяем возможность проведения транзакций")
    public void checkTransactionsOpportunity() throws InterruptedException {
        deposit();
        withdrawl();
        checkBalance();
        Thread.sleep(5000);
        checkTransactions();
    }

    @Step("Нажимаем на кнопку пополнить счет")
    public void deposit() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_DEPOSIT))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(INPUT_AMOUNT_DEPOSIT))).sendKeys(
                String.valueOf(fibonacciByDay.getFibonacciByDay()));
        clickButtonSubmit();
    }

    @Step("Снимаем средства со счета")
    public void withdrawl() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_WITHDRAWL))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(INPUT_AMOUNT_WITHDRAWL))).sendKeys(
                String.valueOf(fibonacciByDay.getFibonacciByDay()));
        clickButtonSubmit();
    }

    @Step("Проверяем, что баланс равен нулю")
    public void checkBalance() {
        String balance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_BALANCE))).getText();
        Assertions.assertEquals(balance, "0", "Баланс равен нулю");
    }

    @Step("Нажимаем на кнопку Транзакции")
    public void checkTransactions() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_TRANSACTIONS))).click();
        String creditTransactionSum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_AMOUNT_FIRST))).getText();
        Assertions.assertEquals(Integer.parseInt(creditTransactionSum), fibonacciByDay.getFibonacciByDay());
        String debitTransactionSum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_AMOUNT_SECOND))).getText();
        Assertions.assertEquals(Integer.parseInt(debitTransactionSum), fibonacciByDay.getFibonacciByDay());
    }

    @Step("Создаем CSV файл с проведенными транзакциями")
    public void makeCSV(String path) {
        String firstDateTime = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_DATE_TIME_FIRST))).getText();
        String secondDateTime = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_DATE_TIME_SECOND))).getText();
        String firstAmount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_AMOUNT_FIRST))).getText();
        String secondAmount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_AMOUNT_SECOND))).getText();
        String firstType = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_TYPE_FIRST))).getText();
        String secondType = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TEXT_TYPE_SECOND))).getText();
        MakeCSV.writeDataAtOnce(path,
                formattedString(firstDateTime),
                firstAmount,
                firstType,
                formattedString(secondDateTime),
                secondAmount,
                secondType
        );
        String attach = firstDateTime + " " + firstAmount + " " + firstType + "\n" + secondDateTime + " " +
                secondAmount + " " + secondType;
                Allure.addAttachment("my.csv", attach);
    }

    public String formattedString(String stringToFormat) {
        return stringToFormat.substring(4, 6) + " " + stringToFormat.substring(0, 3) + stringToFormat.substring(7, 21);
    }
}
