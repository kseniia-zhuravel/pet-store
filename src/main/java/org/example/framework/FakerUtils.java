package org.example.framework;

import com.github.javafaker.Faker;

public class FakerUtils {

    private static final Faker faker = new Faker();

    public static String getRandomWord() {
        return faker.lorem().word();
    }

    public static int getRandomInt() {
        return faker.number().numberBetween(2, Integer.MAX_VALUE);
    }


}