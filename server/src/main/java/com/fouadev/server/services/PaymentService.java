package com.fouadev.server.services;

import com.fouadev.server.dtos.PaymentDTO;
import com.fouadev.server.entities.Payment;
import com.fouadev.server.entities.PaymentStatus;
import com.fouadev.server.entities.PaymentType;
import com.fouadev.server.entities.Student;
import com.fouadev.server.repositories.PaymentRepository;
import com.fouadev.server.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/*
 Created by : Fouad SAIDI on 22/04/2024
 @author : Fouad SAIDI
 @date : 22/04/2024
 @project : student-management-payment
*/
@Service
@Transactional
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }

    public Payment updatePaymentStatus(PaymentStatus status, Long id) {
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public Payment savePayment(MultipartFile file,
                               PaymentDTO paymentDTO) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"), "data", "payments");
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "data", "payments", fileName + ".pdf");
        Files.copy(file.getInputStream(), filePath);
        Student student = studentRepository.findByCode(paymentDTO.getStudentCode());
        Payment payment = Payment.builder()
                .date(paymentDTO.getDate())
                .type(paymentDTO.getType())
                .student(student)
                .amount(paymentDTO.getAmount())
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();

        return paymentRepository.save(payment);
    }

    public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}