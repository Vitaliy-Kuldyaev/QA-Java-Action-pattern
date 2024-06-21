package base;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    private SelenideElement title = $(By.xpath("//title"));

    public String getTitleText() {
        return title.text();
    }
}
