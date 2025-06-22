package com.itsmerishab08.salonservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itsmerishab08.salonservice.model.SalonModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalonResponse extends BaseResponse {

    @JsonProperty("salon")
    private SalonModel salon;

    @JsonProperty("salon_model")
    private List<SalonModel> salons;

    @JsonProperty("total_count")
    private long totalCount;

    @JsonProperty("page_no")
    private int pageNo;

    @JsonProperty("page_size")
    private int pageSize;

}
