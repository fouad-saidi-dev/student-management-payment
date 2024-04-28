package com.fouadev.server.entities;
/*
 Created by : Fouad SAIDI on 22/04/2024
 @author : Fouad SAIDI
 @date : 22/04/2024
 @project : student-management-payment
*/

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity(name = "payments")
@AllArgsConstructor @NoArgsConstructor @Data @Builder @ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
    private String file;
    @ManyToOne
    private Student student;
}