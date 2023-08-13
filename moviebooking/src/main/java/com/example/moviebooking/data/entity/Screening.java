package com.example.moviebooking.data.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SCREENING")
@Data
public class Screening implements Cloneable {
    @Id
    @Column(name = "SCREENING_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long screeningId;
    @Column(name = "THEATRE_ID")
    private long theatreId;
    @Column(name = "SCREEN_ID")
    private long screenId;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "SCREENING_DATE")
    private java.sql.Date screeningDate;
    @Column(name = "SCREENING_TIME")
    private java.sql.Time screeningTime;
    @Column(name = "BOOKED_TICKETS")
    private int bookedTickets;

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
