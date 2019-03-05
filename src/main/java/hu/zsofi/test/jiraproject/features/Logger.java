package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.Util;
import org.openqa.selenium.*;


public class Logger {

    private WebDriver driver;
    private String baseUrl;

    public Logger(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    public void closeDriver() {
        driver.close();
    }

    public String login(String userName, String password) {
        driver.get(baseUrl);

        WebElement userNameField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        userNameField.sendKeys(userName);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.sendKeys(password);
        passwordField.submit();

        Util.waitForContentLoad(driver,"//*[@id=\"header-details-user-fullname\"]//img");

        WebElement profileImage = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]//img"));
        String altString = profileImage.getAttribute("alt");
        return altString;
    }
}
