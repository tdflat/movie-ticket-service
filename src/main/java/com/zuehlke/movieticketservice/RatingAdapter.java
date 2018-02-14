package com.zuehlke.movieticketservice;

import java.util.List;
import java.util.stream.Collectors;

public class RatingAdapter {

    private final RatingServiceApiClient ratingServiceApiClient;
    public RatingAdapter(String url) {
        ratingServiceApiClient = RestClientFactory.createClient(url, RatingServiceApiClient.class);
    }

    public List<Rating> getRatingsById(long id) {
        return map(ratingServiceApiClient.getRatingsById(id));
    }

    private List<Rating> map(List<RatingServiceResponse> ratingsById) {
        return ratingsById.stream()
                .map(r -> new Rating(r.getSource(), r.getValue())).collect(Collectors.toList());
    }
}
