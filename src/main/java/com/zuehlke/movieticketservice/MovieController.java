package com.zuehlke.movieticketservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    private final MovieServiceAdapter movieServiceAdapter;
    private final RatingAdapter ratingAdapter;

    public MovieController(MovieServiceAdapter movieServiceAdapter, RatingAdapter ratingAdapter) {
        this.movieServiceAdapter = movieServiceAdapter;
        this.ratingAdapter = ratingAdapter;
    }

    @GetMapping(value = "/movies", produces = "application/json")
    public List<Movie> movies() {
        return movieServiceAdapter.getAll();
        /*
        return asList(
                new Movie(1, "Batman Begins", "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg"),
                new Movie(2, "Ted", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ1OTU0ODcxMV5BMl5BanBnXkFtZTcwOTMxNTUwOA@@._V1_SX300.jpg"),
                new Movie(3, "Inception", "https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg"));
*/
    }

    @GetMapping(value = "/movies/{id}", produces = "application/json")
    public MovieDetail movie(@PathVariable("id") int id) {
        Optional<MovieDetail> movieById = movieServiceAdapter.getMovieById(id);
        List<Rating> ratingsById = ratingAdapter.getRatingsById(id);
        return movieById.map(m -> m.setRatings(ratingsById))
                .orElseThrow(() -> new MovieNotFoundException("No movie found with id=" + id));
/*
        return new MovieDetail(1,
                "Batman Begins",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg",
                "After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from the corruption that Scarecrow and the League of Shadows have cast upon it.",
                2005,
                "Action",
                asList(
                        new Rating("source1", "value1"),
                        new Rating("source2", "value2"))
        );
*/
        /*return "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Batman Begins\",\n" +
                "  \"poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg\",\n" +
                "  \"plot\": \"After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from the corruption that Scarecrow and the League of Shadows have cast upon it.\",\n" +
                "  \"year\": 2005,\n" +
                "  \"genre\": \"Action\",\n" +
                "  \"ratings\": [\n" +
                "    {\n" +
                "      \"source\": \"Internet Movie Database\",\n" +
                "      \"value\": \"8.3/10\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"source\": \"Rotten Tomatoes\",\n" +
                "      \"value\": \"84%\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
                */
    }
}
