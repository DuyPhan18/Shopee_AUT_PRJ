package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private WebDriverWait waitWithSpecificTimeout;


    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    public boolean isElementVisibility(WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }
    public void enterText(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }
    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


//    public void closeAdPopup() {
//        try {
//            // Chờ cho đến khi phần tử tồn tại
//            //WebElement adPositionBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ad_position_box']")));
//
//            // Chờ cho đến khi phần tử có thể nhìn thấy
//            wait.until(ExpectedConditions.visibilityOf(adPositionBox));
//            driver.switchTo().frame(adPositionBox);
//            driver.switchTo().frame(card);
//            closeAdBtn.click();
//
//        } catch (TimeoutException e) {
//            System.out.println("Ad position box did not appear within the timeout.");
//        } catch (NoSuchElementException e) {
//            System.out.println("Ad position box or close button not found.");
//        }
//    }




}
