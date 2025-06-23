package com.itsmerishab08.categoryservice.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private String message;
    private String status;
    private String userId;

}
