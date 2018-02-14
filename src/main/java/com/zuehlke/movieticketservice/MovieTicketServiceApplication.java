package com.zuehlke.movieticketservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketServiceApplication.class, args);
	}

	@Value("${endpoint.movie-service}")
	private String movieServiceURL;

	@Value("${endpoint.movie-rating-service}")
	private String movieRatingURL;

	@Bean
	public MovieServiceAdapter createMovieServiceAdapter() {
		return new MovieServiceAdapter(this.movieServiceURL);
	}

	@Bean
	public RatingAdapter createRatingAdapter() {
		return new RatingAdapter(this.movieRatingURL);
	}
}
