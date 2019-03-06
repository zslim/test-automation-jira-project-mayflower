package hu.zsofi.test.jiraproject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    private static final String BASE_URL = "https://jira.codecool.codecanvas.hu/";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setDriverPath() {
        String driverProperty = System.getenv("DRIVER_PROPERTY");
        String driverPath = System.getenv("DRIVER_PATH");
        System.setProperty(driverProperty, driverPath);
    }

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