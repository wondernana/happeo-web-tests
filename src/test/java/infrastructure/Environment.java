package infrastructure;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    public static String getURL() {
        // could be used to run against different environments, like dev, test, prod etc

        String env = "staging";

        if(System.getenv().containsKey("env")) {
            env = System.getenv("env");
        }

        if(System.getProperties().contains("env")) {
            env = System.getProperty("env");
        }

        Map<String, String> urls = new HashMap<>();
        urls.put("staging", "https://staging.unvrs.io");
        return urls.get(env);
    }
}
