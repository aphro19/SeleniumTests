package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

  private By emailInputLocator = By.xpath("//input[@id='loginEmail']");
  private By passwordInputLocator = By.xpath("//input[@id='inputPasswordAuth' and @type='password']");

    
    
    private By ContinueLoginLocator = By.xpath("//button[@id='loginBtn']");
    
     public LoginPage(WebDriver driver)  {
        super(driver);
    }    
    
    public void enterEmail(String email) {
        enterText( emailInputLocator, email);
    }

    public void enterPassword(String password) {
        enterText(passwordInputLocator, password);
    }

   
    
    public void loginSubmit(String email, String password) {
        enterEmail(email);
      enterPassword(password);
      clickByXPath(ContinueLoginLocator);
        // clickByXPath(LogInBtn1Locator);
    }
    
    
}
