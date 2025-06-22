package com.itsmerishab08.salonservice.controller;

import com.itsmerishab08.salonservice.request.SalonRequest;
import com.itsmerishab08.salonservice.response.BaseResponse;
import com.itsmerishab08.salonservice.response.SalonResponse;
import com.itsmerishab08.salonservice.service.SalonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salon")
public class SalonController {

    private final SalonService salonService;

    @GetMapping("/get_salon_by_id")
    public SalonResponse getSalonById(@RequestParam("id") String id) {
        SalonRequest request = new SalonRequest();
        request.setId(id);
        return salonService.getSalonById(request);
    }

    @GetMapping("/get_salon_by_user")
    public SalonResponse getSalonByUserId(@RequestParam("owner_id") String ownerId,
                                          @RequestParam("page_no") int pageNo,
                                          @RequestParam("page_size") int pageSize) {
        SalonRequest request = new SalonRequest();
        request.setOwnerId(ownerId);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        return salonService.getSalonByUserId(request);
    }

    @GetMapping("/get_salon_by_city")
    public SalonResponse getSalonByCity(@RequestParam("city") String city,
                                        @RequestParam("page_no") int pageNo,
                                        @RequestParam("page_size") int pageSize) {
        SalonRequest request = new SalonRequest();
        request.setCity(city);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        return salonService.getSalonByCity(request);
    }

    @GetMapping("/get_all_salons")
    public SalonResponse getAllSalons(@RequestParam("page_no") int pageNo,
                                      @RequestParam("page_size") int pageSize) {
        SalonRequest request = new SalonRequest();
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        return salonService.getAllSalons(request);
    }

    @PostMapping("/create_update_salon")
    public BaseResponse createUpdateSalon(@RequestBody SalonRequest request) {
        return salonService.createUpdateSalon(request);
    }

    @PostMapping("/delete_salon")
    public BaseResponse deleteSalon(@RequestBody SalonRequest request) {
        return salonService.deleteSalon(request);
    }
}
