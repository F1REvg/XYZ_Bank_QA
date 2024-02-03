package pages;

import common.ConfProperties;
import io.qameta.allure.Allure;
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

    private final By BUTTON_TRANSACTIONS = By.xpath("//button[@ng-click='transactions()']");
    private final By BUTTON_DEPOSIT = By.xpath("//button[@ng-click='deposit()']");
    private final By BUTTON_WITHDRAWL = By.xpath("//button[@ng-click='withdrawl()']");
    private final By INPUT_AMOUNT_DEPOSIT = By.xpath("//input[@ng-model='amount']");
    private final By INPUT_AMOUNT_WITHDRAWL = By.xpath("//form[@ng-submit='withdrawl()']//input");
    private final By TEXT_BALANCE = By.xpath("(//strong[@class='ng-binding'])[2]");
    private final By TEXT_AMOUNT_FIRST = By.xpath("(//td[@class='ng-binding'])[2]");
    private final By TEXT_AMOUNT_SECOND = By.xpath("(//td[@class='ng-binding'])[5]");
    private final By TEXT_DATE_TIME_FIRST = By.xpath("(//td[@class='ng-binding'])[1]");
    private final By TEXT_DATE_TIME_SECOND = By.xpath("(//td[@class='ng-binding'])[4]");
    private final By TEXT_TYPE_FIRST = By.xpath("(//td[@class='ng-binding'])[3]");
    private final By TEXT_TYPE_SECOND = By.xpath("(//td[@class='ng-binding'])[6]");

    @Step("Проверяем наличие проведенных транзакций и создаем из них csv файл")
    public void checkTransactionsOpportunity() throws InterruptedException {
        Thread.sleep(3000);
        checkTransactions();
        makeCSV(ConfProperties.getProperty("csv_path"));
    }

    @Step("Вычисляем N-е число Фибоначчи, где N - это текущий день месяца + 1")
    private String getFibonacciByDay() {
        return String.valueOf(fibonacciByDay.getFibonacciByDay());
    }

    @Step("Нажимаем на кнопку пополнить счет")
    public void deposit() {
        elementToBeClickable(BUTTON_DEPOSIT).click();
        presenceOfElement(INPUT_AMOUNT_DEPOSIT).sendKeys(getFibonacciByDay());
        clickButtonSubmit();
    }

    @Step("Снимаем средства со счета")
    public void withdrawl() {
        elementToBeClickable(BUTTON_WITHDRAWL).click();
        presenceOfElement(INPUT_AMOUNT_WITHDRAWL).sendKeys(getFibonacciByDay());
        clickButtonSubmit();
    }

    @Step("Проверяем, что баланс равен нулю")
    public void checkBalance() {
        String balance = presenceOfElement(TEXT_BALANCE).getText();
        Assertions.assertEquals(balance, "0", "Баланс равен нулю");
    }

    @Step("Нажимаем на кнопку Транзакции")
    public void checkTransactions() {
        elementToBeClickable(BUTTON_TRANSACTIONS).click();
        String creditTransactionSum = presenceOfElement(TEXT_AMOUNT_FIRST).getText();
        Assertions.assertEquals(Integer.parseInt(creditTransactionSum), fibonacciByDay.getFibonacciByDay());
        String debitTransactionSum = presenceOfElement(TEXT_AMOUNT_SECOND).getText();
        Assertions.assertEquals(Integer.parseInt(debitTransactionSum), fibonacciByDay.getFibonacciByDay());
    }

    @Step("Создаем CSV файл с проведенными транзакциями")
    public void makeCSV(String path) {
        String firstDateTime = presenceOfElement(TEXT_DATE_TIME_FIRST).getText();
        String secondDateTime = presenceOfElement(TEXT_DATE_TIME_SECOND).getText();
        String firstAmount = presenceOfElement(TEXT_AMOUNT_FIRST).getText();
        String secondAmount = presenceOfElement(TEXT_AMOUNT_SECOND).getText();
        String firstType = presenceOfElement(TEXT_TYPE_FIRST).getText();
        String secondType = presenceOfElement(TEXT_TYPE_SECOND).getText();
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

    private String formattedString(String stringToFormat) {
        return stringToFormat.substring(4, 6) + " " + stringToFormat.substring(0, 3) + stringToFormat.substring(7, 21);
    }
}
