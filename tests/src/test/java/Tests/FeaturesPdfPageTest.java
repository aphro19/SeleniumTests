
package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import java.net.MalformedURLException;

import java.util.List;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import Pages.BasePage;
import Pages.FeaturesPdfPage;

public class FeaturesPdfPageTest extends BasePage {

    FeaturesPdfPage featurespage;
    private Actions actions;

    @Before
    public void setup() throws MalformedURLException, IOException {
        super.setup();
        featurespage = new FeaturesPdfPage(this.driver);
        actions = new Actions(driver);
    }

    @Test
    public void testPagetitle() throws IOException {

        featurespage = new FeaturesPdfPage(this.driver);
        featurespage.verifyPageTitle(configured.getProperty("FEATURES_PAGE_TITLE"));

    }

    @Test
    public void testBody() {

        // Test for Main Title
        WebElement mainTitle = featurespage.getElementByXPath(FeaturesPdfPage.mainTitle);
        assert mainTitle.isDisplayed();

        // Test for Feature Image
        WebElement featureImage = featurespage.getElementByXPath(FeaturesPdfPage.featureImage);
        assert featureImage.isDisplayed();

        WebElement whiteBlockTitle = featurespage.getElementByXPath(FeaturesPdfPage.whiteBlockTitle);
        assertTrue("White block title is not displayed as expected.", whiteBlockTitle.isDisplayed());

        WebElement whiteBlockSubtitle = featurespage.getElementByXPath(FeaturesPdfPage.whiteBlockSubtitle);
        assertTrue("White block subtitle is not displayed as expected.", whiteBlockSubtitle.isDisplayed());

        List<WebElement> icons = featurespage.getElements(FeaturesPdfPage.infocardIcons);
        assertFalse("Infocard icons are not present.", icons.isEmpty());
        icons.forEach(icon -> assertTrue("Icon is not displayed.", icon.isDisplayed()));

        List<WebElement> titles = featurespage.getElements(FeaturesPdfPage.infocardTitles);
        assertFalse("Infocard titles are not present.", titles.isEmpty());
        titles.forEach(title -> assertTrue("Infocard title is not displayed.", title.isDisplayed()));

        List<WebElement> texts = featurespage.getElements(FeaturesPdfPage.infocardBodies);
        assertFalse("Infocard texts are not present.", texts.isEmpty());
        texts.forEach(text -> assertTrue("Infocard text is not displayed.", text.isDisplayed()));

        WebElement timeSavingImage = featurespage.getElementByXPath(FeaturesPdfPage.timeSavingImage);
        assertTrue("Time-saving image is not displayed as expected.", timeSavingImage.isDisplayed());

        WebElement wiseUseOfTimeTitle = featurespage.getElementByXPath(FeaturesPdfPage.wiseUseOfTimeTitle);
        assertTrue("Wise use of time title is not displayed as expected.", wiseUseOfTimeTitle.isDisplayed());

        WebElement documentProcessSubtitle = featurespage.getElementByXPath(FeaturesPdfPage.documentProcessSubtitle);
        assertTrue("Document process subtitle is not displayed as expected.", documentProcessSubtitle.isDisplayed());

        WebElement timeSavingTips = featurespage.getElementByXPath(FeaturesPdfPage.timeSavingTips);
        assertTrue("Time-saving tips paragraph is not displayed as expected.", timeSavingTips.isDisplayed());

        WebElement fileManagementFreedomTitle = featurespage
                .getElementByXPath(FeaturesPdfPage.fileManagementFreedomTitle);
        assertTrue("File management freedom title is not displayed as expected.",
                fileManagementFreedomTitle.isDisplayed());

        WebElement mobilePlatformImage = featurespage.getElementByXPath(FeaturesPdfPage.mobilePlatformImage);
        assertTrue("Mobile Platform image is not displayed", mobilePlatformImage.isDisplayed());

        WebElement platformTitle = featurespage.getElementByXPath(FeaturesPdfPage.platformTitle);
        assertTrue("Platform title is not displayed", platformTitle.isDisplayed());

        WebElement workFromCloudParagraph = featurespage.getElementByXPath(FeaturesPdfPage.workFromCloudParagraph);
        assertTrue("Work from the cloud paragraph is not displayed", workFromCloudParagraph.isDisplayed());

        WebElement teamBuildingImage = featurespage.getElementByXPath(FeaturesPdfPage.teamBuildingImage);
        assertTrue("Team Building image is not displayed", teamBuildingImage.isDisplayed());

        WebElement premiumFeaturesTitle = featurespage.getElementByXPath(FeaturesPdfPage.premiumFeaturesTitle);
        assertTrue("Premium Features title is not displayed", premiumFeaturesTitle.isDisplayed());

        WebElement challengeText = featurespage.getElementByXPath(FeaturesPdfPage.challengeText);
        assertTrue("Challenge text is not displayed", challengeText.isDisplayed());

    }

    @Test
    public void testFooterLinks() {
        // Check if the footer is displayed
        WebElement footer = driver.findElement(By.cssSelector(".footer-main"));
        assertTrue(footer.isDisplayed());

        // Iterate over properties to check each footer link
        configured.stringPropertyNames().forEach(key -> {
            if (key.startsWith("link.") && key.endsWith(".text")) {
                String baseKey = key.replace(".text", "");
                String linkText = configured.getProperty(key);
                String linkUrl = configured.getProperty(baseKey + ".url");
                featurespage.checkLinkHref(linkText, linkUrl);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    public void testHover() throws InterruptedException {

        actions = new Actions(driver);

        WebElement hamburgerIcon = featurespage.getElementByXPath(By.xpath("//span[i/@class='ico ico--hamburger']"));
        Thread.sleep(250);
        // Perform hover action
        actions.moveToElement(hamburgerIcon).build().perform();

        WebElement dropdownMenu = featurespage
                .getElementByXPath(By.xpath("//div[@class='dropdown dropdown--single dropdown--left']"));

        assertTrue("Dropdown menu is not visible", dropdownMenu.isDisplayed());

    }

    @Test
    public void testMultiplePagesTitles() {
        configured.stringPropertyNames().forEach(key -> {
            if (key.startsWith("link.") && key.endsWith(".url")) {
                String baseKey = key.replace(".url", "");
                String linkUrl = configured.getProperty(key);
                String expectedTitle = configured.getProperty(baseKey + ".title");
                driver.get(linkUrl);
                featurespage.verifyPageTitle(expectedTitle);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted: " + e.getMessage());
                }
            }
        });
    }

    @After
    public void tearDown() {
        super.close();
    }

}
