package Tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.util.Properties;
import static org.junit.Assert.assertEquals;

import Pages.BasePage;
import Pages.FirstPage;
import Pages.LoginPage;

public class LoginPageTest  {

    private WebDriver driver;
    private WebDriverWait wait;
    private Properties configured;

    private By registeredLocator = By.xpath("//img[contains(@class, 'avatar avatar--registered')]");
    private By LogOutLocator = By.xpath("//a[@class='logout']");
    private By LoginLocator = By.xpath("//a[@href='/login']" );
    
    @Before
    public void setup()throws MalformedURLException,IOException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);

        String configFilePath = "src/test/java/config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        configured = new Properties();
        configured.load(propsInput);
    }
   
    
     @Test
    public void testLogin() throws IOException {
      
      FirstPage firstpage = new FirstPage(this.driver);
        
       LoginPage loginpage = firstpage.goToLoginPage();

       loginpage.loginSubmit(configured.getProperty("TEST_EMAIL"), configured.getProperty("TEST_PASSWORD"));
     
      // verificatioin login
     
     Assert.assertTrue(loginpage.getElementByXPath(registeredLocator).getAttribute("alt").contains(configured.getProperty("TEST_USERNAME")));

    }

    @Test
    public void testLoginFailedBypwd() throws IOException {

        FirstPage firstpage = new FirstPage(this.driver);
        
       LoginPage loginpage = firstpage.goToLoginPage();

       loginpage.loginSubmit(configured.getProperty("TEST_EMAIL"), "PASSWORD");
     
      // verificatioin login
     
        Assert.assertTrue(loginpage.getElementByXPath(LoginPage.bodyLocator).getText().contains("Incorrect username or password."));

    }

    @Test
    public void testLoginFailedByEmail() throws IOException {

        FirstPage firstpage = new FirstPage(this.driver);
        
       LoginPage loginpage = firstpage.goToLoginPage();

       loginpage.loginSubmit("EMAIL", "PASSWORD");
     
      // verificatioin login
     
        Assert.assertTrue(loginpage.getElementByXPath(LoginPage.bodyLocator).getText().contains("Email is not a valid email address."));

    }
    
    @Test
    public void testLogOut() throws IOException {

        FirstPage firstpage = new FirstPage(this.driver);
        
       LoginPage loginpage = firstpage.goToLoginPage();

       loginpage.loginSubmit(configured.getProperty("TEST_EMAIL"), configured.getProperty("TEST_PASSWORD"));
     

       Actions action = new Actions(driver);
       action.moveToElement(loginpage.getElementByXPath(registeredLocator)).perform();
       loginpage.clickByXPath(LogOutLocator);
       firstpage.getElementByXPath(LoginLocator);
     
    }
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
