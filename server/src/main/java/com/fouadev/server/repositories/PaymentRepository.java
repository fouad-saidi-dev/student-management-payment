package com.fouadev.server.repositories;
/*
 Created by : Fouad SAIDI on 22/04/2024
 @author : Fouad SAIDI
 @date : 22/04/2024
 @project : student-management-payment
*/

import com.fouadev.server.entities.Payment;
import com.fouadev.server.entities.PaymentStatus;
import com.fouadev.server.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}