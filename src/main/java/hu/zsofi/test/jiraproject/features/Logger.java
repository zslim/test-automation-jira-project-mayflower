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

        WebElement userNameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        userNameField.sendKeys(userName);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
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
}
