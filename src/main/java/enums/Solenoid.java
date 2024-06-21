package enums;

import static utils.Utils.hostProperty;

public enum Solenoid {
    local(
            hostProperty("host","http://localhost:4444/wd/hub"),
            "http://localhost:4444/",
            4444
    );


    private final String host;
    private final String hostApi;
    private final int port;

    Solenoid(String host, String hostApi, int port) {
        this.host = host;
        this.hostApi = hostApi;
        this.port = port;

    }

    public int getPort() {
        return port;
    }
    public String gethostApi() {
        return hostApi;
    }

    public String getHost() {
        return host;
    }

    public static Solenoid getSolenoidServer() {
        String activeStand = System.getenv("stand");
        if (activeStand == null) activeStand = System.getProperty("stand");
        if (activeStand != null) return valueOf(activeStand);
        return local;
    }
    }
