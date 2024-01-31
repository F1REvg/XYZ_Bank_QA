package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FibonacciByDay;
import utils.MakeCSV;
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

    final String BUTTON_SUBMIT = "//button[@type='submit']";

    @Step("Нажимаем на кнопку подтверждения")
    public void clickButtonSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BUTTON_SUBMIT))).click();
    }
}
