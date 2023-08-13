package com.example.moviebooking.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
@Data
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    @Column(name="USER_ID")
    private long userId;
    @Column(name="ORDER_STATUS")
    private String orderStatus;
    @Column(name="PAYMENT_STATUS")
    private String paymentStatus;
    @Column(name="NUM_OF_SEATS")
    private int numOfSeats;


}
