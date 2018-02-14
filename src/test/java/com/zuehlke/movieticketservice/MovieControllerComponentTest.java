package com.zuehlke.movieticketservice;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class MovieControllerComponentTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void getMovies() throws Exception {
        when()
                .get("/api/v1/movies").
        then()
                .statusCode(200)
                .body("[0].title", equalTo("Batman Begins"));
    }

    @Test
    public void getMovieById() throws Exception {
        when()
                .get("/api/v1/movies/1").
        then()
                .statusCode(200)
                .body("title", equalTo("Batman Begins"))
                .body("year", equalTo(2005))
                .body("ratings[0].source", equalTo("source1"));
    }

}