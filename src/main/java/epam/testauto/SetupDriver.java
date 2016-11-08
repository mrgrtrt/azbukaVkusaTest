package epam.testauto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class SetupDriver {
    private WebDriver driver;
    static String driverPath = "src/test/resources/";

    public WebDriver getDriver() {
        return driver;
    }

    private void setDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                driver = initFirefoxDriver();
        }
    }

    private static WebDriver initChromeDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        //to remove '--ignore-certificate-errors--' message when launching chromedriver
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        capabilities.setCapability("chrome.binary", driverPath + "chromedriver.exe");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        System.setProperty("webdriver.edge.driver", driverPath + "MicrosoftWebDriver.exe");
        WebDriver driver = new EdgeDriver();
        return driver;
    }

    @Parameters({"browserType", "indexPageUrl"})
    @BeforeSuite
    public void initSetupDriver(String browserType, String pageUrl) {
        setDriver(browserType);
        driver.get(pageUrl);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

}
