package com.itsmerishab08.user_service.service;

import com.itsmerishab08.user_service.request.UserRequest;
import com.itsmerishab08.user_service.response.BaseResponse;
import com.itsmerishab08.user_service.response.UserResponse;

public interface UserService {

    BaseResponse createUpdateUser(UserRequest userRequest);

    UserResponse getUserById(UserRequest userRequest);

    BaseResponse deleteUserById(UserRequest userRequest);

    UserResponse getUserListing(UserRequest userRequest);
}
