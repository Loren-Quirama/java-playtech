package com.useradministration.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;
    private LocalDate date_birth;
    private String address;
    private String token;
    private String password;
    @Column(name = "mobile_phone")
    private String mobilePhone;
    private String email;

}
