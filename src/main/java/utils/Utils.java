package utils;


import base.BaseTest;
import com.codeborne.selenide.SelenideElement;
import enums.Stands;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.saveData.SaveDataInThread;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Utils extends BaseTest {
    public static void execQuery(String query, Object... params) {
        DB.connect(Stands.getActiveStand()).setAutoCommit(true);
        DB.exec(query, params);
        DB.disconnect();
    }

    public static List<Map> doSelect(String query) {
        DB.connect(Stands.getActiveStand()).setAutoCommit(true);
        List<Map> result = DB.findAll(query);
        DB.disconnect();
        return result;
    }

    @Step("Получение промежуточных данных по ключу \"{key}\"")
    public static String getSave(String key) {
        return SaveDataInThread.getSaveValue(key);
    }

    public static boolean isClickable(SelenideElement se)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofMillis(5000));
            wait.until(ExpectedConditions.elementToBeClickable(se));
            return true;
        }
        catch (ElementClickInterceptedException e)
        {
            return false;
        }
    }

    public static void sleeps(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String hostProperty(String propertyName, String defaultValue) {
        final String hostEnv = Objects.nonNull(System.getenv(propertyName)) ? System.getenv(propertyName) : "";
        final String hostProp = Objects.nonNull(System.getProperty(propertyName)) ? System.getProperty(propertyName) : "";
        if (!Objects.equals(hostEnv, "")) {
            //System.out.println("Set host from Environment= "+hostEnv);
            return hostEnv;
        }
        if (!Objects.equals(hostProp, "")) {
            //System.out.println("Set host from Property = "+hostProp);
            return hostProp;
        }
        //System.out.println("Set host (default) = "+defaultValue);
        return defaultValue;
    }

    public static SelenideElement nameBtn(String nameBtn) {
        return $(By.xpath("//*[@aria-label='"+nameBtn+"']"));
    }
    public static SelenideElement findInLink(String linkText) {
        return $$(By.xpath("//cite[contains(text(),'jetbrains.com')]")).get(0);
    }


}
