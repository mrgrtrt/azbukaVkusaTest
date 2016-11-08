package epam.testauto.pageobjects;

import epam.testauto.basics.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkbox extends Element {
    public Checkbox(WebDriver driver, String text) {
        super(driver, By.id(text));
    }

    public boolean isChecked() {
        return getElement().isSelected();
    }

    public void check() {
        if (!isChecked()) {
            getElement().click();
        }
    }
    public void uncheck() {
        if (isChecked()) {
            getElement().click();
        }
    }

    public void checkboxSwitch(boolean toCheck) {
        if (toCheck) {
            check();
        } else uncheck();
    }
}
