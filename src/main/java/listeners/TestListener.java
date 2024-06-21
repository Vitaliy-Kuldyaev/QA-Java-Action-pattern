package listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;


import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class TestListener implements ITestListener, IInvokedMethodListener {

    @Attachment(value = "{name}", type = "image/png")
    public byte[] saveScreenShoot(String name) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static String getTestMethodName(ITestResult iTestResult) {
     return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if (iInvokedMethod.isTestMethod()) {
            try {
                if (hasWebDriverStarted()){
                    if(iTestResult.getStatus()==2) {
                        //
                        saveScreenShoot("Скриншот последнего шага");}
                }
            } finally {
                if (hasWebDriverStarted() && iInvokedMethod.isTestMethod()) {
                    try {
                        getWebDriver().close();
                        getWebDriver().quit();
                    }catch (NoSuchWindowException exception){
                        getWebDriver().quit();
                    }

                }
            }
        }
    }


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {}

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestFailure(ITestResult iTestResult) {}

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}

}

