package com.engeto.registration_system;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.MySQLContainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegistrationSystemApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    static {
        mySQLContainer.start();
    }

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test @Order(1)
    void shouldCreateUser() {
        String createUserJson = """
                {
                   "name": "Petr",
                   "surname": "Klement",
                   "personID": "nS7tJ0qR5wGh"
                 }
                """;

        var responseBodyString = given()
                .contentType("application/json")
                .body(createUserJson)
                .when()
                .post("/api/v1/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .body().asString();

        assertThat(responseBodyString, Matchers.is("{\"id\":1,\"name\":\"Petr\",\"surname\":\"Klement\"}"));
    }

    @Test @Order(2)
    void shouldUpdateUser() {
        String updateBody = """
            {
               "name": "Updated",
               "surname": "User"
            }
            """;

        given()
                .contentType("application/json")
                .pathParam("id", 1)
                .body(updateBody)
                .when()
                .put("/api/v1/users/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Updated"))
                .body("surname", equalTo("User"));
    }

    @Test @Order(3)
    void shouldGetAllUsers() {
        given()
                .when()
                .get("/api/v1/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test @Order(4)
    void shouldGetUserById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/api/v1/users/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Updated"))
                .body("surname", equalTo("User"));
    }

    @Test @Order(5)
    void shouldDeleteUser() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/api/v1/users/{id}")
                .then()
                .statusCode(204);

        // verify deletion returns 404
        given()
                .pathParam("id", 1)
                .when()
                .get("/api/v1/users/{id}")
                .then()
                .statusCode(404);
    }

}
