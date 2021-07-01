package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.anyOf;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/greeting")
          .then()
             .statusCode(200)
             .body(is("Howdy RESTEasy"));
    }

    @Test
    public void testListGreetingsEndpoint() {
        given()
          .when().get("/greeting/list")
          .then()
             .statusCode(200)
             .body("size()", is(3))
             .body("[0]", is("Howdy from app.prop"))
             .body("[1]", is("Hola from app.prop"))
             .body("[2]", is("Hello from app.prop"));
    }

    @Test
    public void testRandomEndpoint() {
        given()
          .when().get("/greeting/list/random")
          .then()
             .statusCode(200)
             .body(anyOf(is("Howdy from app.prop"),
                   is("Hola from app.prop"),
                   is("Hello from app.prop")));
    }
}