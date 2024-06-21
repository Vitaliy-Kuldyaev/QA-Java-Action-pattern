package enums;

public enum Users {
    MAIN_USER("Администратор", Stands.getActiveStand().getUserName(), Stands.getActiveStand().getPassword())
;
    private final String name;
    private final String login;
    private final String pass;

    Users(String name, String login, String pass) {
        this.name = name;
        this.login = login;
        this.pass = pass;

    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}
