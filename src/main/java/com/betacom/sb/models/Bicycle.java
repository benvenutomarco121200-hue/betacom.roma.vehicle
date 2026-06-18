package com.betacom.sb.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bicycles")
@Getter
@Setter
public class Bicycle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "gear_count", nullable = false)
	private Integer gearCount;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(
	    name = "brake_type_id", 
	    nullable = false, 
	    foreignKey = @ForeignKey(name = "fk_vehicle_braketype") 
	)
	private BrakeType brakeType;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(
	    name = "suspension_type_id", 
	    nullable = false, 
	    foreignKey = @ForeignKey(name = "fk_vehicle_suspensiontype") 
	)
	private SuspensionType suspensionType;
	
	@Column(name = "is_foldable", nullable = false)
	private Boolean isFoldable;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_vehicle_bicycle"))
    private Vehicle vehicle;
}
