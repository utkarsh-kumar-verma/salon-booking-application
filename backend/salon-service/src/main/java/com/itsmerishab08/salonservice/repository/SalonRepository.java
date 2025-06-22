package com.itsmerishab08.salonservice.repository;

import com.itsmerishab08.salonservice.entity.SalonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<SalonEntity, String> {

    SalonEntity findByIdAndDeletedAtIsNull(String id);
    Page<SalonEntity> findAllByDeletedAtIsNull(Pageable pageable);
    Page<SalonEntity> findAllByOwnerIdAndDeletedAtIsNull(String ownerId, Pageable pageable);
    Page<SalonEntity> findAllByCityContainsIgnoreCaseAndDeletedAtIsNull(String city, Pageable pageable);
}
