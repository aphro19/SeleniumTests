package Tests;

import org.junit.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.io.*;

import Pages.BasePage;
import Pages.FirstPage;
import Pages.LoginPage;

public class LoginPageTest extends BasePage {

    FirstPage firstpage;
    LoginPage loginpage;
    private By registeredLocator = By.xpath("//img[contains(@class, 'avatar avatar--registered')]");
    private By LogOutLocator = By.xpath("//a[@class='logout']");
    private By LoginLocator = By.xpath("//a[@href='/login']");

    @Before
    public void setup() throws MalformedURLException, IOException {
        super.setup();
        firstpage = new FirstPage(this.driver);

    }

    @Test
    public void testLogin() throws IOException {
        loginpage = firstpage.goToLoginPage();
        loginpage.loginSubmit(configured.getProperty("TEST_EMAIL"), configured.getProperty("TEST_PASSWORD"));

        // verificatioin login

        Assert.assertTrue(loginpage.getElementByXPath(registeredLocator).getAttribute("alt")
                .contains(configured.getProperty("TEST_USERNAME")));

    }

    @Test
    public void testLoginFailedBypwd() throws IOException {

        loginpage = firstpage.goToLoginPage();
        loginpage.loginSubmit(configured.getProperty("TEST_EMAIL"), "PASSWORD");

        // verificatioin login

        Assert.assertTrue(loginpage.getElementByXPath(LoginPage.bodyLocator).getText()
                .contains("Incorrect username or password."));

    }

    @Test
    public void testLoginFailedByEmail() throws IOException {

        loginpage = firstpage.goToLoginPage();
        loginpage.loginSubmit("EMAIL", "PASSWORD");

        // verificatioin login

        Assert.assertTrue(loginpage.getElementByXPath(LoginPage.bodyLocator).getText()
                .contains("Email is not a valid email address."));

    }

    @Test
    public void testLogOut() throws IOException {
        loginpage = firstpage.goToLoginPage();
        loginpage.loginSubmit(configured.getProperty("TEST_EMAIL"), configured.getProperty("TEST_PASSWORD"));
        Actions action = new Actions(driver);
        action.moveToElement(loginpage.getElementByXPath(registeredLocator)).perform();
        loginpage.clickByXPath(LogOutLocator);
        firstpage.getElementByXPath(LoginLocator);

    }

    @After
    public void tearDown() {
        super.close();
    }
}
