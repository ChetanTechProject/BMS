package com.example.moviebooking.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MOVIE")
@Data
public class Movie {
    @Id
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "MOVIE_ID")
    private long movieId;
    @Column(name = "MOVIE_POSTER_URL")
    private String moviePosterUrl;
    @Column(name = "MOVIE_TAGS")
    private String movieTags;

}
