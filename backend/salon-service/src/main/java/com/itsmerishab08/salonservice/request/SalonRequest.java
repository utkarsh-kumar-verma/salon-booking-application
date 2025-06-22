package com.itsmerishab08.salonservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalonRequest extends BaseRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("images")
    private List<String> images;

    @JsonProperty("address")
    private String address;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("city")
    private String city;

    @JsonProperty("owner_id")
    private String ownerId;

    @JsonProperty("open_time")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime openTime;




    @JsonProperty("close_time")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime closeTime;



//    private LocalTime closeTime;
}
