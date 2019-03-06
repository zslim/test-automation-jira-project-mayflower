package hu.zsofi.test.jiraproject.features;

import org.openqa.selenium.WebDriver;

public abstract class Feature {
    protected WebDriver driver;

    protected Feature(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        driver.close();
    }
}
