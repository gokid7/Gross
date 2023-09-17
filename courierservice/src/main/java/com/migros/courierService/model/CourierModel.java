package com.migros.courierService.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courier")
@Data
@NoArgsConstructor
public class CourierModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public CourierModel(long id) {
        this.id = id;
    }
}
