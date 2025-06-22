package com.itsmerishab08.salonservice.service;

import com.itsmerishab08.salonservice.request.SalonRequest;
import com.itsmerishab08.salonservice.response.BaseResponse;
import com.itsmerishab08.salonservice.response.SalonResponse;

public interface SalonService {

    BaseResponse createUpdateSalon(SalonRequest salonRequest);
    BaseResponse deleteSalon(SalonRequest salonRequest);
    SalonResponse getSalonById(SalonRequest salonRequest);
    SalonResponse getSalonByUserId(SalonRequest salonRequest);
    SalonResponse getSalonByCity(SalonRequest salonRequest);
    SalonResponse getAllSalons(SalonRequest salonRequest);

}
