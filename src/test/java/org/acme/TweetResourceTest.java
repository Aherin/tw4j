package org.acme;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TweetResourceTest {
    @Test
    void testTweetsEndpoint() {
        given()
          .when().get("/tweets")
          .then()
             .statusCode(200);
    }

    @Test
    void testTweetsValidEndpoint() {
        given()
          .when().get("/tweets/valid")
          .then()
             .statusCode(200);
    }

    @Test
    void testTweetsTrendsEndpoint() {
        given()
          .when().get("/tweets/trends")
          .then()
             .statusCode(200);
    }
}
