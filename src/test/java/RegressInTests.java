
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class RegressInTests extends TestBase {

    @Test
    @DisplayName("Получение списка пользователей")
    public void getUsersListTest() {
        given()
                .log().uri()
                .when()
                .queryParam("page", "2")
                .get("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2));

    }

    @Test
    @DisplayName("Получение данных пользователя")
    public void getSingleUserTest() {
        given()
                .log().uri()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));

    }

    @Test
    @DisplayName("Создание пользователя")
    public void createUserTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .header("x-api-key", API_KEY)
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));

    }

    @Test
    @DisplayName("Обновление данных пользователя")
    public void updateUserTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
        given()
                .header("x-api-key", API_KEY)
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void deleteUserTest() {
        given()
                .header("x-api-key", API_KEY)
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
