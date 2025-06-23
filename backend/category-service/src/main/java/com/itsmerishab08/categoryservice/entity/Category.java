package com.itsmerishab08.categoryservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "categoryDB")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;

    @Column(nullable = false)
    private String name;

    private String image;

    private String description;

    @Column(nullable = false)
    private String salonId;

    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
    private Date deletedAt;
    private String deletedBy;
}
