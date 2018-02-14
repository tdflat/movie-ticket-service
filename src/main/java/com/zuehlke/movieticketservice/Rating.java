package com.zuehlke.movieticketservice;

import java.util.Objects;

public class Rating {
    @Override
    public String toString() {
        return "Rating{" +
                "source='" + source + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(source, rating.source) &&
                Objects.equals(value, rating.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(source, value);
    }

    public Rating(String source, String value) {
        this.source = source;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public String getValue() {
        return value;
    }

    private String source;
    private String value;
}
