package utils.drivers;

import com.codeborne.selenide.WebDriverRunner;
import enums.Links;
import enums.Stands;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static utils.Utils.sleeps;

public class WebDriver_chrome {
    private static WebDriver driver =null;
    private static final Logger LOG = LoggerFactory.getLogger(WebDriver_chrome.class);

    public static void openMainUrlWithWait(String url, long waitTime) {
        long startTime = System.currentTimeMillis();
        boolean pageLoaded = false;
        while ((System.currentTimeMillis() - startTime) < waitTime) {
            if (openSite(url)) {
                LOG.info("---");
                LOG.info("--- Страница загружена: " + url);
                LOG.info("---");
                pageLoaded = true;
                break;
            } else {
                sleeps(2000);
            }
        }
        if (!pageLoaded) Assert.fail("Не открылась главная страница сайта за время, msec: " + waitTime);
    }

    public static void openUrlWithWait(Links link) {
        try {
            LOG.info("--- Открываем страницу: " + link.getUrl());
            open(link.getUrl());
        } catch (Exception ex) {
            Assert.fail("Не открылась страница: " + link.getName() + "   url: " + Stands.Url() + link.getUrl());
        }


    }

    private static boolean openSite(String url) {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
        } catch (IllegalStateException is) {
            WebDriverRunner.setWebDriver(getDriver());
        }
        try {
            open(url);
            //Selenide.executeJavaScript("document.body.style.zoom='50%'");
        } catch (Exception ex) {
            LOG.info("ERROR  Страница: " + url + " не открыта");
            LOG.info(ex.getMessage());
        }
        return true;
    }


    public static WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            System.setProperty("selenide.browser", "Chrome");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setBinary("d:\\Chromes\\126.0.6478.63\\chrome-win64\\chrome.exe");
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
            try {
                driver = new ChromeDriver(chromeOptions);
                return driver;
            } catch (Exception ex) {
                LOG.info("ERROR  WebDriver не создан");
                LOG.info(ex.getMessage());
            }
        } else return driver;
        return null;
    }
}
