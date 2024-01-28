package pages;


import common.Locators;
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
    Locators locators;
    WebDriverWait wait;
    FibonacciByDay fibonacciByDay;
    MakeCSV makeCSV;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.locators = new Locators();
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        this.fibonacciByDay = new FibonacciByDay();
        this.makeCSV = new MakeCSV();
    }

    @Step("Нажимаем на кнопку подтверждения")
    public void clickButtonSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.BUTTON_SUBMIT))).click();
    }
}
