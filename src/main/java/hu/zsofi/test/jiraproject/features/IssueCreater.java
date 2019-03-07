package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.DomHandler;
import hu.zsofi.test.jiraproject.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IssueCreater extends Feature {

    private Logger logger;
    private DomHandler domHandler;

    protected IssueCreater(WebDriver driver) {
        super(driver);
        this.logger = new Logger(driver, Utils.getBaseUrl());
        this.domHandler = new DomHandler(driver);
    }

    public void openIssueForm(){
        String createIssueButtonXPath ="//*[@id=\"create_link\"]";
        logger.loginValidCredentials();
        WebElement createIssueButton = domHandler.waitAndGetElement(createIssueButtonXPath);
        createIssueButton.click();
    }
}
