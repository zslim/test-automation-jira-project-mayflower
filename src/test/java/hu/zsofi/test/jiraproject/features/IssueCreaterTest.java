package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.DomHandler;
import hu.zsofi.test.jiraproject.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

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
    void closeDriver() {
        issueCreater.closeDriver();
    }

    @Test
    void openCreateIssueForm(){
        issueCreater.openIssueForm();
        assertTrue(domHandler.isPresentAfterWaiting(createIssueTitleXPath));
    }

}
