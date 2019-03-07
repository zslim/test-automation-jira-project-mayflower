package hu.zsofi.test.jiraproject.features;

import org.openqa.selenium.*;


public class Logger extends Feature {

    private String baseUrl;
    private static final String JIRA_USER_NAME = System.getenv("JIRA_USER_NAME");
    private static final String JIRA_PASSWORD = System.getenv("JIRA_PASSWORD");


    public Logger(WebDriver driver, String baseUrl) {
        super(driver);
        this.baseUrl = baseUrl;
    }

    public void login(String userName, String password) {
        driver.get(baseUrl);

        WebElement userNameField = domHandler.waitAndGetElement("//*[@id=\"login-form-username\"]");
        userNameField.sendKeys(userName);

        WebElement passwordField = domHandler.getElement("//*[@id=\"login-form-password\"]");
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void loginValidCredentials() {
        login(JIRA_USER_NAME, JIRA_PASSWORD);
    }

    public void logout() {
        loginValidCredentials();

        String profileMenuXpath = "//*[@id=\"header-details-user-fullname\"]";
        WebElement profileMenu = domHandler.waitAndGetElement(profileMenuXpath);
        profileMenu.click();

        String logoutXpath = "//*[@id='log_out']";
        WebElement logoutOption = domHandler.waitAndGetElement(logoutXpath);
        logoutOption.click();
    }

    public void secondaryLoginValid(){
        String secondaryLoginUrl = "https://jira.codecool.codecanvas.hu/login.jsp";
        driver.get(secondaryLoginUrl);

        String userNameXPath = "//*[@id=\"login-form-username\"]";
        WebElement userNameField = domHandler.getElement(userNameXPath);
        userNameField.sendKeys(JIRA_USER_NAME);

        String passwordXPath = "//*[@id=\"login-form-password\"]";
        WebElement passwordField = domHandler.getElement(passwordXPath);
        passwordField.sendKeys(JIRA_PASSWORD);
        passwordField.submit();
    }

}
