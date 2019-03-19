package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.DomHandler;
import hu.zsofi.test.jiraproject.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;


class CreateIssueTest {
    private IssueCreater issueCreater;
    private DomHandler domHandler;
    private String createIssueTitleXPath;

    @BeforeAll
    static void setDriver() {
        Utils.setDriverPath();
    }

    @BeforeEach
    void setupTest() {
        issueCreater = new IssueCreater(new FirefoxDriver());
    }

    @AfterEach
    void teardown() {
        issueCreater.deleteIssue();
        issueCreater.closeDriver();
    }


    @Test
    void createIssueMandatoryFieldsFilled() {
        issueCreater.createIssueMandatoryFieldsFilled();
        assertTrue(issueCreater.isIssueCreated());
    }

}
