package com.itsmerishab08.user_service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itsmerishab08.user_service.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseResponse {

    @JsonProperty("user_model")
    private UserModel user;

    @JsonProperty("list_of_user_model")
    private List<UserModel> users;

    @JsonProperty("total_count")
    private long totalCount;

    @JsonProperty("page_no")
    private int pageNo;

    @JsonProperty("page_size")
    private int pageSize;
}
