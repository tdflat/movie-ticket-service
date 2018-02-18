package com.zuehlke.movieticketservice;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerComponentTest {


    @MockBean
    private MovieServiceAdapter movieServiceAdapterMock;

    @MockBean
    private RatingAdapter ratingAdapterMock;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void getMovies() throws Exception {
        Mockito.when(ratingAdapterMock.getRatingsById(Mockito.anyLong())).thenReturn(Collections.emptyList());
        Mockito.when(movieServiceAdapterMock.getAll())
                .thenReturn(Arrays.asList(new Movie(1, "Batman Begins", "posterurl")));

        when()
                .get("/api/v1/movies").
        then()
                .statusCode(200)
                .body("[0].title", equalTo("Batman Begins"));
    }

    @Test
    public void getMovieById() throws Exception {
        Mockito.when(movieServiceAdapterMock.getMovieById(1))
                .thenReturn(Optional.of(new MovieDetail(1, "Batman Begins",
                        "posterurl", "plot", 2005, "genre", null
                        )));

        Mockito.when(ratingAdapterMock.getRatingsById(1))
                .thenReturn(Arrays.asList(new Rating("source1", "value1")));

        when()
                .get("/api/v1/movies/1").
        then()
                .statusCode(200)
                .body("title", equalTo("Batman Begins"))
                .body("year", equalTo(2005))
                .body("ratings[0].source", equalTo("source1"));
    }

}