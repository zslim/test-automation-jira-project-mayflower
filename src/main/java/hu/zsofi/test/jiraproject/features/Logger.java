package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.Utils;
import org.openqa.selenium.*;


public class Logger {

    private WebDriver driver;
    private static final String JIRA_USER_NAME = System.getenv("JIRA_USER_NAME");
    private static final String JIRA_PASSWORD = System.getenv("JIRA_PASSWORD");

    public WebDriver getDriver() {
        return driver;
    }

    public Logger(WebDriver driver) {
        this.driver = driver;
    }

    public void closeDriver() {
        driver.close();
    }

    public void login(String userName, String password, String url) {
        driver.get(url);

        WebElement userNameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        userNameField.sendKeys(userName);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void loginValidCredentials(String Url) {
        login(JIRA_USER_NAME, JIRA_PASSWORD, Url);
    }

    public void logout(String url) {
        loginValidCredentials(url);

        String profileMenuXpath = "//*[@id=\"header-details-user-fullname\"]";
        Utils.waitForContentLoad(driver, profileMenuXpath);

        WebElement profileMenu = driver.findElement(By.xpath(profileMenuXpath));
        profileMenu.click();

        String logoutXpath = "//*[@id='log_out']";
        Utils.waitForContentLoad(driver, logoutXpath);

        WebElement logoutOption = driver.findElement(By.xpath(logoutXpath));
        logoutOption.click();
    }
}
