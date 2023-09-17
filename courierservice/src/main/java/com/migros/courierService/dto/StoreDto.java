package com.migros.courierService.dto;

import lombok.Data;

@Data
public class StoreDto {

    private long id;
    private String name;
    private Double lat;
    private Double lng;
}
