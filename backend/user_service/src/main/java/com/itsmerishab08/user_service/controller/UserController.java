package com.itsmerishab08.user_service.controller;

import com.itsmerishab08.user_service.request.UserRequest;
import com.itsmerishab08.user_service.response.BaseResponse;
import com.itsmerishab08.user_service.response.UserResponse;
import com.itsmerishab08.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get_user")
    public UserResponse getUserById(@RequestParam(value = "user_id", required = true)String userId) {
       UserRequest userRequest = new UserRequest();
       userRequest.setUserId(userId);
        return userService.getUserById(userRequest);
    }

    @GetMapping("/get_user_listing")
    public UserResponse getUserListing(@RequestParam(value = "page_no", required = false) int pageNo,
                                       @RequestParam(value = "page_size",required = false) int pageSize) {
        UserRequest userRequest = new UserRequest();
        userRequest.setPageNo(pageNo);
        userRequest.setPageSize(pageSize);
        return userService.getUserListing(userRequest);
    }

    @PostMapping("/delete_user")
    public BaseResponse deleteUserById(@RequestBody UserRequest userRequest) {
        return userService.deleteUserById(userRequest);
    }

    @PostMapping("/create_update_user")
    public BaseResponse createUpdateUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createUpdateUser(userRequest);
    }


}
