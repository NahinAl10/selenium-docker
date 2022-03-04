package com.newtours.tests;

import com.newtours.pages.*;
import com.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by nahin on 2/22/2022
 **/
public class BookFlightTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParameters(String noOfPassengers, String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void registrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPage")
    public void registrationConfirmationPage() throws InterruptedException {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
//        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "registrationConfirmationPage")
    public void flightDetailsPage() throws InterruptedException {
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
//        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "flightDetailsPage")
    public void findFlightPage() throws InterruptedException {
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToFlightConfirmationPage();
//        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "findFlightPage")
    public void flightConfirmationPage() throws InterruptedException {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
//        Thread.sleep(3000);
    }
}
