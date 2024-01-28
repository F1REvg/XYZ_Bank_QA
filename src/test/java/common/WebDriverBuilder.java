package common;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class WebDriverBuilder {
    private WebDriver webDriver;

    public WebDriverBuilder() {}

//    public WebDriverBuilder createDriver() throws MalformedURLException{
//        System.setProperty("webdriver.chrome.driver", Paths.get("D:", "Downloads", "chromedriver.exe").toString());
//        this.webDriver = new ChromeDriver();
//        return this;
//    }

    public WebDriverBuilder createDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("43");
        this.webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        return this;
    }

    public WebDriverBuilder maximize() {
        this.webDriver.manage().window().maximize();
        return this;
    }

    public WebDriverBuilder setImplicitlyWait(long seconds) {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        return this;
    }

    public WebDriver build() {
        return webDriver;
    }
}
