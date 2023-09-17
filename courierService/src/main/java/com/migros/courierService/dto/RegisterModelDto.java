package com.migros.courierService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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

    @JsonProperty("id")
    private long id;
    @JsonProperty("timestamp")
    private Timestamp timestamp;

    @JsonProperty("storeId")
    private long storeId;
    @JsonProperty("storeName")
    private String storeName;
    @JsonProperty("storeLat")
    private Double storeLat;
    @JsonProperty("storeLng")
    private Double storeLng;

    @JsonProperty("courierId")
    private long courierId;
}
