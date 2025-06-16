package org.example;

import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import org.api.User;

import static io.restassured.RestAssured.given;

public class UserGenerator {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String USER_PATH = "/api/auth/user";

    private final Faker faker = new Faker();
    private User user;
    private String accessToken;

    public User generateRandomUser() {
        return new User(
                faker.internet().emailAddress(),
                faker.internet().password(6, 12),
                faker.name().username()
        );
    }

    public void registerUser() {
        this.user = generateRandomUser();

        ValidatableResponse response = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(this.user)
                .post(REGISTER_PATH)
                .then();
        this.accessToken = response.extract().path("accessToken");
    }

    public void deleteUser(String token) {
        given()
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .delete(USER_PATH)
                .then();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public User getUser() {
        return this.user;
    }
}