package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.DomHandler;
import hu.zsofi.test.jiraproject.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IssueCreater extends Feature {

    private Logger logger;
    private DomHandler domHandler;
    private final String myNewIssueXpath = "//*[@id=\"content\"]//li[contains(@title,\"Nice\")]";

    protected IssueCreater(WebDriver driver) {
        super(driver);
        this.logger = new Logger(driver, Utils.getBaseUrl());
        this.domHandler = new DomHandler(driver);
    }

    public void openIssueForm() {
        String createIssueButtonXPath = "//*[@id=\"create_link\"]";
        logger.loginValidCredentials();
        WebElement createIssueButton = domHandler.waitAndGetElement(createIssueButtonXPath);
        createIssueButton.click();
    }

    public void navigateToNewIssue() {
        domHandler.getDriver().get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");

        String issuesXpath = "//*[@id=\"find_link\"]";
        WebElement issues = domHandler.getClickableElement(issuesXpath);
        issues.click();

        String issuesMoreXpath = "//*[@id=\"issues_new_search_link_lnk\"]";
        WebElement issuesMore = domHandler.waitAndGetElement(issuesMoreXpath);
        issuesMore.click();

        String myIssuesXpath = "//*[@id=\"navigator-sidebar\"]//a[contains(text(), \"me\")]";
        WebElement myIssues = domHandler.getClickableElement(myIssuesXpath);
        myIssues.click();

    }

    public void deleteIssue() {
        String traceXpath = "//*[@id=\"TM4JTraceLinksPanel\"]/issue-trace-links-panel//traceability-empty-message";
        domHandler.waitForElementLoad(traceXpath);

        String issueMoreXpath = "//*[@id=\"opsbar-operations_more\"]";
        WebElement issueMore = domHandler.getClickableElement(issueMoreXpath);
        issueMore.click();

        String deleteXpath = "//*[@id=\"delete-issue\"]/a";
        WebElement delete = domHandler.getClickableElement(deleteXpath);
        delete.click();

        String deleteConfirmXpath = "//*[@id=\"delete-issue-submit\"]";
        WebElement deleteConfirm = domHandler.getClickableElement(deleteConfirmXpath);
        deleteConfirm.click();
    }

    public boolean isIssueCreated(){
        navigateToNewIssue();
        return domHandler.isPresentAfterWaiting(myNewIssueXpath);
    }

    public void createIssueMandatoryFieldsFilled(){
        openIssueForm();
        String createIssueTitleXPath = "//*[@id=\"create-issue-dialog\"]//h2";

        domHandler.waitForElementLoad(createIssueTitleXPath);

        String summaryFieldXpath = "//*[@id=\"summary\"]";
        WebElement summaryField = domHandler.getElement(summaryFieldXpath);
        summaryField.sendKeys("Nice Summary");
        summaryField.submit();
    }
}
