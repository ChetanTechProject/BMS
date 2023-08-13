package com.example.moviebooking.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "THEATRE")
@Data
public class Theatre {
    @Id
    @Column(name = "THEATRE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long theatreId;
    @Column(name = "THEATRE_NAME")
    private String theatreName;
    @Column(name = "THEATRE_CITY")
    private String theatreCity;

}
