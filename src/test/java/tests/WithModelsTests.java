package tests;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.RequestSpec.*;
import static specs.ResponseSpec.responseSpec;

public class WithModelsTests extends TestBase{


    @Test
    @Tag("api")
    @DisplayName("Получение списка пользователей")
    public void getUsersListTest() {
        UsersListResponse response = step("Отправить запрос на получение списка пользователей", () ->
        given()
                .spec(request)
                .when()
                .queryParam("page", "2")
                .get("/users")
                .then()
                .spec(responseSpec(200))
                .extract().as(UsersListResponse.class));

        step("Проверить параметры", () -> {
            assertThat(response.getPage()).isEqualTo(2);
            assertThat(response.getPer_page()).isEqualTo(6);
            assertThat(response.getTotal()).isEqualTo(12);
            assertThat(response.getTotal_pages()).isEqualTo(2);
        });

    }

    @Test
    @Tag("api")
    @DisplayName("Получение данных пользователя")
    public void getSingleUserTest() {
        UserDataResponseModel response = step("Отправить запрос на получение данных", () ->
                given()
                        .spec(request)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(UserDataResponseModel.class));

        UserDataModel data = response.getData();

        step("Проверить id пользователя", () ->
                assertThat(data.getId()).isEqualTo(2));
        step("Проверить email пользователя", () ->
                assertThat(data.getEmail()).isEqualTo("janet.weaver@reqres.in"));
        step("Проверить имя пользователя", () ->
                assertThat(data.getFirstName()).isEqualTo("Janet"));
        step("Проверить фамилию пользователя", () ->
                assertThat(data.getLastName()).isEqualTo("Weaver"));
    }

    @Test
    @Tag("api")
    @DisplayName("Создание пользователя")
    public void createUserTest() {
        LoginUserLombok data = new LoginUserLombok();
        data.setName("morpheus");
        data.setJob("leader");

        UserBodyResponseLombok user = step("Отправить запрос на создание пользователя", () ->
                given()
                        .spec(request)
                        .body(data)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec(201))
                        .extract().as(UserBodyResponseLombok.class));

        step("Проверить имя пользователя", () ->
                assertThat(user.getName()).isEqualTo("morpheus"));

        step("Проверить должность пользователя", () ->
                assertThat(user.getJob()).isEqualTo("leader"));
    }

    @Test
    @Tag("api")
    @DisplayName("Обновление данных пользователя")
    public void updateUserTest() {
        LoginUserLombok data = new LoginUserLombok();
        data.setName("morpheus");
        data.setJob("zion resident");

        UserBodyResponseLombok user = step("Отправить запрос на обновление данных пользователя", () ->
                given()
                        .spec(request)
                        .body(data)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(responseSpec(200))
                        .extract().as(UserBodyResponseLombok.class));

        step("Проверить имя пользователя", () ->
                assertThat(user.getName()).isEqualTo("morpheus"));

        step("Проверить изменение должности пользователя", () ->
                assertThat(user.getJob()).isEqualTo("zion resident"));

    }

    @Test
    @Tag("api")
    @DisplayName("Удаления  пользователя")
    void deleteUser() {

        step("Отправить запрос на удаление", () ->
                given()
                        .spec(request)
                        .log().uri()
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(responseSpec(204)));
    }
}



