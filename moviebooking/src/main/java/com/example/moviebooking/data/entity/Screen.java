package com.example.moviebooking.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SCREEN")
@Data
public class Screen {
    @Id
    @Column(name = "SCREEN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long screenId;
    @Column(name = "THEATRE_ID")
    private long theatreId;
    @Column(name = "SEATS_NUM")
    private int seatsNum;

}
