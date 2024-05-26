package Tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Pages.BasePage;
import Pages.RemovePDFpagesPage;

public class RemovePDFpagesPageTest extends BasePage {

    RemovePDFpagesPage rmvPdfPage;

    @Before
    public void setup() throws MalformedURLException, IOException {
        super.setup();

    }

    @Test
    public void testUpload() throws IOException, InterruptedException {

        rmvPdfPage = new RemovePDFpagesPage(this.driver);

        File uploadFile = new File("/home/selenium/tests/test.pdf");

        ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        WebElement elem = driver.findElement(By.xpath("//input[@type='file']"));
        String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";

        ((JavascriptExecutor) driver).executeScript(js, elem);

        elem.sendKeys(uploadFile.getAbsolutePath());

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Click on pages to remove from document. You can use \"shift\" key to set ranges.')]")));
        assertTrue("Element is not displayed on the page ,file not uploaded",element.isDisplayed() );

    }

    @After
    public void tearDown() {
        super.close();
    }

}
