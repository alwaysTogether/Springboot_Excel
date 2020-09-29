package com.example.shipcalc;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShipmentData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String companyName;

    @Column
    private String country;

    @Column
    private int weight;

    @Column
    private int price;

}
