package com.itsmerishab08.user_service.dao;

import com.itsmerishab08.user_service.entity.UserEntity;
import com.itsmerishab08.user_service.repositry.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceDAO {

    private final UserRepository userRepository;

    public String save(UserEntity user) {
        return userRepository.save(user).getUserId();
    }

    public UserEntity findByUserId(String userId) {
        return userRepository.findByUserIdAndDeletedAtIsNull(userId);
    }

    public Page<UserEntity> findByDeletedAtIsNull(Pageable pageable) {
        return userRepository.findByDeletedAtIsNull(pageable);
    }



}
