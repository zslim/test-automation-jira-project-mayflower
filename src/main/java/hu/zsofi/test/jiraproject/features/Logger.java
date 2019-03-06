package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.Utils;
import org.openqa.selenium.*;


public class Logger {
    private String baseUrl;
    private WebDriver driver;
    private static final String JIRA_USER_NAME = System.getenv("JIRA_USER_NAME");
    private static final String JIRA_PASSWORD = System.getenv("JIRA_PASSWORD");

    public WebDriver getDriver() {
        return driver;
    }

    public Logger(WebDriver driver, String url) {
        this.driver = driver;
        this.baseUrl = url;
    }

    public void closeDriver() {
        driver.close();
    }

    public void login(String userName, String password) {
        driver.get(baseUrl);

        setUserData(userName, password);
    }

    public void loginValidCredentials() {
        login(JIRA_USER_NAME, JIRA_PASSWORD);
    }

    public void logout() {
        loginValidCredentials();

        String profileMenuXpath = "//*[@id=\"header-details-user-fullname\"]";
        Utils.waitForContentLoad(driver, profileMenuXpath);

        WebElement profileMenu = driver.findElement(By.xpath(profileMenuXpath));
        profileMenu.click();

        String logoutXpath = "//*[@id='log_out']";
        Utils.waitForContentLoad(driver, logoutXpath);

        WebElement logoutOption = driver.findElement(By.xpath(logoutXpath));
        logoutOption.click();
    }

    public void secondaryLogin(){
        String secondaryLoginUrl = "https://jira.codecool.codecanvas.hu/login.jsp";
        driver.get(secondaryLoginUrl);

        setUserData(JIRA_USER_NAME, JIRA_PASSWORD);
    }

    private void setUserData(String jiraUserName, String jiraPassword) {
        String userNameXPath = "//*[@id=\"login-form-username\"]";
        WebElement userNameField = driver.findElement(By.xpath(userNameXPath));
        userNameField.sendKeys(jiraUserName);

        String passwordXPath = "//*[@id=\"login-form-password\"]";
        WebElement passwordField = driver.findElement(By.xpath(passwordXPath));
        passwordField.sendKeys(jiraPassword);
        passwordField.submit();
    }
}
