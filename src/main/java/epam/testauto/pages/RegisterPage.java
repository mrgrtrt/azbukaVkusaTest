package epam.testauto.pages;

import epam.testauto.basics.BasePage;
import epam.testauto.pageobjects.Checkbox;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.allure.annotations.Step;

public class RegisterPage extends BasePage {

    private String errorMessage = "Чтобы продолжить регистрацию, примите условия соглашения";

    @FindBy(id = "agree-conditions")
    private WebElement errorMessageText;
    @FindBy(css = "input[name='email']")
    private WebElement emailTextBox;
    @FindBy(css = "input[name='password']")
    private WebElement passwordTextBox;
    @FindBy(css = "input[name='checkPassword']")
    private WebElement checkPasswordTextBox;
    @FindBy(css = "input[name='name']")
    private WebElement nameTextBox;
    @FindBy(css = "input[name='surname']")
    private WebElement surnameTextBox;
    @FindBy(css = "input[name='middleName']")
    private WebElement middleNameTextBox;
    @FindBy(xpath = "//button[@type='button']")
    private WebElement registrationButton;

    @FindBy(css = "li.b-registration__step > span")
    private WebElement personalDataAreOk;
    @FindBy(css = ".b-registration__submit_left")
    private WebElement withoutAddressButton;
    @FindBy(name = "address")
    private WebElement addressTextBox;
    @FindBy(name = "apartmentOffice")
    private WebElement roomNumberTextBox;
    @FindBy(name = "line2")
    private WebElement floorTextBox;
    @FindBy(name = "contactPhone")
    private WebElement phoneTextBox;
    @FindBy(name = "comment")
    private WebElement commentTextBox;
    @FindBy(css = ".b-registration__submit_right")
    private WebElement withAddressButton;
    @FindBy(css = ".b-site-top__account-name")
    private WebElement accountName;

    private Checkbox agreeReceiveNewsCheckbox = new Checkbox(driver, "agreeReceiveNews");
    private Checkbox agreeConditionsCheckbox = new Checkbox(driver, "agreeConditions");
    private Checkbox cottageCheckbox = new Checkbox(driver, "privateSupport");

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("Typing register data")
    public RegisterPage typeRegisterData(String email, String password, String checkPassword,
                                         String name, String surname, String middleName,
                                         boolean agreeReceiveNews, boolean agreeConditions) {
        setElementText(emailTextBox, email);
        setElementText(passwordTextBox, password);
        setElementText(checkPasswordTextBox, checkPassword);
        setElementText(nameTextBox, name);
        setElementText(surnameTextBox, surname);
        setElementText(middleNameTextBox, middleName);

        agreeReceiveNewsCheckbox.checkboxSwitch(agreeReceiveNews);
        agreeConditionsCheckbox.checkboxSwitch(agreeConditions);

        return this;
    }

    @Step("Submit login form and check if it's right")
    public RegisterPage submitRegisterForm(boolean isCorrect, boolean agreeConditions) {
        clickElement(registrationButton);
        if (!agreeConditions) {
            Assert.assertEquals(errorMessageText.getText(), errorMessage);
        }
        if (isCorrect) {
            Assert.assertTrue(personalDataAreOk.isDisplayed());
        }
        return this;
    }

    public RegisterPage inputAddress(boolean withAddress, String address, String roomNumber, String floor,
                                     boolean cottage, String phone, String comment) {
//        Assert.assertTrue(personalDataAreOk.isDisplayed());
        if (withAddress) {
            setElementText(addressTextBox, address);
            setElementText(roomNumberTextBox, roomNumber);
            setElementText(floorTextBox, floor);
            cottageCheckbox.checkboxSwitch(cottage);
            setElementText(phoneTextBox, phone);
            setElementText(commentTextBox, comment);
            try {
                clickElement(withAddressButton);
                Assert.assertTrue(accountName.isDisplayed());
            } catch (InvalidElementStateException e) {}


        } else {
            clickElement(withoutAddressButton);
        }
        return this;
    }
}
