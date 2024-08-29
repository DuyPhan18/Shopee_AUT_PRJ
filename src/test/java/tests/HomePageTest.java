package tests;

import core.BaseTest;
import core.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @Test
    public void VerifyAllElementDisplayed(){
        homePage = new HomePage(getDriver());

        Assert.assertTrue(homePage.isSearchFormDisplay(), "SearchForm" + Constants.IS_NOT_DISPLAYED);
    }

}
