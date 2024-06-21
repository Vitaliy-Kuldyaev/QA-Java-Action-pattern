package base;

import actions.Action;
import com.codeborne.selenide.Selenide;
import interfaces.ProjectConfig;
import com.codeborne.selenide.WebDriverRunner;
import database.PGBase;
import io.qameta.allure.Step;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import page.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.logging.Logger;

@Listeners(TestListener.class)
public class BaseTest {
    public static final Logger LOGGER = Logger.getLogger("TEST LOGGER");
    public static final PGBase DB = new PGBase();
    public Action ACTIONS_;
    public ProjectConfig config;

    @BeforeSuite
    public void setProjectVariable() {
        Field[] fields = this.getClass().asSubclass(this.getClass()).getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    @BeforeClass
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeMethod
    protected void setUp(Method method) {
        ACTIONS_ = new Action();
        Configuration.savePageSource = false;
        Configuration.reportsFolder = "build/reports/tests";
        Configuration.downloadsFolder = "build/downloads";
        Configuration.pageLoadTimeout = 80000;
    }

    @AfterMethod
    protected void teardown() {
        try {
            Selenide.closeWebDriver();
            //getWebDriver().close();
        } catch (Throwable tr) {
            //
        }
    }

}
