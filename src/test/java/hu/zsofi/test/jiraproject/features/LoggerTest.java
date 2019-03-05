package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;


class LoggerTest {
    private String baseUrl = "https://jira.codecool.codecanvas.hu/";
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
        logger = new Logger(new FirefoxDriver(), baseUrl);
    }

    @AfterEach
    void closeDriver() {
        logger.closeDriver();
    }

    @org.junit.jupiter.api.Test
    void testLoginValid() {
        String expectedResult = "User profile for " + System.getenv("JIRA_USER_FULL_NAME");
        logger.loginValidCredentials();

        WebDriver driver = logger.getDriver();
        Util.waitForContentLoad(driver, userImageXpath);
        WebElement profileImage = driver.findElement(By.xpath(userImageXpath));
        String altString = profileImage.getAttribute("alt");

        assertEquals(expectedResult, altString);
    }
}