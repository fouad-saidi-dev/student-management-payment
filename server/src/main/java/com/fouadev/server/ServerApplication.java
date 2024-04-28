package com.fouadev.server;

import com.fouadev.server.entities.Payment;
import com.fouadev.server.entities.PaymentStatus;
import com.fouadev.server.entities.PaymentType;
import com.fouadev.server.entities.Student;
import com.fouadev.server.repositories.PaymentRepository;
import com.fouadev.server.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    CommandLineRunner start(StudentRepository studentRepository,
                            PaymentRepository paymentRepository) {
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("fouad").lastName("saidi").code("143678").programId("BDCC").email("fouad.sai@gmail.com").build());

            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("reda").lastName("almai").code("83620").programId("IHDT").email("reda.alm@gmail.com").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("hajar").lastName("adfir").code("73748").programId("RTIA").email("hajar.adf@gmail.com").build());

            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();

            studentRepository.findAll().forEach(student -> {
                for (int i = 0; i < 10; i++) {
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount(1000+(int)(Math.random()+20000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(student)
                            .build();
                    paymentRepository.save(payment);
                }
            });
        };
    }
}
