package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(how = How.CLASS_NAME, using = "shopee-searchbar")
    private WebElement searchForm;
    @FindBy(how = How.CLASS_NAME, using = "full-home-banners-wrapper")
    private WebElement banners;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'home-category-list__header')]//div[contains(@class, 'shopee-header-section__content')]")
    private WebElement categorySection;
    @FindBy(how = How.CLASS_NAME, using = "shopee-searchbar-input__input")
    private WebElement searchInput;
    @FindBy(how = How.ID,using = "shopee-searchbar__search-button")
    private WebElement searchBtn;

    public boolean isSearchFormDisplay(){
        return isElementVisibility(searchForm);
    }
    public void enterTextToSearchInput(String text){
        enterText(searchInput, text);
    }
    public void clickToSearchBtn(){
        clickElement(searchBtn);
    }
}
