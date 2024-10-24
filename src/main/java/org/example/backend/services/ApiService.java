package org.example.backend.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.framework.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiService {

    private static final RequestSpecification BASIC_REQUEST_SPEC =
            new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .setBaseUri(Config.restApiBaseUrl)
                    .addFilters(getFilters())
                    .build();

    private static final ResponseSpecification BASIC_RESPONSE_SPEC =
            new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .build();

    private static List<Filter> getFilters() {
        if (Config.logsEnabled)
            return Arrays.asList(
                    new RequestLoggingFilter(),
                    new ResponseLoggingFilter(),
                    new AllureRestAssured()
            );
        return Collections.emptyList();
    }

    public static <T> Response sendPostRequest(String path, T body) {
        return given()
                .spec(BASIC_REQUEST_SPEC)
                .body(body)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public static <T> T sendPostRequest(String path, T body, Class<T> tClass) {
        return given()
                .spec(BASIC_REQUEST_SPEC)
                .body(body)
                .when()
                .post(path)
                .then()
                .spec(BASIC_RESPONSE_SPEC)
                .extract()
                .response()
                .as(tClass);
    }

    public static Response sendDeleteRequest(String path) {
        return given()
                .spec(BASIC_REQUEST_SPEC)
                .when()
                .delete(path)
                .then()
                .extract()
                .response();
    }

    public static <T> T sendGetRequest(String path, Class<T> tClass) {
        return given()
                .spec(BASIC_REQUEST_SPEC)
                .when()
                .get(path)
                .then()
                .spec(BASIC_RESPONSE_SPEC)
                .extract()
                .response()
                .as(tClass);
    }

    public static Response sendGetRequest(String path) {
        return given()
                .spec(BASIC_REQUEST_SPEC)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }
}