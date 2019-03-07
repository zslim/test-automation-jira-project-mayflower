package hu.zsofi.test.jiraproject;

public class Utils {

    private static final String BASE_URL = "https://jira.codecool.codecanvas.hu/";  // TODO: this should be public with no getter

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setDriverPath() {
        String driverProperty = System.getenv("DRIVER_PROPERTY");
        String driverPath = System.getenv("DRIVER_PATH");
        System.setProperty(driverProperty, driverPath);
    }

}