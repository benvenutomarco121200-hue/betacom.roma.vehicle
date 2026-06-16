package com.betacom.sb.models;

import com.betacom.sb.enums.BrakeType;
import com.betacom.sb.enums.SuspensionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "bicycles")
@Getter
@Setter
public class Bicycle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "gear_count", nullable = false)
	private Integer gearCount;
	
    @Enumerated(EnumType.STRING)
	@Column(name = "brake_type", nullable = false, length = 50)
	private BrakeType brakeType;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "suspension_type", nullable = false, length = 50)
	private SuspensionType suspensionType;
	
	@Column(name = "is_foldable", nullable = false)
	private Boolean isFoldable;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_vehicle_bicycle"))
    private Vehicle vehicle;
}
