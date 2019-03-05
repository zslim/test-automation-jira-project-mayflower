package hu.zsofi.test.jiraproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static void waitForContentLoad(WebDriver driver, String xpath) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(_driver -> _driver.findElement(By.xpath(xpath)));
    }

    public static boolean isElementPresent(WebDriver driver, String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}