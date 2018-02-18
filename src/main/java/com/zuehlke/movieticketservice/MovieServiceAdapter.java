package com.zuehlke.movieticketservice;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieServiceAdapter implements HealthIndicator{

    private final MovieServiceApiClient moviesApiClient;
    
    public MovieServiceAdapter(String url) {

        moviesApiClient = RestClientFactory.createClient(url, MovieServiceApiClient.class);
    }

    public List<Movie> getAll() {
        return moviesApiClient.getMovies().stream()
                .map(m -> new Movie((int) m.getId(), m.getTitle(), m.getPoster())).collect(Collectors.toList());
    }

    public Optional<MovieDetail> getMovieById(long id) {
        MovieDetail movieById;
        try {
            movieById = map(moviesApiClient.getMovieById(id));
        } catch (HystrixRuntimeException hre) {
            movieById = null;
        }
        return Optional.ofNullable(movieById);
    }

    private MovieDetail map(MovieServiceResponse movieById) {
        return new MovieDetail((int) movieById.getId(), movieById.getTitle(), movieById.getPoster(), movieById.getPlot(),
                movieById.getYear(), movieById.getGenre(), null);
    }

    @Override
    public Health health() {
        return Health.unknown().build();
    }
}
