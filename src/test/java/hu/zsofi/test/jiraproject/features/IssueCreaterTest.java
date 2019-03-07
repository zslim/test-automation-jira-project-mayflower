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
        domHandler = new DomHandler(issueCreater.getDriver());
        createIssueTitleXPath = "//*[@id=\"create-issue-dialog\"]//h2";
    }

    @AfterEach
    void teardown() {
        issueCreater.deleteIssue();
        issueCreater.closeDriver();
    }


    @Test
    void createIssueMandatoryFieldsFilled() {
        issueCreater.openIssueForm();
        domHandler.waitForElementLoad(createIssueTitleXPath);

        String summaryFieldXpath = "//*[@id=\"summary\"]";
        WebElement summaryField = domHandler.getElement(summaryFieldXpath);
        summaryField.sendKeys("Nice Summary");
        summaryField.submit();

        assertTrue(domHandler.isPresentAfterWaiting(issueCreater.navigateToNewIssue()));
    }

}
