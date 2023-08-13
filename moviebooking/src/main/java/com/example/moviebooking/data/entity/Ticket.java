package com.example.moviebooking.data.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "TICKET")
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
    @Column(name = "THEATRE_ID")
    private long theatreId;
    @Column(name = "ORDER_ID")
    private long orderId;

}
