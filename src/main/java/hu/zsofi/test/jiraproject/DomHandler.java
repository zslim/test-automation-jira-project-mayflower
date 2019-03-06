package hu.zsofi.test.jiraproject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DomHandler {
    private static final int TIMEOUT_FOR_LOADING = 10;
    private WebDriver driver;

    public DomHandler(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void waitForElementLoad(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_FOR_LOADING);
        wait.until(_driver -> _driver.findElement(By.xpath(xpath)));
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_FOR_LOADING);
        wait.until(_driver -> (JavascriptExecutor)_driver).executeScript("return document.readyState").equals("complete");
    }

    public boolean isElementPresent(String elementXpath) {
        try {
            driver.findElement(By.xpath(elementXpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement waitAndGetElement(String xpath) {
        waitForElementLoad(xpath);
        return getElement(xpath);
    }
}
