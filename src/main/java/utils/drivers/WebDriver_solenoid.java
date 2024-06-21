package utils.drivers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import enums.Links;
import enums.Stands;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import static enums.Solenoid.getSolenoidServer;
import static utils.Utils.sleeps;

public class WebDriver_solenoid {
    private static final Logger LOG = LoggerFactory.getLogger(WebDriver_solenoid.class);

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
        } catch (Exception ex) {
            Selenide.open(link.getUrl());
            Assert.fail("Не открылась страница: " + link.getName() + "   url: " + Stands.Url() + link.getUrl());
        }


    }

    private static boolean openSite(String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", new HashMap<String, Object>() {
            {
                put("profile.default_content_settings.popups", 0);
                put("download.default_directory", "/home/selenium/Downloads/");
                put("download.prompt_for_download", false);
                put("download.directory_upgrade", true);
                put("safebrowsing.enabled", false);
                put("plugins.always_open_pdf_externally", true);
                put("plugins.plugins_disabled", new ArrayList<String>() {
                    {
                        add("Chrome PDF Viewer");
                    }
                });
            }
        });
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setCapability("browserName", "chrome");
        chromeOptions.setCapability("browserVersion", "100.0");
        chromeOptions.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false,
                "sessionTimeout", "5m"
        ));
        try {
            WebDriverRunner.setWebDriver(new RemoteWebDriver(new URL(getSolenoidServer().getHost()), chromeOptions));
            getWebDriver().manage().window().maximize();
            Selenide.open(url);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
