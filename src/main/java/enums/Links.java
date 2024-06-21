package enums;

public enum Links {
    local(
            "main page", "https://www.jetbrains.com/"
    );


    private final String name;
    private final String url;

    Links(String name, String url) {
        this.name = name;
        this.url = url;

    }


    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

}


