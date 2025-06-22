package com.itsmerishab08.salonservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "salonDB")
@Builder
public class SalonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    private String description;
    @ElementCollection
    private List<String> images;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String email;
    private String phoneNumber;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String ownerId;
    @Column(nullable = false)
    private LocalTime openTime;
    @Column(nullable = false)
    private LocalTime closeTime;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}

