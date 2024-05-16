package com.fouadev.server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

/*
 Created by : Fouad SAIDI on 22/04/2024
 @author : Fouad SAIDI
 @date : 22/04/2024
 @project : student-management-payment
*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@ToString
@Entity(name = "students")
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String code;
    private String programId;
    private String photo;
}