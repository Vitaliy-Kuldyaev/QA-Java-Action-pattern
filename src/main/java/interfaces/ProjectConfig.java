package interfaces;

import annotations.Key;
import annotations.Source;

@Source("classpath:config.properties")
public interface ProjectConfig {
    @Key("app.url")
    String appUrl();
}
