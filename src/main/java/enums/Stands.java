package enums;

import static utils.Utils.hostProperty;


public enum Stands {
    local(
            "testStand",
            4444,
            "testStand",
            "test",
            "test",
            "qwe",
            "https://www.google.ru/"
    ),
    dev(
            hostProperty("host","http://localhost:4445/wd/hub"),
            4444,
            "none",
            "none",
            "none",
            "none",
            "none"
    ),

    preProd(
            hostProperty("host","http://localhost:4445/wd/hub"),
            4444,
            "none",
            "none",
            "none",
            "none",
            "none"
    );

    private final String host;
    private final int port;
    private final String databaseName;
    private final String databaseHost;
    private final String userName;
    private final String password;
    private final String webURL;

    Stands(String host, int port, String databaseHost, String databaseName, String userName, String password, String webURL) {
        this.host = host;
        this.port = port;
        this.databaseHost = databaseHost;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        this.webURL = webURL;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getHost() {
        return host;
    }

    public static Stands getActiveStand() {
        String activeStand = System.getenv("stand");
        if (activeStand == null) activeStand = System.getProperty("stand");
        if (activeStand != null) return valueOf(activeStand);
        return local;
    }

    public static String Url() {
        String activeStand = System.getenv("stand");
        if (activeStand == null) activeStand = System.getProperty("stand");
        if (activeStand != null) return valueOf(activeStand).getWebURL();
        return local.getWebURL();
    }


}
