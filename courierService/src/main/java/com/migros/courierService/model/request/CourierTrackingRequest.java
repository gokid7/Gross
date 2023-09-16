package com.migros.courierService.model.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CourierTrackingRequest {
    private Timestamp time;
    private long courierId;
    private Double courierLat;
    private Double courierLng;
}
