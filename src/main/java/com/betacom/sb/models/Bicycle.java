package com.betacom.sb.models;

import com.betacom.sb.enums.BreakType;
import com.betacom.sb.enums.SuspensionType;

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
	
	@Column(nullable = false)
	private Integer gears;
	
    @Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private BreakType breakType;
	
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
	private SuspensionType suspensionType;
	
	@Column(nullable = false)
	private Boolean isFoldable;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_vehicle_bicycle"))
    private Vehicle vehicle;
}
