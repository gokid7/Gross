package com.migros.courierService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterModelDto {

    private long id;
    private Timestamp timestamp;

    private long storeId;
    private String storeName;
    private Double storeLat;
    private Double storeLng;

    private long courierId;
}
