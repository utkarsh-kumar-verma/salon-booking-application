package com.itsmerishab08.user_service.repositry;

import com.itsmerishab08.user_service.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUserIdAndDeletedAtIsNull(String id);

    Page<UserEntity> findByDeletedAtIsNull(Pageable pageable);
}
