package com.itsmerishab08.user_service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {
    @JsonProperty("page_no")
    private int pageNo = 1;
    @JsonProperty("page_size")
    private int pageSize = 10;
}
