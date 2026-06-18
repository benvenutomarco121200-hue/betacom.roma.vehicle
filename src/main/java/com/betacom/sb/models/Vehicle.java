package com.betacom.sb.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(
	    name = "vehicle_type_id", 
	    nullable = false, 
	    foreignKey = @ForeignKey(name = "fk_vehicle_vehicletype") 
	)
    private VehicleType vehicleType;
    
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(
	    name = "fuel_type_id", 
	    nullable = false, 
	    foreignKey = @ForeignKey(name = "fk_vehicle_fueltype") 
	)
    private FuelType fuelType;
    
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(
	    name = "category_id", 
	    nullable = false, 
	    foreignKey = @ForeignKey(name = "fk_vehicle_category") 
	)
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
