package com.example.theatreservice.data.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Ticket {
    @Id
    @Column(name = "TICKET_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketId;
    @Column(name = "SCREENING_ID")
    private long screeningId;
    @Column(name = "SEAT_NUM")
    private int seatNum;

}
