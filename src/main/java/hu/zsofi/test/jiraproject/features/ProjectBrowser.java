package hu.zsofi.test.jiraproject.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProjectBrowser extends Feature {

    private Logger logger;
    private String projectBrowsePageUrl;
    private String projectListXpath;
    private String firstProjectLinkXpath;

    public ProjectBrowser(Logger logger) {
        super(logger.getDriver());
        this.logger = logger;
        this.projectBrowsePageUrl = "https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa";
        this.projectListXpath = "//*[@id=\"projects\"]//tbody[@class=\"projects-list\"]";
        this.firstProjectLinkXpath = projectListXpath + "//td[@class=\"cell-type-name\"][1]/a";
    }

    public String getProjectListXpath() {
        return projectListXpath;
    }

    public String getFirstProjectLinkXpath() {
        return firstProjectLinkXpath;
    }

    public void navigateToBrowsePageVisually() {
        logger.loginValidCredentials();

        String projectMenuXpath = "//*[@id=\"browse_link\"]";
        WebElement projectMenuLink = domHandler.waitAndGetElement(projectMenuXpath);
        projectMenuLink.click();

        String allProjectXpath = "//*[@id=\"project_view_all_link_lnk\"]";
        WebElement projectViewOption = domHandler.waitAndGetElement(allProjectXpath);
        projectViewOption.click();
    }

    public void navigateToBrowsePageUrl() { // TODO: login fails, WHY
        logger.loginValidCredentials();
        driver.get(projectBrowsePageUrl);
    }

    public void clickFirstProjectLink() {
        if (!domHandler.isElementPresent(projectListXpath)) {
            throw new IllegalStateException("Browse Projects page should be loaded when calling this method.");
        }

        /*
        Here I work with the assumption that there are projects in my testing environment although I didn't create this environment.
        Normally as a tester I would create one environment with and one without existing projects.
         */

        WebElement firstProjectLink = domHandler.getElement(firstProjectLinkXpath);
        firstProjectLink.click();
    }
}
