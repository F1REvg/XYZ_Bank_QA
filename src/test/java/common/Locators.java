package common;

public class Locators {
    public final String CUSTOMER_LOGIN_BUTTON = "//button[@ng-click='customer()']";
    public final String CUSTOMER_NAME_DROPDOWN = "//select[@name='userSelect']";
    public final String CUSTOMER_HARRY_POTTER = "//option[text()='Harry Potter']";
    public final String BUTTON_SUBMIT = "//button[@type='submit']";
    public final String BUTTON_TRANSACTIONS = "//button[@ng-click='transactions()']";
    public final String BUTTON_DEPOSIT = "//button[@ng-click='deposit()']";
    public final String BUTTON_WITHDRAWL = "//button[@ng-click='withdrawl()']";
    public final String INPUT_AMOUNT_DEPOSIT = "//input[@ng-model='amount']";
    public final String INPUT_AMOUNT_WITHDRAWL = "//form[@ng-submit='withdrawl()']//input";
    public final String TEXT_BALANCE = "(//strong[@class='ng-binding'])[2]";
    public final String TEXT_AMOUNT_FIRST = "(//td[@class='ng-binding'])[2]";
    public final String TEXT_AMOUNT_SECOND = "(//td[@class='ng-binding'])[5]";
    public final String TEXT_DATE_TIME_FIRST = "(//td[@class='ng-binding'])[1]";
    public final String TEXT_DATE_TIME_SECOND = "(//td[@class='ng-binding'])[4]";
    public final String TEXT_TYPE_FIRST = "(//td[@class='ng-binding'])[3]";
    public final String TEXT_TYPE_SECOND = "(//td[@class='ng-binding'])[6]";
}
