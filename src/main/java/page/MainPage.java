package page;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends BasePage {
    public SelenideElement searchButton = $x("//*[@name='q']");

    public SelenideElement getSearchButton() {
        return searchButton;
    }
}
