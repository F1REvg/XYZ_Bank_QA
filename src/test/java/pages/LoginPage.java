package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    private final String CUSTOMER_LOGIN_BUTTON = "//button[@ng-click='customer()']";
    private final String CUSTOMER_NAME_DROPDOWN = "//select[@name='userSelect']";
    private final String CUSTOMER_HARRY_POTTER = "//option[text()='Harry Potter']";

    @Step("Входим в аккаунт под пользователем Harry Potter")
    public void loginAsHarryPotter() {
        clickCustomerLoginButton();
        setHarryPotterAsUser();
        clickButtonSubmit();
    }

    @Step("Нажимаем на кнопку Customer Login")
    public void clickCustomerLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CUSTOMER_LOGIN_BUTTON))).click();
    }

    @Step("Выбираем пользователя Harry Potter")
    public void setHarryPotterAsUser() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CUSTOMER_NAME_DROPDOWN))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CUSTOMER_HARRY_POTTER))).click();
    }
}
