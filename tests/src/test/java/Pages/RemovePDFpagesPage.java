package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RemovePDFpagesPage extends BasePage {

    public static By selectPDFLocator = By.xpath("//input[@type='file']");

    public static By settingToggleLocator = By.xpath("//a[@id='settingsToogle']");

    public RemovePDFpagesPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.ilovepdf.com/remove-pages");
    }

}
