package com.betacom.sb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "license_plate", nullable = false, unique = true, length = 7)
	private String licensePlate;
	
	@Column(nullable = false)
	private Integer cc;
	
	@Column(name = "door_number", nullable = false)
	private Integer doorNumber;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_vehicle_car"))
    private Vehicle vehicle;
}
