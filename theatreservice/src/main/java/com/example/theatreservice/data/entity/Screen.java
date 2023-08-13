package com.example.theatreservice.data.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "SCREEN")
@Data
public class Screen {
    @Id
    @Column(name = "SCREEN_ID")
    private long screenId;
    @Column(name = "THEATRE_ID")
    private long theatreId;
    @Column(name = "SEATS_NUM")
    private int seatsNum;

}
