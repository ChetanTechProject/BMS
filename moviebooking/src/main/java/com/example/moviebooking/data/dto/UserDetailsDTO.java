package com.example.moviebooking.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDTO {

    private String user;
    private long userId;
    private String city;
    private PaymentDTO paymentDto;
}
