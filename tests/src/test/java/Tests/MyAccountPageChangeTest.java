package Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Pages.BasePage;
import Pages.FirstPage;
import Pages.LoginPage;
import Pages.MyAccountPage;

public class MyAccountPageChangeTest extends BasePage{
    
    FirstPage firstpage;
    MyAccountPage accountpage ;

    @Before
    public void setup() throws MalformedURLException,IOException {
        super.setup(); 
      
    }
   
    
     @Test
    public void testChange() throws IOException {
      
       String surname ="newSurename";
       firstpage = new FirstPage(this.driver);
       LoginPage loginpage = firstpage.goToLoginPage();

       loginpage.loginSubmit(configured.getProperty("TEST_EMAIL"), configured.getProperty("TEST_PASSWORD"));
       accountpage = new MyAccountPage(this.driver);
      accountpage.change(surname);

      Assert.assertTrue(loginpage.getElementByXPath(MyAccountPage.bodyLocator).getText().contains("Yes! Your information has been updated."));

    }
     
    @Test
    public void testHistoryBackButton() throws IOException {
      firstpage = new FirstPage(this.driver);
        LoginPage loginpage = firstpage.goToLoginPage();
 
        loginpage.loginSubmit(configured.getProperty("TEST_EMAIL"), configured.getProperty("TEST_PASSWORD"));
        accountpage = new MyAccountPage(this.driver);
        accountpage.goBack();

          assertEquals("Back navigation did not return to the first page",configured.getProperty("MAIN_URL"),driver.getCurrentUrl());

    }

    @After
    public void tearDown() {
        super.close();
    }

}
