package com.itsmerishab08.categoryservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryModel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("salon_id")
    private String salonId;
    @JsonProperty("image")
    private String image;
}
