package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.DomHandler;
import hu.zsofi.test.jiraproject.StringIntMap;
import hu.zsofi.test.jiraproject.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectBrowserTest {
    private ProjectBrowser projectBrowser;
    private WebDriver driver;
    private DomHandler domHandler;

    @BeforeAll
    static void setup() {
        Utils.setDriverPath();
    }

    @BeforeEach
    void createProjectBrowserInstance() {
        Logger logger = new Logger(new FirefoxDriver(), Utils.getBaseUrl());
        projectBrowser = new ProjectBrowser(logger);
        driver = projectBrowser.getDriver();
        domHandler = new DomHandler(driver);
    }

    @AfterEach
    void closeDriver() {
        projectBrowser.closeDriver();
    }

    @Test
    void testNavigateToBrowsePageVisually() {
        projectBrowser.navigateToBrowsePageVisually();

        String projectListXpath = projectBrowser.getProjectListXpath();
        domHandler.waitForElementLoad(projectListXpath);
        assertTrue(domHandler.isElementPresent(projectListXpath));
    }

    @Test
    void testNavigateToProjectPage() {
        projectBrowser.navigateToBrowsePageVisually();

        String firstProjectLinkXpath = projectBrowser.getFirstProjectLinkXpath();
        WebElement firstProjectLink = domHandler.waitAndGetElement(firstProjectLinkXpath);
        String firstProjectName = firstProjectLink.getAttribute("title");

        projectBrowser.clickFirstProjectLink();

        String projectTitleXpath = "//h1//a";
        WebElement projectTitle = domHandler.waitAndGetElement(projectTitleXpath);
        String nameOfLoadedProject = projectTitle.getAttribute("title");

        assertEquals(firstProjectName, nameOfLoadedProject);
    }

    @Test
    void testProjectListByType() {
        String[] projectTypes = {"Software", "Business"};
        Integer[] expectedNumbers = {7, 3};
        Integer[] zeros = {0, 0};
        StringIntMap<String, Integer> expectedResult = new StringIntMap<>(projectTypes, expectedNumbers);
        StringIntMap<String, Integer> actualResult = new StringIntMap<>(projectTypes, zeros);

        projectBrowser.navigateToBrowsePageVisually();

        String projectTypeImgXpath = "//div[@id='projects']//img[@class='project-type-icon']";
        List<WebElement> projectTypeImgs = domHandler.getListOfElements(projectTypeImgXpath);

        for (WebElement img :
                projectTypeImgs) {

            String projectType = img.getAttribute("title");
            actualResult.incrementValueByKey(projectType);
        }

        assertEquals(expectedResult, actualResult);
    }
}