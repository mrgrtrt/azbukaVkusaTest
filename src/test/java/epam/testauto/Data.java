package epam.testauto;

import org.testng.annotations.DataProvider;


public class Data {

    //boolean isCorrect, String email, String password, String checkPassword,
    //String name, String surname, String middleName,
    //boolean agreeReceiveNews, boolean agreeConditions
    @DataProvider(name = "register")
    public static Object[][] logData() {
        return new Object[][] {
                {false, "", "", "", "", "", "", false, false},
                {false, "", "", "", "", "", "", false, true},
                {false, "email.ru", "password", "password", "name", "", "", false, true},
                {false, "ivanovaritavitalevna@gmail.com", "pass", "pass", "name", "", "", false, true},
                {false, "ivanovaritavitalevna@gmail.com", "password", "passwordword", "name", "", "", false, true},
                {false, "ivanovaritavitalevna@gmail.com", "password", "password", "name", "", "", false, true}
        };
    }


    //boolean withAddress, String address, String roomNumber, String floor,
    //boolean cottage, String phone, String comment
    @DataProvider(name = "address")
    public static Object[][] addressData() {
        return new Object[][] {
                {false, "", "", "", false, "", ""},
                {true, "", "", "", true, "", ""},
                {true, "Россия, Москва, Кутузовский проспект, 2/1к1А", "12", "2", false, "9888888888", "no comments"}
        };
    }

}

