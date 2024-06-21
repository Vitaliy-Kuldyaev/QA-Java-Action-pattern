package tests;

import base.BaseTest;
import enums.Stands;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import page.MainPage;
import steps.Group.MainPageSteps;

import static utils.Utils.findInLink;
import static utils.Utils.nameBtn;


public class Test extends BaseTest {

    @org.testng.annotations.Test(description = "Проверка открытия сайта JetBrains")
    @TmsLink("148")
    @Link(type = "manual", value = "148")
    public void test1() {
        MainPage mainPage = new MainPage();
        ACTIONS_.open(Stands.Url())
                .click(mainPage.searchButton)
                .step(MainPageSteps.clickToSearchButton())
                .set(mainPage.getSearchButton(),"JetBrains.com","поиск JetBrains")
                .click(nameBtn("Поиск в Google"), "кнопка Найти")
                .click(findInLink("jetbrains.com"),"нажимаем на jetbrains.com")
                .checkContains(mainPage.getTitleText(),"JetBrains: Essential tools for software developers and teams","сайт JetBrains открылся");
    }
}

