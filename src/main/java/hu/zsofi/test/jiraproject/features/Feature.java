package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.DomHandler;
import org.openqa.selenium.WebDriver;

public abstract class Feature {
    protected WebDriver driver;
    protected DomHandler domHandler;

    protected Feature(WebDriver driver) {
        this.driver = driver;
        this.domHandler = new DomHandler(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        driver.close();
    }
}
