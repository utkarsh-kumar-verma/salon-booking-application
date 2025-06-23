package com.itsmerishab08.serviceofferingservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "serviceDB")
public class Service {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false)
    private String salonId;
    @Column(nullable = false)
    private String categoryId;
    private String imageUrl;

}
