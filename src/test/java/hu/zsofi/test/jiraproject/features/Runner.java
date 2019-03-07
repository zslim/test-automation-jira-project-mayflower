package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.DomHandler;
import hu.zsofi.test.jiraproject.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Runner {
    public static void main(String[] args) {
        Utils.setDriverPath();
        Logger logger = new Logger(new FirefoxDriver(), Utils.getBaseUrl());
        WebDriver driver = logger.getDriver();
        ProjectBrowser projectBrowser = new ProjectBrowser(logger);
        DomHandler domHandler = new DomHandler(driver);

        logger.loginValidCredentials();
        System.out.println("submitted login data");

        domHandler.waitForPageLoad(); // TODO: show this to Márk
        driver.get("http://codecool.com");  // TODO: if this line is after the login it breaks login but not if it's after projectMenu.click()
//        domHandler.waitForElementLoad("//h1");
//        System.out.println("some content loaded");

        String projectMenuXpath = "//*[@id=\"browse_link\"]";
        WebElement projectMenuLink = domHandler.waitAndGetElement(projectMenuXpath);
        projectMenuLink.click();

        projectBrowser.closeDriver();
    }
}
