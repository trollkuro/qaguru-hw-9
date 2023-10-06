package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.FakeDataSetter;

public class RegistrationTests extends TestBase {

    private FakeDataSetter data = new FakeDataSetter();
    private String firstName = data.firstName();
    private String lastName = data.lastName();
    private String userEmail = data.email();
    private String sex = data.gender();
    private String userNumber = data.number();
    private String day = "04";
    private String month = "June";
    private String year = "1975";
    private String subject = data.subject();
    private String hobby = data.hobby();
    private String filePath = "src/test/resources/tiger.jpg";
    private String fileName = "tiger.jpg";
    private String currentAddress = data.currentAddress();
    private String state = data.state();
    private String city = data.city(state);
    private String resultTableTitle = "Thanks for submitting the form";
    private RegistrationPage registrationPage = new RegistrationPage();


    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .removeFooterAndBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(sex)
                .setNumber(userNumber)
                .setDate(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFle(filePath)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitForm();

        registrationPage.verifyResult(firstName + " " + lastName)
                        .verifyResult(userEmail)
                        .verifyResult(sex)
                        .verifyResult(userNumber)
                        .verifyResult(day + " " + month + "," + year)
                        .verifyResult(subject)
                        .verifyResult(hobby)
                        .verifyResult(fileName)
                        .verifyResult(currentAddress)
                        .verifyResult(state + " " + city);
    }

    @Test
    void modalIsClosedTest(){
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(sex)
                .setNumber(userNumber)
                .submitForm();

        registrationPage.modalIsDisplayed()
                        .verifyModalTitle(resultTableTitle)
                        .closeModal()
                        .modalIsNotDisplayed();
    }
}