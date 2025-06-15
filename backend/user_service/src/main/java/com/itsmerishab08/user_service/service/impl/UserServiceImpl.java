package com.itsmerishab08.user_service.service.impl;

import com.itsmerishab08.user_service.dao.UserServiceDAO;
import com.itsmerishab08.user_service.entity.UserEntity;
import com.itsmerishab08.user_service.model.UserModel;
import com.itsmerishab08.user_service.request.UserRequest;
import com.itsmerishab08.user_service.response.BaseResponse;
import com.itsmerishab08.user_service.response.UserResponse;
import com.itsmerishab08.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServiceDAO userServiceDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public BaseResponse createUpdateUser(UserRequest userRequest) {
        BaseResponse response = new BaseResponse();
        try {
            UserEntity userEntity = userRequest.getUserId() != null
                    ? userServiceDAO.findByUserId(userRequest.getUserId())
                    : new UserEntity();

            if (userEntity == null && userRequest.getUserId() != null) {
                throw new IllegalArgumentException("User not found");
            }

            userEntity.setFullName(userRequest.getFullName());
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setPhoneNumber(String.valueOf(userRequest.getPhoneNumber()));
            userEntity.setRole(userRequest.getRole());
            userEntity.setUpdatedAt(new Date());

            if (userRequest.getUserId() == null) {
                userEntity.setCreatedAt(new Date());
            }

            String userId = userServiceDAO.save(userEntity);
            response.setUserId(userId);
            response.setStatus(HttpStatus.OK.toString());
        } catch (Exception e) {
            logger.error("Error creating/updating user: {}", e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("Exception in creating/updating user: " + e.getMessage());
        }
        return response;
    }

    @Override
    public UserResponse getUserById(UserRequest userRequest) {
        UserResponse response = new UserResponse();
        try {
            UserEntity userEntity = userServiceDAO.findByUserId(userRequest.getUserId());
            if (userEntity == null) {
                throw new IllegalArgumentException("User not found");
            }

            UserModel userModel = new UserModel();
            userModel.setUserId(userEntity.getUserId());
            userModel.setFullName(userEntity.getFullName());
            userModel.setEmail(userEntity.getEmail());
            userModel.setPhoneNumber(userEntity.getPhoneNumber());
            userModel.setRole(userEntity.getRole());

            response.setUser(userModel);
            response.setStatus(HttpStatus.OK.toString());
        } catch (Exception e) {
            logger.error("Error fetching user: {}", e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("Exception in fetching user: " + e.getMessage());
        }
        return response;
    }

    @Override
    public BaseResponse deleteUserById(UserRequest userRequest) {
        BaseResponse response = new BaseResponse();
        try {
            UserEntity userEntity = userServiceDAO.findByUserId(userRequest.getUserId());
            if (userEntity == null) {
                throw new IllegalArgumentException("User not found");
            }

            userEntity.setDeletedAt(new Date());
            String userId = userServiceDAO.save(userEntity);

            response.setStatus(HttpStatus.OK.toString());
            response.setMessage("User deleted successfully");
            response.setUserId(userId);
        } catch (Exception e) {
            logger.error("Error deleting user: {}", e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("Exception in deleting user: " + e.getMessage());
        }
        return response;
    }

    @Override
    public UserResponse getUserListing(UserRequest userRequest) {
        UserResponse response = new UserResponse();
        try {
            Pageable pageable = PageRequest.of(userRequest.getPageNo() - 1, userRequest.getPageSize());
            Page<UserEntity> userEntities = userServiceDAO.findByDeletedAtIsNull(pageable);

            List<UserModel> userModelList = userEntities.getContent().stream()
                    .map(userEntity -> {
                        UserModel userModel = new UserModel();
                        userModel.setUserId(userEntity.getUserId());
                        userModel.setFullName(userEntity.getFullName());
                        userModel.setEmail(userEntity.getEmail());
                        userModel.setPhoneNumber(userEntity.getPhoneNumber());
                        userModel.setRole(userEntity.getRole());
                        return userModel;
                    })
                    .collect(Collectors.toList());

            response.setUsers(userModelList);
            response.setStatus(HttpStatus.OK.toString());
            response.setMessage("User listing fetched successfully");
            response.setPageNo(userRequest.getPageNo());
            response.setPageSize(userRequest.getPageSize());
            response.setTotalCount(userEntities.getTotalElements());
        } catch (Exception e) {
            logger.error("Error fetching user listing: {}", e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("Exception in fetching user listing: " + e.getMessage());
        }
        return response;
    }
}
