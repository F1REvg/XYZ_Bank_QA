package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FibonacciByDay;
import java.time.Duration;

public abstract class BasePage {

    WebDriver webDriver;
    WebDriverWait wait;
    FibonacciByDay fibonacciByDay;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        this.fibonacciByDay = new FibonacciByDay();
    }

    final By BUTTON_SUBMIT = By.xpath("//button[@type='submit']");

    public WebElement elementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement presenceOfElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Step("Нажимаем на кнопку подтверждения")
    public void clickButtonSubmit() {
        elementToBeClickable(BUTTON_SUBMIT).click();
    }
}
