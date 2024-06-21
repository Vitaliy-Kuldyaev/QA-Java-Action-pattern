package steps.Group;

import actions.Action;

import page.MainPage;


public class MainPageSteps extends Steps {

    public static MainPageSteps clickToSearchButton() {
        MainPage MAIN_PAGE = new MainPage();
        Action ACTIONS_ = new Action();
        ACTIONS_.click(MAIN_PAGE.getSearchButton());
        return null;
    }


}

