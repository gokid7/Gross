package com.migros.registerService.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RegisterModelDto {

    private long id;
    private Timestamp timestamp;

    private long storeId;
    private String storeName;
    private Double storeLat;
    private Double storeLng;

    private long courierId;
}
