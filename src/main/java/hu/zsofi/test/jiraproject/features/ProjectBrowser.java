package hu.zsofi.test.jiraproject.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectBrowser {

    private Logger logger;
    private WebDriver driver;

    public ProjectBrowser(Logger logger) {
        this.logger = logger;
        this.driver = logger.getDriver();
    }

    public void closeDriver() {
        driver.close();
    }

    public void navigateToBrowsePageVisually() {
        logger.loginValidCredentials();

        WebElement projectMenuLink = driver.findElement(By.xpath("//*[@id=\"browse_link\"]"));
        projectMenuLink.click();

        WebElement projectViewOption = driver.findElement(By.xpath("//*[@id=\"project_view_all_link_lnk\"]"));
        projectViewOption.click();
    }
}
