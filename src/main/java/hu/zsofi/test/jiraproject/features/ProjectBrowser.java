package hu.zsofi.test.jiraproject.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProjectBrowser extends Feature {

    private Logger logger;

    public ProjectBrowser(Logger logger) {
        super(logger.getDriver());
        this.logger = logger;
    }

    public void navigateToBrowsePageVisually() {
        logger.loginValidCredentials();

        String projectMenuXpath = "//*[@id=\"browse_link\"]";
        WebElement projectMenuLink = domHandler.waitAndGetElement(projectMenuXpath);
        projectMenuLink.click();

        WebElement projectViewOption = driver.findElement(By.xpath("//*[@id=\"project_view_all_link_lnk\"]"));
        projectViewOption.click();
    }
}
