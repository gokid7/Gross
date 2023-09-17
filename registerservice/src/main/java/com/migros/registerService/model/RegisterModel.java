package com.migros.registerService.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "register")
@Data
@NoArgsConstructor
public class RegisterModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "storeId",nullable = false)
    private long storeId;
    @Column(name = "storeName",nullable = false)
    private String storeName;
    @Column(name = "storeLat",nullable = false)
    private Double storeLat;
    @Column(name = "storeLng",nullable = false)
    private Double storeLng;

    @Column(name = "courierId",nullable = false)
    private long courierId;

    public RegisterModel(long id, long storeId, String storeName, Double storeLat, Double storeLng, long courierId) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeLat = storeLat;
        this.storeLng = storeLng;
        this.courierId = courierId;
    }
}
