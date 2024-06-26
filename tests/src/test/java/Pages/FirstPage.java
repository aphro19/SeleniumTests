package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirstPage extends BasePage {

    private By LogInBtn1Locator = By.xpath("//a[text()='Login']");

    public FirstPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.ilovepdf.com/en");

    }

    public LoginPage goToLoginPage() throws IOException {
        getElementByXPath(LogInBtn1Locator).click();
        return new LoginPage(this.driver);
    }

}
