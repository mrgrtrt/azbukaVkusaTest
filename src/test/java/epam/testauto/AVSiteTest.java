package epam.testauto;

import epam.testauto.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.internal.thread.ThreadExecutionException;
import ru.yandex.qatools.allure.annotations.Title;

@Title("av.ru test")
public class AVSiteTest extends SetupDriver {
    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeSuite
    public void setup(){
        driver = getDriver();
    }

    @Title("Register check")
    @Test(priority = 1, dataProviderClass = Data.class, dataProvider = "register")
    public void register(boolean isCorrect, String email, String password, String checkPassword,
                         String name, String surname, String middleName,
                         boolean agreeReceiveNews, boolean agreeConditions) {
        RegisterPage registerPage = new RegisterPage(driver)
                        .typeRegisterData(email, password, checkPassword,
                                          name, surname, middleName,
                                          agreeReceiveNews, agreeConditions)
                        .submitRegisterForm(isCorrect, agreeConditions);

    }

    @Title("Address check")
    @Test(priority = 2, dataProviderClass = Data.class, dataProvider = "address")
    public void address(boolean withAddress, String address, String roomNumber, String floor,
                        boolean cottage, String phone, String comment) {
        registerPage.inputAddress(withAddress, address, roomNumber,
                                  floor, cottage, phone, comment);
    }

}
