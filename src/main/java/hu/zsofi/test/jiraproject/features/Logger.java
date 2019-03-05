package hu.zsofi.test.jiraproject.features;

import org.openqa.selenium.*;


public class Logger {

    private WebDriver driver;
    private String baseUrl;
    private static final String JIRA_USER_NAME = System.getenv("JIRA_USER_NAME");
    private static final String JIRA_PASSWORD = System.getenv("JIRA_PASSWORD");

    public WebDriver getDriver() {
        return driver;
    }

    public Logger(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    public void closeDriver() {
        driver.close();
    }

    public void login(String userName, String password) {
        driver.get(baseUrl);

        WebElement userNameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        userNameField.sendKeys(userName);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void loginValidCredentials(){
        login(JIRA_USER_NAME, JIRA_PASSWORD);
    }
}
