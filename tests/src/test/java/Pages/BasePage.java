package Pages;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    protected Properties configured ;

    public static By bodyLocator = By.xpath("//body" );

    public BasePage() {
        
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 50); 
    
    }

    @Before
    public void setup()throws MalformedURLException,IOException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 60);

        String configFilePath = "src/test/java/config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        configured = new Properties();
        configured.load(propsInput);
        

        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--disable-gpu"); // Disable GPU acceleration
        options.addArguments("--no-sandbox"); // Bypass OS security model, necessary on certain systems
        options.addArguments("--enable-logging", "--v=1");
    }

      @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
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

    public void verifyPageTitle(String expectedTitle) {
        // Get the title of the current page
        String actualTitle = getPageTitle();
    
        // Assert that the actual title matches the expected title
        Assert.assertEquals("The page title does not match the expected title.",expectedTitle,actualTitle);
    }


    public void goBack()
    {
        this.driver.navigate().back();
    }
}
