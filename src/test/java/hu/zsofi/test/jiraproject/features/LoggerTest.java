package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.DomHandler;
import hu.zsofi.test.jiraproject.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;


class LoggerTest {
    private String baseUrl = Utils.getBaseUrl();
    private Logger logger;
    private String userImageXpath = "//*[@id=\"header-details-user-fullname\"]//img";
    private DomHandler domHandler;

    @BeforeAll
    static void setup() {
        Utils.setDriverPath();
    }

    @BeforeEach
    void createLoggerInstance() {
        logger = new Logger(new FirefoxDriver(), baseUrl);
        domHandler = new DomHandler(logger.getDriver());
    }

    @AfterEach
    void closeDriver() {
        logger.closeDriver();
    }

    @Test
    void testLoginValid() {
        String expectedResult = "User profile for " + System.getenv("JIRA_USER_FULL_NAME");

        logger.loginValidCredentials();
        WebElement profileImage = domHandler.waitAndGetElement(userImageXpath);
        String altString = profileImage.getAttribute("alt");

        assertEquals(expectedResult, altString);
    }

    @Test
    void testLogout() {
        logger.logout();

        String loginXpath = "//*[@id='user-options']/a[contains(concat(' ', normalize-space(@class),' '),' login-link ')]";
        domHandler.waitForElementLoad(loginXpath);
        boolean isLoginPresent = domHandler.isElementPresent(loginXpath);

        assertTrue(isLoginPresent);
    }


    @Test
    void testLoginEmptyFields(){
        logger.login("","");
        String logInButtonXpath = "//*[@id=\"usernameerror\"]/p";
        boolean actualResult = domHandler.isPresentAfterWaiting(logInButtonXpath);

        assertTrue(actualResult);
    }

    @Test
    void testInvalidCredentials(){
        logger.login("ecetes","uborka");
        String logInButtonXpath = "//*[@id=\"usernameerror\"]/p";

        assertTrue(domHandler.isPresentAfterWaiting(logInButtonXpath));
    }

    @Test
    void testLoginValidSecondaryPage(){
        String expectedResult = "User profile for " + System.getenv("JIRA_USER_FULL_NAME");

        logger.secondaryLoginValid();
        WebElement profileImage = domHandler.waitAndGetElement(userImageXpath);
        String altString = profileImage.getAttribute("alt");

        assertEquals(expectedResult, altString);
    }
}