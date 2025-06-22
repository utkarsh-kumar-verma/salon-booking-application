package com.itsmerishab08.salonservice.service.impl;

import com.itsmerishab08.salonservice.dao.SalonServiceDAO;
import com.itsmerishab08.salonservice.entity.SalonEntity;
import com.itsmerishab08.salonservice.mapper.SalonMapper;
import com.itsmerishab08.salonservice.model.SalonModel;
import com.itsmerishab08.salonservice.request.SalonRequest;
import com.itsmerishab08.salonservice.response.BaseResponse;
import com.itsmerishab08.salonservice.response.SalonResponse;
import com.itsmerishab08.salonservice.service.SalonService;
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
public class SalonServiceImpl implements SalonService {

    private static final Logger logger = LoggerFactory.getLogger(SalonServiceImpl.class);
    private final SalonServiceDAO salonServiceDAO;

    @Override
    public BaseResponse createUpdateSalon(SalonRequest salonRequest) {
        BaseResponse response = new BaseResponse();
        try{
            SalonEntity entity;

            if (salonRequest.getId() != null && !salonRequest.getId().isBlank()) {
                // UPDATE flow
                entity = salonServiceDAO.getSalonById(salonRequest.getId());

                if (entity == null) {
                    throw new Exception("Salon with ID " + salonRequest.getId() + " not found");
                }

                // Update fields from request
                entity.setName(salonRequest.getName());
                entity.setDescription(salonRequest.getDescription());
                entity.setImages(salonRequest.getImages());
                entity.setAddress(salonRequest.getAddress());
                entity.setEmail(salonRequest.getEmail());
                entity.setPhoneNumber(salonRequest.getPhoneNumber());
                entity.setCity(salonRequest.getCity());
                entity.setOwnerId(salonRequest.getOwnerId());
                entity.setOpenTime(salonRequest.getOpenTime());
                entity.setCloseTime(salonRequest.getCloseTime());
                entity.setUpdatedAt(new Date());

            } else {
                // CREATE flow
                entity = SalonMapper.toEntity(salonRequest);
                entity.setUpdatedAt(new Date());
                entity.setCreatedAt(new Date());
            }

            String id = salonServiceDAO.saveSalon(entity);

            response.setMessage("Salon " + (salonRequest.getId() == null ? "created" : "updated") + " successfully with ID: " + id);
            response.setStatus(HttpStatus.OK.toString());
        }catch(Exception exception)
        {
            logger.error("Exception in creating/ updating the salon {}", exception.getMessage());
            response.setMessage("Exception in creating/ updating the salon " + exception.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }
        return response;
    }

    @Override
    public BaseResponse deleteSalon(SalonRequest salonRequest) {
        BaseResponse response = new BaseResponse();
        try{
            SalonEntity entity = salonServiceDAO.getSalonById(salonRequest.getId());
            if (entity == null) {
                throw new Exception("Salon with ID " + salonRequest.getId() + " not found");
            }
            entity.setUpdatedAt(new Date());
            entity.setDeletedAt(new Date());
            String id = salonServiceDAO.saveSalon(entity);
            response.setMessage("Salon " + id + " successfully deleted");
            response.setStatus(HttpStatus.OK.toString());
        }catch(Exception exception)
        {
            logger.error("Exception in deleting the salon {}", exception.getMessage());
            response.setMessage("Exception in deleting the salon " + exception.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }
        return response;
    }

    @Override
    public SalonResponse getSalonById(SalonRequest salonRequest) {
        SalonResponse response = new SalonResponse();
        try {
            String id = salonRequest.getId();

            if (id == null || id.isBlank()) {
                throw new Exception("Salon ID must be provided");
            }

            SalonEntity entity = salonServiceDAO.getSalonById(id);

            if (entity == null) {
                throw new Exception("Salon not found for ID: " + id);
            }

            SalonModel model = SalonMapper.toModel(entity);
            response.setSalon(model);
            response.setTotalCount(1);
            response.setStatus(HttpStatus.OK.toString());
            response.setMessage("Salon fetched successfully");

        }
        catch (Exception e) {
            logger.error("Unexpected error while fetching salon by ID", e);
            response.setMessage("Unexpected error while fetching salon by ID: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }
        return response;
    }



    @Override
    public SalonResponse getSalonByUserId(SalonRequest salonRequest) {
        SalonResponse response = new SalonResponse();
        try {
            String ownerId = salonRequest.getOwnerId();

            if (ownerId == null || ownerId.isBlank()) {
                throw new Exception("Owner ID must be provided");
            }
            Pageable pageable = PageRequest.of(salonRequest.getPageNo()-1, salonRequest.getPageSize()); // You can enhance this later

            Page<SalonEntity> page = salonServiceDAO.getSalonsByOwner(ownerId, pageable);

            List<SalonModel> salonModels = page.getContent()
                    .stream()
                    .map(SalonMapper::toModel)
                    .collect(Collectors.toList());

            response.setSalons(salonModels);
            response.setTotalCount(page.getTotalElements());
            response.setPageNo(page.getNumber());
            response.setPageSize(page.getSize());
            response.setStatus(HttpStatus.OK.toString());
            response.setMessage("Salons fetched successfully for owner ID: " + ownerId);

        } catch (Exception e) {
            logger.error("Error in getSalonByUserId: {}", e.getMessage(), e);
            response.setMessage("Error fetching salons by user ID: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }

        return response;
    }


    @Override
    public SalonResponse getSalonByCity(SalonRequest salonRequest) {
        SalonResponse response = new SalonResponse();
        try {
            String city = salonRequest.getCity();

            if (city == null || city.isBlank()) {
                throw new Exception("City must be provided");
            }

            Pageable pageable = PageRequest.of(salonRequest.getPageNo()-1, salonRequest.getPageSize());

            Page<SalonEntity> page = salonServiceDAO.getSalonsByCity(city, pageable);

            List<SalonModel> salonModels = page.getContent()
                    .stream()
                    .map(SalonMapper::toModel)
                    .collect(Collectors.toList());

            response.setSalons(salonModels);
            response.setTotalCount(page.getTotalElements());
            response.setPageNo(page.getNumber());
            response.setPageSize(page.getSize());
            response.setStatus(HttpStatus.OK.toString());
            response.setMessage("Salons fetched successfully for city: " + city);

        } catch (Exception e) {
            logger.error("Error in getSalonByCity: {}", e.getMessage(), e);
            response.setMessage("Error fetching salons by city: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }

        return response;
    }


    @Override
    public SalonResponse getAllSalons(SalonRequest salonRequest) {
        SalonResponse response = new SalonResponse();
        try {
            int pageNo = salonRequest.getPageNo() - 1;
            int pageSize = salonRequest.getPageSize();

            Pageable pageable = PageRequest.of(pageNo, pageSize);

            Page<SalonEntity> page = salonServiceDAO.getAllSalons(pageable);

            List<SalonModel> salonModels = page.getContent()
                    .stream()
                    .map(SalonMapper::toModel)
                    .collect(Collectors.toList());

            response.setSalons(salonModels);
            response.setTotalCount(page.getTotalElements());
            response.setPageNo(page.getNumber());
            response.setPageSize(page.getSize());
            response.setStatus(HttpStatus.OK.toString());
            response.setMessage("All salons fetched successfully");

        } catch (Exception e) {
            logger.error("Error in getAllSalons: {}", e.getMessage(), e);
            response.setMessage("Error fetching all salons: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
        }

        return response;
    }

}
