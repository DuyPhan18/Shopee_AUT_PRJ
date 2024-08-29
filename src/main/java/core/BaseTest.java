package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseTest {

    private WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(@Optional("chrome")String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }


    @AfterSuite
    public void afterSuite(){
        if (driver != null){
            try {
                driver.close();
                driver.quit();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public WebDriver getDriver(){
        return driver;
    }
}
