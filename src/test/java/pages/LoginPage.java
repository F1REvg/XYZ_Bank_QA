package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Входим в аккаунт под пользователем Harry Potter")
    public void loginAsHarryPotter() {
        clickCustomerLoginButton();
        setHarryPotterAsUser();
        clickButtonSubmit();
    }

    @Step("Нажимаем на кнопку Customer Login")
    public void clickCustomerLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.CUSTOMER_LOGIN_BUTTON))).click();
    }

    @Step("Выбираем пользователя Harry Potter")
    public void setHarryPotterAsUser() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.CUSTOMER_NAME_DROPDOWN))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.CUSTOMER_HARRY_POTTER))).click();
    }
}
