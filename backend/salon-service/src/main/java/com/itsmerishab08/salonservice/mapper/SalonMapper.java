package com.itsmerishab08.salonservice.mapper;

import com.itsmerishab08.salonservice.entity.SalonEntity;
import com.itsmerishab08.salonservice.model.SalonModel;
import com.itsmerishab08.salonservice.request.SalonRequest;
import org.springframework.stereotype.Service;


@Service
public class SalonMapper {

    public static SalonEntity toEntity(SalonRequest request) {
        SalonEntity entity = new SalonEntity();

        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setImages(request.getImages());
        entity.setAddress(request.getAddress());
        entity.setEmail(request.getEmail());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setCity(request.getCity());
        entity.setOwnerId(request.getOwnerId());
        entity.setOpenTime(request.getOpenTime());
        entity.setCloseTime(request.getCloseTime());

        return entity;
    }


    public static SalonModel toModel(SalonEntity entity) {
        return new SalonModel(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImages(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getCity(),
                entity.getOwnerId(),
                entity.getOpenTime(),
                entity.getCloseTime(),
                null
        );
    }
}

