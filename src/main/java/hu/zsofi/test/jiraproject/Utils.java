package hu.zsofi.test.jiraproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static void waitForContentLoad(WebDriver driver, String xpath) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(_driver -> _driver.findElement(By.xpath(xpath)));
    }
}