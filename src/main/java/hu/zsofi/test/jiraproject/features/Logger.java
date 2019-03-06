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
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");

        setUserData(JIRA_USER_NAME, JIRA_PASSWORD);
    }

    private void setUserData(String jiraUserName, String jiraPassword) {
        WebElement userNameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        userNameField.sendKeys(jiraUserName);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.sendKeys(jiraPassword);
        passwordField.submit();
    }
}
