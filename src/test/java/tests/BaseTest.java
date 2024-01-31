package tests;

import common.ConfProperties;
import common.WebDriverBuilder;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.TransactionsPage;

import java.net.MalformedURLException;

public abstract class BaseTest {
    WebDriver webDriver;
    LoginPage loginPage;
    TransactionsPage transactionsPage;

    public BaseTest() throws MalformedURLException {
        webDriver = new WebDriverBuilder()
                .createDriver(ConfProperties.getProperty("driver"))
                .setImplicitlyWait(5)
                .maximize()
                .build();
        loginPage = new LoginPage(webDriver);
        transactionsPage = new TransactionsPage(webDriver);
    }

    @Step("Инициализируем браузер и открываем страницу {url}")
    public void init (String url) {
        webDriver.get(url);
    }

    @AfterEach
    public void dispose() {
        webDriver.close();
        webDriver.quit();
    }
}
