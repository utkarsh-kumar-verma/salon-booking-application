package com.itsmerishab08.salonservice.dao;

import com.itsmerishab08.salonservice.entity.SalonEntity;
import com.itsmerishab08.salonservice.repository.SalonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalonServiceDAO {

    private final SalonRepository salonRepository;

    public String saveSalon(SalonEntity salonEntity) {
        return salonRepository.save(salonEntity).getId();
    }

    public SalonEntity getSalonById(String id) {
        return salonRepository.findByIdAndDeletedAtIsNull(id);
    }

    public Page<SalonEntity> getAllSalons(Pageable pageable) {
        return salonRepository.findAllByDeletedAtIsNull(pageable);
    }

    public Page<SalonEntity> getSalonsByOwner(String ownerId, Pageable pageable) {
        return salonRepository.findAllByOwnerIdAndDeletedAtIsNull(ownerId, pageable);
    }

    public Page<SalonEntity> getSalonsByCity(String city, Pageable pageable) {
        return salonRepository.findAllByCityContainsIgnoreCaseAndDeletedAtIsNull(city, pageable);
    }
}
