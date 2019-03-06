package hu.zsofi.test.jiraproject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DomHandler {
    private static final int TIMEOUT_FOR_LOADING = 10;
    WebDriver driver;

    public DomHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad(String elementXpath) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_FOR_LOADING);
        wait.until(_driver -> _driver.findElement(By.xpath(elementXpath)));
    }

    public boolean isElementPresent(String elementXpath) {
        try {
            driver.findElement(By.xpath(elementXpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement waitAndGetElement(String elementXpath) {
        waitForLoad(elementXpath);
        return driver.findElement(By.xpath(elementXpath));
    }
}
