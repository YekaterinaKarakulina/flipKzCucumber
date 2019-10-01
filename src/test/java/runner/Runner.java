package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import driver.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import service.FileReaderJsonAndProperties;
import tests.businessObjects.User;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/tests/features/flip.feature", glue = "")
public class Runner {
    private static User user;
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static User getUser() {
        return user;
    }

    @BeforeClass
    public static void initUserAndDriver() {
        System.out.println("get user and driver");
        user = FileReaderJsonAndProperties.getUser();
        driver = WebDriverManager.getWebDriverInstance();
    }

    @AfterClass
        public static void closeDriver(){
            System.out.println("close driver");
            driver.close();
    }

}
