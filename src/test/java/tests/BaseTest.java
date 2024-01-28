package tests;

import common.Urls;
import common.WebDriverBuilder;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.TransactionsPage;

import java.net.MalformedURLException;

public abstract class BaseTest {
    WebDriver webDriver;
    Urls urls;
    LoginPage loginPage;
    TransactionsPage transactionsPage;

    public BaseTest() throws MalformedURLException {
        webDriver = new WebDriverBuilder()
                .createDriver()
                .setImplicitlyWait(5)
                .maximize()
                .build();
        urls = new Urls();
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
