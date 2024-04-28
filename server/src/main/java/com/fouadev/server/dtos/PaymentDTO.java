package com.fouadev.server.dtos;

import com.fouadev.server.entities.PaymentStatus;
import com.fouadev.server.entities.PaymentType;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
}