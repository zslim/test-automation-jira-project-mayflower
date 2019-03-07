package hu.zsofi.test.jiraproject.features;

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
    private Logger logger;
    private String userImageXpath = "//*[@id=\"header-details-user-fullname\"]//img";


    @BeforeAll
    static void setDriverPath() {
        String driverProperty = System.getenv("DRIVER_PROPERTY");
        String driverPath = System.getenv("DRIVER_PATH");
        System.setProperty(driverProperty, driverPath);
    }

    @BeforeEach
    void createLoggerInstance() {
        String baseUrl = "https://jira.codecool.codecanvas.hu/";
        logger = new Logger(new FirefoxDriver(), baseUrl);
    }

    @AfterEach
    void closeDriver() {
        logger.closeDriver();
    }

    @Test
    void testLoginValid() {
        String expectedResult = "User profile for " + System.getenv("JIRA_USER_FULL_NAME");
        logger.loginValidCredentials();

        WebDriver driver = logger.getDriver();
        Utils.waitForContentLoad(driver, userImageXpath);
        WebElement profileImage = driver.findElement(By.xpath(userImageXpath));
        String altString = profileImage.getAttribute("alt");

        assertEquals(expectedResult, altString);
    }

    @Test
    void testLogout() {
        logger.logout();

        WebDriver driver = logger.getDriver();
        String loginXpath = "//*[@id='user-options']/a[contains(concat(' ', normalize-space(@class),' '),' login-link ')]";
        Utils.waitForContentLoad(driver, loginXpath);

        boolean isLoginPresent = Utils.isElementPresent(driver, loginXpath);
        assertTrue(isLoginPresent);
    }


    @Test
    void testLoginEmptyFields(){
        logger.login("","");
        WebDriver driver = logger.getDriver();
        String logInButton = "//*[@id=\"usernameerror\"]/p";

        Utils.waitForContentLoad(driver, logInButton);

        assertTrue(Utils.isElementPresent(driver, logInButton));
    }

    @Test
    void testInvalidCredentials(){
        logger.login("ecetes","uborka");
        WebDriver driver = logger.getDriver();
        String logInButton = "//*[@id=\"usernameerror\"]/p";

        Utils.waitForContentLoad(driver, logInButton);

        assertTrue(Utils.isElementPresent(driver, logInButton));
    }

    @Test
    void testLoginValidSecondaryPage(){
        String expectedResult = "User profile for " + System.getenv("JIRA_USER_FULL_NAME");

        WebDriver driver = logger.getDriver();
        logger.secondaryLoginValid();

        Utils.waitForContentLoad(driver, userImageXpath);
        WebElement profileImage = driver.findElement(By.xpath(userImageXpath));
        String altString = profileImage.getAttribute("alt");

        assertEquals(expectedResult, altString);
    }
}