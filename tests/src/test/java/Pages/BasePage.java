package Pages;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import java.util.Properties;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected Properties configured = new Properties();

    public BasePage() {
        
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); 
    
    }

    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        // Setup remote WebDriver through Selenium Grid
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize(); // Maximize the browser window
        initWait(50); 
    }


    private void initWait(int timeoutInSeconds) {
        this.wait = new WebDriverWait(driver, timeoutInSeconds);
    }
     // ensuring the element is visible before interacting with it
     private WebElement waitVisibiiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

       // Method to get the title of the current page
       public String getPageTitle() {
        return driver.getTitle();
    }

      // Method to click on elements
      public void click(By locator) {
        WebElement element = waitVisibiiltyAndFindElement(locator);
        element.click();
    }

    // Click an element using XPath
    public void clickByXPath(By xpath) {
        WebElement element =getElementByXPath(xpath);
        element.click();
    }
    
    // Method to set text on input fields
    
    protected void enterText(By locator, String text) {
        WebElement element = getElementByXPath(locator);
        element.clear();
        element.sendKeys(text);
    }
  
    // Method to get text from a web element
    public String getText(By locator) {
        return waitVisibiiltyAndFindElement(locator).getText();
    }
    
 // Get a single web element by its XPath
    public WebElement getElementByXPath(By xpath) {
        return waitVisibiiltyAndFindElement(xpath);
    }


    public void deleteCookies() {
        this.driver.manage().deleteAllCookies();
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
