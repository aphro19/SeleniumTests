package Pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeaturesPdfPage extends BasePage {

    public static By mainTitle = By.xpath("//h1[@class='super__title']");
    public static By featureImage = By.xpath("//img[@alt='Features']");
    public static By whiteBlockTitle = By.xpath("//div[@class='block block--white']//h2[@class='title1']");
    public static By whiteBlockSubtitle = By.xpath("//div[@class='block block--white']//h3[@class='subtitle1']");
    public static By infocardIcons = By.xpath("//div[@class='block block--white']//div[@class='infocard__icon']/img");
    public static By infocardTitles = By.xpath("//div[@class='block block--white']//div[@class='infocard__title']");
    public static By infocardBodies = By.xpath("//div[@class='block block--white']//div[@class='infocard__body']/p");

    public static By timeSavingImage = By.xpath("//div[contains(@class, 'block--grey')]//img");
    public static By wiseUseOfTimeTitle = By.xpath("//div[contains(@class, 'block--grey')]//h2");
    public static By documentProcessSubtitle = By.xpath("//div[contains(@class, 'block--grey')]//h3[1]");
    public static By timeSavingTips = By.xpath("//div[contains(@class, 'block--grey')]//p");
    public static By fileManagementFreedomTitle = By.xpath("//div[contains(@class, 'block--grey')]//h3[2]");

    public static By mobilePlatformImage = By.xpath("//div[contains(@class, 'block--white')]//img");
    public static By platformTitle = By.xpath("//div[contains(@class, 'block--white')]//h2");
    public static By workFromCloudParagraph = By.xpath("//div[contains(@class, 'block--white')]//p");

    public static By teamBuildingImage = By.xpath("//div[contains(@class, 'block--grey')]//img");
    public static By premiumFeaturesTitle = By.xpath("//div[contains(@class, 'block--grey')]//h2");

    public static By challengeText = By.xpath("//div[contains(@class, 'block--separator')]//h3");

    public static By footerContainer = By.xpath("//div[contains(@class, 'footer-main')]");
    public static By footerTitles = By.xpath("//div[@class='footer-main__title']");
    public static By footerNavLinks = By
            .xpath("//div[contains(@class, 'footer-main')]//ul[@class='footer-main__nav']/li");
    public static By appStoreLinks = By.xpath("//ul[contains(@class, 'app__store')]//a");
    public static By footerLanguageSelector = By.xpath("//div[contains(@class, 'footer-big__lang')]");
    public static By footerInfoSlogan = By.xpath("//div[@class='footer-main__info']//p");

    public FeaturesPdfPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.ilovepdf.com/features");
    }

    public List<WebElement> getElements(By by) {
        this.wait = new WebDriverWait(driver, 40);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void checkLinkHref(String linkText, String expectedHref) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));

        try {
            // Output the href attribute to the console for debugging
            System.out.println("Testing link: " + linkText + " URL: " + link.getAttribute("href"));

            assertTrue("The link is not displayed.", link.isDisplayed());
            assertEquals("The href attribute does not match the expected URL.", expectedHref,
                    link.getAttribute("href"));

            // Click the link and wait for navigation
            link.click();

            // Wait for the new page to load and URL to update
            new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(expectedHref));

            // Verify that the URL is exactly what was expected
            String actualURL = driver.getCurrentUrl();
            assertEquals("The URL after clicking does not match the expected URL.", expectedHref, actualURL);

            driver.get("https://www.ilovepdf.com/features");
        } catch (Exception e) {

            System.err.println("Error while testing link: " + linkText + " - " + e.getMessage());
        }

    }

}
