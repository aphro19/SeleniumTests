package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

    protected By ChangeBtnLocator = By.xpath("//button[@id='account-change-btn']");
    private By surnameInptLocator = By.xpath("//input[@id='updateuserform-surname']");
    private By submitLocator = By.xpath("//input[@id='save-btn']");

    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.ilovepdf.com/user");
    }

    public void change(String surname) {
        clickByXPath(ChangeBtnLocator);
        enterText(surnameInptLocator, surname);
        clickByXPath(submitLocator);
    }

}
