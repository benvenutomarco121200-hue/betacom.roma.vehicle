package com.betacom.sb.models;

import com.betacom.sb.enums.Category;
import com.betacom.sb.enums.FuelType;
import com.betacom.sb.enums.VehicleType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false, length = 50)
    private VehicleType vehicleType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false, length = 50)
    private FuelType fuelType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Category category;
    
    @Column(name = "wheel_count", nullable = false)
    private Integer wheelCount;
    
    @Column(nullable = false, length = 50)
    private String color;
    
    @Column(nullable = false, length = 50)
    private String brand;
    
    @Column(name = "production_year", nullable = false)
    private Integer productionYear;
    
    @Column(nullable = false, length = 50)
    private String model;
    
    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Car car;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Motorcycle motorcycle;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Bicycle bicycle;
}
