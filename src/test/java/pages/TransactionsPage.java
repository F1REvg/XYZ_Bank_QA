package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.MakeCSV;

public class TransactionsPage extends BasePage {

    public TransactionsPage (WebDriver webDriver) {
        super(webDriver);
    }

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
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.BUTTON_DEPOSIT))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.INPUT_AMOUNT_DEPOSIT))).sendKeys(
                String.valueOf(fibonacciByDay.getFibonacciByDay()));
        clickButtonSubmit();
    }

    @Step("Снимаем средства со счета")
    public void withdrawl() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.BUTTON_WITHDRAWL))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.INPUT_AMOUNT_WITHDRAWL))).sendKeys(
                String.valueOf(fibonacciByDay.getFibonacciByDay()));
        clickButtonSubmit();
    }

    @Step("Проверяем, что баланс равен нулю")
    public void checkBalance() {
        String balance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_BALANCE))).getText();
        Assertions.assertEquals(balance, "0", "Баланс равен нулю");
    }

    @Step("Нажимаем на кнопку Транзакции")
    public void checkTransactions() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.BUTTON_TRANSACTIONS))).click();
        String creditTransactionSum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_AMOUNT_FIRST))).getText();
        Assertions.assertEquals(Integer.parseInt(creditTransactionSum), fibonacciByDay.getFibonacciByDay());
        String debitTransactionSum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_AMOUNT_SECOND))).getText();
        Assertions.assertEquals(Integer.parseInt(debitTransactionSum), fibonacciByDay.getFibonacciByDay());
    }

    @Step("Создаем CSV файл с проведенными транзакциями")
    public void makeCSV() {
        String firstDateTime = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_DATE_TIME_FIRST))).getText();
        String secondDateTime = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_DATE_TIME_SECOND))).getText();
        String firstAmount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_AMOUNT_FIRST))).getText();
        String secondAmount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_AMOUNT_SECOND))).getText();
        String firstType = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_TYPE_FIRST))).getText();
        String secondType = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.TEXT_TYPE_SECOND))).getText();
        MakeCSV.writeDataAtOnce("D:/IdeaProjects/XYZ_Bank_QA/csv/my.csv",
                formattedString(firstDateTime),
                firstAmount,
                firstType,
                formattedString(secondDateTime),
                secondAmount,
                secondType
        );
    }

    public String formattedString(String stringToFormat) {
        return stringToFormat.substring(4, 6) + " " + stringToFormat.substring(0, 3) + stringToFormat.substring(7, 21);
    }
}
