package hu.zsofi.test.jiraproject.features;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;


class LoggerTest {
    private String baseUrl = "https://jira.codecool.codecanvas.hu/";
    private Logger logger;

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
    void testLogin() {
        String jiraUsername = System.getenv("JIRA_USER_NAME");
        String jiraPassword = System.getenv("JIRA_PASSWORD");
        String expectedResult = "User profile for " + System.getenv("JIRA_USER_FULL_NAME");
        String actualResult = logger.login(jiraUsername, jiraPassword);
        assertEquals(expectedResult, actualResult);
    }
}