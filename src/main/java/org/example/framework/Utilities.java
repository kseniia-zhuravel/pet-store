package org.example.framework;

import io.restassured.response.Response;

public class Utilities {

    public static <T> T extractResponseAs(Response response, Class<T> tClass) {
        return response.then().extract().as(tClass);
    }

}
