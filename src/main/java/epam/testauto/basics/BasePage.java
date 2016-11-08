package epam.testauto.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by Rita on 26.10.2016.
 */
public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadPage(String pageUrl) {
        driver.get(pageUrl);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void setElementText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        //Assert.assertEquals(element.getAttribute("value"), text);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
