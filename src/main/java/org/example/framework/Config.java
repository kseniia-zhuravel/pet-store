package org.example.framework;

import static org.example.framework.PropertyLoader.getProperty;

public class Config {

    public static String restApiBaseUrl = getProperty("REST_API_BASE_URL");
    public static Boolean logsEnabled = Boolean.parseBoolean(getProperty("LOGS_ENABLED"));

}

