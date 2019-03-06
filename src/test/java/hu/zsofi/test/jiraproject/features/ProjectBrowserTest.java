package hu.zsofi.test.jiraproject.features;

import hu.zsofi.test.jiraproject.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

class ProjectBrowserTest {
    private ProjectBrowser projectBrowser;

    @BeforeAll
    static void setup() {
        Utils.setDriverPath();
    }

    @BeforeEach
    void createProjectBrowserInstance() {
        Logger logger = new Logger(new FirefoxDriver(), Utils.getBaseUrl());
        projectBrowser = new ProjectBrowser(logger);
    }

    @Test
    void navigateToBrowsePageVisually() {
    }
}