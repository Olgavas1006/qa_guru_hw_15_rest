package specs;

import io.restassured.specification.RequestSpecification;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpec {
    public static RequestSpecification request  = with()
            .header("x-api-key", "reqres-free-v1")
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);
}