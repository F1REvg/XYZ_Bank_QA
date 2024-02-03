package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    private final By CUSTOMER_LOGIN_BUTTON = By.xpath("//button[@ng-click='customer()']");
    private final By CUSTOMER_NAME_DROPDOWN = By.xpath("//select[@name='userSelect']");
    private final By CUSTOMER_HARRY_POTTER = By.xpath("//option[text()='Harry Potter']");

    @Step("Входим в аккаунт под пользователем Harry Potter")
    public void loginAsHarryPotter() {
        clickCustomerLoginButton();
        setHarryPotterAsUser();
        clickButtonSubmit();
    }

    @Step("Нажимаем на кнопку Customer Login")
    public void clickCustomerLoginButton() {
        elementToBeClickable(CUSTOMER_LOGIN_BUTTON).click();
    }

    @Step("Выбираем пользователя Harry Potter")
    public void setHarryPotterAsUser() {
        elementToBeClickable(CUSTOMER_NAME_DROPDOWN).click();
        elementToBeClickable(CUSTOMER_HARRY_POTTER).click();
    }
}
