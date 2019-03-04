package hu.zsofi.test.jiraproject.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        WebElement profileImage = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]//img"));
        String altString = profileImage.getAttribute("alt");
        return altString;
    }
}
