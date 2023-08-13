package com.example.theatreservice.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SEAT")
@Data
public class Seat {
    @Id
    @Column(name = "SEAT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seatid;
    @Column(name = "ROW_ID")
    private char rowId;
    @Column(name = "SCREEN_ID")
    private long screenId;
    @Column(name = "SEAT_NBR")
    private int seatNbr;

}
