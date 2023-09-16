package com.migros.courierService.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourierDto {

    private long id;
    private String status;
    private List<StoreDto> stores;
}
